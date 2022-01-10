// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.connect

import com.soywiz.korio.lang.toByteArray
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.wss
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.FrameType.BINARY
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readUInt
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.data.live.CertificatePacketBody
import sdl.moe.yabapi.data.live.CertificatePacketResponse
import sdl.moe.yabapi.data.live.LiveDanmakuHost
import sdl.moe.yabapi.data.live.commands.LiveCommand
import sdl.moe.yabapi.data.live.commands.RawLiveCommand
import sdl.moe.yabapi.packet.LiveMsgPacket
import sdl.moe.yabapi.packet.LiveMsgPacketProtocol.SPECIAL_NO_COMPRESSION
import sdl.moe.yabapi.packet.LiveMsgPacketType.CERTIFICATE
import sdl.moe.yabapi.packet.LiveMsgPacketType.CERTIFICATE_RESPONSE
import sdl.moe.yabapi.packet.LiveMsgPacketType.COMMAND
import sdl.moe.yabapi.packet.LiveMsgPacketType.HEARTBEAT
import sdl.moe.yabapi.packet.LiveMsgPacketType.HEARTBEAT_RESPONSE
import sdl.moe.yabapi.packet.Sequence
import sdl.moe.yabapi.util.Logger
import sdl.moe.yabapi.util.findJson
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private val logger = Logger("LiveMessageConnection")

private typealias Wss = DefaultClientWebSocketSession

internal class LiveMessageConnection(
    private val loginUserMid: Int,
    private val realRoomId: Int,
    private val token: String,
    private val host: LiveDanmakuHost,
    private val client: HttpClient,
    private val jsonParser: Json,
    private val dispatcher: CoroutineDispatcher = Platform.ioDispatcher,
    config: LiveDanmakuConnectConfig.() -> Unit = {},
) {
    private val configInstance = LiveDanmakuConnectConfig()

    init {
        configInstance.config()
    }

    private val sequence = Sequence()

    suspend fun start() = coroutineScope {
        launch(dispatcher) {
            client.wss(HttpMethod.Get, host = host.host, host.wssPort, "/sub") {
                val isSuccess = sendCertificatePacket()
                if (isSuccess) {
                    launch {
                        launchHeartbeatJob()
                    }
                    handleIncoming()
                }
            }
        }
    }

    private suspend inline fun Wss.sendLiveDanmakuPacket(
        packet: LiveMsgPacket,
    ): Boolean {
        var isSuccess = false
        outgoing.trySend(Frame.byType(true, BINARY, packet.encode())).also {
            logger.debug { "Try to send ${packet.head.type} packet." }
        }.onFailure {
            if (it is CancellationException) {
                logger.info(it) { "Send Job Cancelled" }
                return@onFailure
            }
            logger.debug { "Failed to send ${packet.head.type} packet: $packet" }
            logger.verbose(it) { "stacktrace:" }
        }.onSuccess {
            logger.debug { "Sent ${packet.head.type} packet: $packet" }
            sequence.value.getAndIncrement()
            logger.verbose { "Now Sequence: $sequence" }
            isSuccess = true
        }.onClosed {
            logger.debug { "Outgoing Channel closed" }
            cancel("Remote closed", it)
        }
        return isSuccess
    }

    private suspend inline fun Wss.handleIncoming() {
        incoming.consumeAsFlow().collect { frame ->
            when (frame) {
                is Frame.Binary -> {
                    logger.verbose { "Received Binary: ${frame.data.contentToString()}" }
                    LiveMsgPacket.decode(frame.data).also { packet ->
                        logger.debug { "Decoded Packet Head: ${packet.head}" }
                        handleBinaryPacket(packet)
                    }
                }
                is Frame.Text -> logger.debug { "Received Text: ${frame.data.contentToString()}" }
                is Frame.Close -> cancel("Remote closed.")
                else -> {
                    // DO NOTHING
                }
            }
        }
    }

    private suspend inline fun Wss.handleBinaryPacket(
        packet: LiveMsgPacket,
    ) {
        when (packet.head.type) {
            HEARTBEAT_RESPONSE -> {
                val popular = buildPacket { writeFully(packet.body) }.readUInt()
                logger.debug { "Decoded popular value: $popular" }
                configInstance.onHeartbeatResponse(this, channelFlow {
                    this.send(popular)
                })
            }
            CERTIFICATE_RESPONSE -> {
                val data: CertificatePacketResponse =
                    jsonParser.decodeFromString(packet.body.decodeToString())
                logger.debug { "Decoded Certificate Response: $data" }
                configInstance.onCertificateResponse(this, channelFlow {
                    this.send(data)
                })
            }
            COMMAND -> {
                val flow = packet.body.decodeToString().also {
                    logger.verbose { "Raw Received body decoded to string: $it" }
                }.findJson().asFlow()
                flow.map { parsed -> // String -> RawLiveCommand
                    logger.verbose { "Decoded weired json string: $parsed" }
                    RawLiveCommand(jsonParser.decodeFromString(parsed))
                }.collect { raw -> // Send Raw to downstream
                    logger.debug { "Decoded RawLiveCommand $raw" }
                    configInstance.onRawCommandResponse(this, channelFlow { send(raw) })

                    val data = try {
                        raw.data
                    } catch (e: SerializationException) {
                        logger.warn(e) {
                            "Unexpected Serialization Exception, raw decoded: $raw"
                        }
                        null
                    }
                    logger.debug { "Decoded LiveCommand $data" }
                    configInstance.onCommandResponse(this, channelFlow {
                        data?.let { this.send(it) }
                    })
                }
            }
            else -> error("Decoded Unexpected Incoming Packet: $packet")
        }
    }

    private suspend inline fun Wss.launchHeartbeatJob() {
        while (isActive) {
            sendHeartbeatPacket()
            delay(30_000)
        }
    }

    private suspend inline fun Wss.sendCertificatePacket(): Boolean {
        val body = jsonParser.encodeToString(
            CertificatePacketBody(
                mid = loginUserMid,
                roomId = realRoomId,
                key = token
            )
        ).toByteArray()
        return sendLiveDanmakuPacket(
            LiveMsgPacket(
                protocol = SPECIAL_NO_COMPRESSION,
                type = CERTIFICATE,
                sequence = sequence,
                body = body
            )
        )
    }

    private suspend inline fun Wss.sendHeartbeatPacket() =
        sendLiveDanmakuPacket(
            LiveMsgPacket(
                protocol = SPECIAL_NO_COMPRESSION,
                type = HEARTBEAT,
                sequence = sequence,
                body = "[object Object]".toByteArray()
            )
        )
}

private typealias Config = LiveDanmakuConnectConfig

public class LiveDanmakuConnectConfig {
    internal var onHeartbeatResponse: suspend Wss.(popular: Flow<UInt>) -> Unit = {}

    internal var onCertificateResponse: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit = {}

    internal var onCommandResponse: suspend Wss.(command: Flow<LiveCommand>) -> Unit = {}

    internal var onRawCommandResponse: suspend Wss.(command: Flow<RawLiveCommand>) -> Unit = {}
}

public fun Config.onHeartbeatResponse(block: suspend Wss.(popular: Flow<UInt>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onHeartbeatResponse = block
}

public fun Config.onCertificateResponse(block: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onCertificateResponse = block
}

public fun Config.onCommandResponse(block: suspend Wss.(command: Flow<LiveCommand>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onCommandResponse = block
}

public fun Config.onRawCommandResponse(block: suspend Wss.(command: Flow<RawLiveCommand>) -> Unit) {
    contract { callsInPlace(block, EXACTLY_ONCE) }
    onRawCommandResponse = block
}
