package moe.sdl.yabapi.connect

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.wss
import io.ktor.http.HttpMethod
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readUInt
import io.ktor.utils.io.core.toByteArray
import io.ktor.utils.io.core.writeFully
import io.ktor.websocket.Frame
import io.ktor.websocket.FrameType.BINARY
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineName
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
import moe.sdl.yabapi.Platform
import moe.sdl.yabapi.data.live.CertificatePacketBody
import moe.sdl.yabapi.data.live.CertificatePacketResponse
import moe.sdl.yabapi.data.live.LiveDanmakuHost
import moe.sdl.yabapi.data.live.commands.LiveCommand
import moe.sdl.yabapi.data.live.commands.RawLiveCommand
import moe.sdl.yabapi.packet.LiveMsgPacket
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.SPECIAL_NO_COMPRESSION
import moe.sdl.yabapi.packet.LiveMsgPacketType.CERTIFICATE
import moe.sdl.yabapi.packet.LiveMsgPacketType.CERTIFICATE_RESPONSE
import moe.sdl.yabapi.packet.LiveMsgPacketType.COMMAND
import moe.sdl.yabapi.packet.LiveMsgPacketType.HEARTBEAT
import moe.sdl.yabapi.packet.LiveMsgPacketType.HEARTBEAT_RESPONSE
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.string.findJson
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("LiveMessageConnection") }

private typealias Wss = DefaultClientWebSocketSession

internal class LiveMessageConnection(
    private val loginUserMid: Int,
    private val realRoomId: Int,
    private val token: String,
    private val host: LiveDanmakuHost,
    private val client: HttpClient,
    private val jsonParser: Json,
    private val context: CoroutineContext = Platform.ioDispatcher + CoroutineName("yabapi-live-msg-connect"),
    config: LiveDanmakuConnectConfig.() -> Unit = {},
) {
    private val configInstance = LiveDanmakuConnectConfig()

    init {
        configInstance.config()
    }

    private val sequence = atomic(0L)

    suspend fun start() = coroutineScope {
        launch(context) {
            requireNotNull(host.host)
            requireNotNull(host.wssPort)
            client.wss(HttpMethod.Get, host = host.host, host.wssPort, "/sub") {
                val isSuccess = sendCertificatePacket()
                if (isSuccess) {
                    launch {
                        doHeartbeatJob()
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
        outgoing.trySend(Frame.byType(true, BINARY, packet.encode(), rsv1 = false, rsv2 = false, rsv3 = false)).also {
            logger.debug { "Try to send ${packet.header.type} packet." }
        }.onFailure {
            if (it is CancellationException) throw it
            logger.debug { "Failed to send ${packet.header.type} packet: $packet" }
            logger.verbose(it) { "stacktrace:" }
        }.onSuccess {
            logger.debug { "Sent ${packet.header.type} packet: $packet" }
            sequence.getAndIncrement()
            logger.verbose { "Now Sequence: $sequence" }
            isSuccess = true
        }.onClosed {
            if (it is CancellationException) throw it
            logger.debug { "Outgoing Channel closed" }
            cancel("Remote closed", it)
        }
        return isSuccess
    }

    private suspend inline fun Wss.handleIncoming() {
        incoming.consumeAsFlow().collect { frame ->
            when (frame) {
                is Frame.Binary -> {
                    try {
                        LiveMsgPacket.decode(frame.data).also { packet ->
                            logger.debug { "Decoded Packet Head: ${packet.header}" }
                            handleBinaryPacket(packet)
                        }
                    } catch (e: NotImplementedError) {
                        logger.warn(e) { "Not Implemented Compression" }
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
    ) = when (packet.header.type) {
        HEARTBEAT_RESPONSE -> {
            val popular = buildPacket { writeFully(packet.body) }.readUInt()
            logger.debug { "Decoded popular value: $popular" }
            configInstance.onHeartbeatResponse(
                this,
                channelFlow {
                    this.send(popular)
                }
            )
        }
        CERTIFICATE_RESPONSE -> {
            val data: CertificatePacketResponse =
                jsonParser.decodeFromString(packet.body.decodeToString())
            logger.debug { "Decoded Certificate Response: $data" }
            configInstance.onCertificateResponse(
                this,
                channelFlow {
                    this.send(data)
                }
            )
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
                    logger.warn(e) { "Unexpected Serialization Exception, raw decoded: $raw" }
                    null
                }
                logger.debug { "Decoded LiveCommand $data" }
                configInstance.onCommandResponse(
                    this,
                    channelFlow {
                        data?.let { this.send(it) }
                    }
                )
            }
        }
        else -> error("Decoded Unexpected Incoming Packet: $packet")
    }

    private suspend inline fun Wss.doHeartbeatJob() {
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

/**
 * 直播弹幕信息流的配置
 *
 * 将函数存储为值以供调用, 默认为空
 *
 * 流是通过 `channelFlow` 构造的, 上流是 `channel` 下流是 `flow`.
 * 因此, 并不具备冷流特性, 详见官方文档
 * @see channelFlow
 * @see Flow
 */
public class LiveDanmakuConnectConfig {
    public var onHeartbeatResponse: suspend Wss.(popular: Flow<UInt>) -> Unit = {}

    public var onCertificateResponse: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit = {}

    public var onCommandResponse: suspend Wss.(command: Flow<LiveCommand>) -> Unit = {}

    public var onRawCommandResponse: suspend Wss.(command: Flow<RawLiveCommand>) -> Unit = {}
}

public inline fun Config.onHeartbeatResponse(noinline block: suspend Wss.(popular: Flow<UInt>) -> Unit) {
    onHeartbeatResponse = block
}

public inline fun Config.onCertificateResponse(noinline block: suspend Wss.(response: Flow<CertificatePacketResponse>) -> Unit) {
    onCertificateResponse = block
}

public inline fun Config.onCommandResponse(noinline block: suspend Wss.(command: Flow<LiveCommand>) -> Unit) {
    onCommandResponse = block
}

public inline fun Config.onRawCommandResponse(noinline block: suspend Wss.(command: Flow<RawLiveCommand>) -> Unit) {
    onRawCommandResponse = block
}
