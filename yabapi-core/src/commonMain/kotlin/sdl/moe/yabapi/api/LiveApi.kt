// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import com.soywiz.korio.lang.toByteArray
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.wss
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.FrameType.BINARY
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readUInt
import io.ktor.utils.io.core.writeFully
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.config.LiveDanmakuSocketConfig
import sdl.moe.yabapi.consts.internal.LIVE_DANMAKU_INFO_URL
import sdl.moe.yabapi.consts.internal.LIVE_INIT_INFO_GET_URL
import sdl.moe.yabapi.consts.json
import sdl.moe.yabapi.data.live.CertificatePacketBody
import sdl.moe.yabapi.data.live.CertificatePacketResponse
import sdl.moe.yabapi.data.live.LiveDanmakuHost
import sdl.moe.yabapi.data.live.LiveDanmakuInfoGetResponse
import sdl.moe.yabapi.data.live.LiveInitGetResponse
import sdl.moe.yabapi.data.live.commands.RawLiveCommand
import sdl.moe.yabapi.packet.LiveMsgPacket
import sdl.moe.yabapi.packet.LiveMsgPacketProtocol.SPECIAL_NO_COMPRESSION
import sdl.moe.yabapi.packet.LiveMsgPacketType.CERTIFICATE
import sdl.moe.yabapi.packet.LiveMsgPacketType.CERTIFICATE_RESPONSE
import sdl.moe.yabapi.packet.LiveMsgPacketType.COMMAND
import sdl.moe.yabapi.packet.LiveMsgPacketType.HEARTBEAT
import sdl.moe.yabapi.packet.LiveMsgPacketType.HEARTBEAT_RESPONSE
import sdl.moe.yabapi.packet.Sequence
import sdl.moe.yabapi.util.findJson
import sdl.moe.yabapi.util.logger

public object LiveApi : BiliApi {
    override val apiName: String = "live"

    init {
        BiliClient.registerApi(this)
    }

    /**
     * 獲得直播間初始化信息
     * @see [LiveInitGetResponse]
     */
    public suspend fun BiliClient.getRoomInitInfo(roomId: Int): LiveInitGetResponse =
        withContext(dispatcher) {
            logger.debug { "Getting Room Init Info for room $roomId" }
            client.get<LiveInitGetResponse>(LIVE_INIT_INFO_GET_URL) {
                parameter("id", roomId)
            }.also {
                logger.debug { "Got Room Init Info for room $roomId: $it" }
            }
        }

    /**
     * @param realRoomId 需要輸入真實直播間 ID
     * @see getRoomInitInfo
     */
    public suspend fun BiliClient.getLiveDanmakuInfo(realRoomId: Int): LiveDanmakuInfoGetResponse =
        withContext(dispatcher) {
            logger.debug { "Getting live danmaku info for room $realRoomId" }
            client.get<LiveDanmakuInfoGetResponse>(LIVE_DANMAKU_INFO_URL) {
                parameter("id", realRoomId)
            }.also {
                logger.debug { "Got live danmaku info for room $realRoomId: $it" }
            }
        }

    /**
     * @param loginUserMid 當前用戶的 mid
     * @param realRoomId 真實直播間 ID
     * @param token 密鑰, 獲取: [LiveDanmakuInfoGetResponse]
     * @param host [LiveDanmakuHost]
     */
    public suspend fun BiliClient.createLiveDanmakuConnection(
        loginUserMid: Int,
        realRoomId: Int,
        token: String,
        host: LiveDanmakuHost,
        config: LiveDanmakuSocketConfig.() -> Unit = {},
    ): Unit =
        withContext(dispatcher) {
            val configInstance = LiveDanmakuSocketConfig()
            configInstance.config()
            val sequence = Sequence()
            client.wss(HttpMethod.Get, host = host.host, host.wssPort, "/sub") {
                val isSuccess = sendCertificatePacket(loginUserMid, realRoomId, token, sequence)
                if (isSuccess) {
                    launch {
                        launchHeartbeatJob(sequence)
                    }
                    handleIncoming(configInstance)
                }
            }
        }

    private suspend fun DefaultClientWebSocketSession.sendLiveDanmakuPacket(
        packet: LiveMsgPacket, sequence: Sequence,
    ): Boolean {
        var isSuccess = false
        outgoing.trySend(Frame.byType(true, BINARY, packet.encode())).also {
            logger.debug { "Try to send ${packet.head.type} packet." }
        }.onFailure {
            logger.debug { "Failed to send ${packet.head.type} packet: $packet" }
            logger.verbose(it) { "stacktrace:" }
        }.onSuccess {
            logger.debug { "Sent ${packet.head.type} packet: $packet" }
            sequence.value.getAndIncrement()
            logger.verbose { "Now Sequence: $sequence" }
            isSuccess = true
        }.onClosed {
            logger.debug { "Outgoing Channel closed" }
            cancel("Remote closed")
        }
        return isSuccess
    }

    private suspend fun DefaultClientWebSocketSession.handleIncoming(
        config: LiveDanmakuSocketConfig,
    ) {
        incoming.consumeAsFlow().collect { frame ->
            when (frame) {
                is Frame.Binary -> {
                    logger.verbose { "Received Binary: ${frame.data.contentToString()}" }
                    LiveMsgPacket.decode(frame.data).also { packet ->
                        logger.debug { "Decoded Packet Head: ${packet.head}" }
                        handleBinaryPacket(packet, config)
                    }
                }
                is Frame.Text -> logger.debug { "Received Text: ${frame.data.contentToString()}" }
                is Frame.Close -> logger.debug { "Remote closed." }
                else -> {
                    // DO NOTHING
                }
            }
        }
    }

    private suspend inline fun DefaultClientWebSocketSession.handleBinaryPacket(
        packet: LiveMsgPacket,
        config: LiveDanmakuSocketConfig,
    ) {
        when (packet.head.type) {
            HEARTBEAT_RESPONSE -> {
                val popular = buildPacket { writeFully(packet.body) }.readUInt()
                logger.debug { "Decoded popular value: $popular" }
                config.onHeartbeatResponse(this, channelFlow {
                    this.send(popular)
                })
            }
            CERTIFICATE_RESPONSE -> {
                val data: CertificatePacketResponse =
                    json.decodeFromString(packet.body.decodeToString())
                logger.debug { "Decoded Certificate Response: $data" }
                config.onCertificateResponse(this, channelFlow {
                    this.send(data)
                })
            }
            COMMAND -> {
                val flow = packet.body.decodeToString().also {
                    logger.verbose { "Raw Received body decoded to string: $it" }
                }.findJson().asFlow()
                flow.map { parsed -> // String -> RawLiveCommand
                    logger.verbose { "Decoded weired json string: $parsed" }
                    RawLiveCommand(json.decodeFromString(parsed))
                }.collect { raw -> // Send Raw to downstream
                    logger.debug { "Decoded RawLiveCommand $raw" }
                    config.onRawCommandResponse(this, channelFlow { send(raw) })

                    val data = try {
                        raw.data
                    } catch (e: SerializationException) {
                        logger.warn(e) {
                            "Unexpected Serialization Exception, raw decoded: $raw"
                        }
                        null
                    }
                    logger.debug { "Decoded LiveCommand $data" }
                    config.onCommandResponse(this, channelFlow {
                        data?.let { this.send(it) }
                    })
                }
            }
            else -> error("Decoded Unexpected Incoming Packet: $packet")
        }
    }

    private suspend inline fun DefaultClientWebSocketSession.launchHeartbeatJob(
        sequence: Sequence,
    ) {
        while (isActive) {
            sendHeartbeatPacket(sequence)
            delay(30_000)
        }
    }

    private suspend inline fun DefaultClientWebSocketSession.sendCertificatePacket(
        mid: Int,
        roomId: Int,
        token: String,
        sequence: Sequence,
    ): Boolean {
        val body = Json.encodeToString(CertificatePacketBody(mid, roomId, token)).toByteArray()
        return sendLiveDanmakuPacket(LiveMsgPacket(SPECIAL_NO_COMPRESSION, CERTIFICATE, sequence, body), sequence)
    }

    private suspend inline fun DefaultClientWebSocketSession.sendHeartbeatPacket(sequence: Sequence) =
        sendLiveDanmakuPacket(LiveMsgPacket(SPECIAL_NO_COMPRESSION,
            HEARTBEAT,
            sequence,
            "[object Object]".toByteArray()), sequence)
}
