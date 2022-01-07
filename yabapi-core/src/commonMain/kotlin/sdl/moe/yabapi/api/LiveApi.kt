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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.LIVE_DANMAKU_INFO_URL
import sdl.moe.yabapi.consts.internal.LIVE_INIT_INFO_GET_URL
import sdl.moe.yabapi.data.live.CertificatePacketBody
import sdl.moe.yabapi.data.live.LiveDanmakuHost
import sdl.moe.yabapi.data.live.LiveDanmakuInfoGetResponse
import sdl.moe.yabapi.data.live.LiveInitGetResponse
import sdl.moe.yabapi.packet.LiveMsgPacket
import sdl.moe.yabapi.packet.LiveMsgPacketProtocol.SPECIAL_NO_COMPRESSION
import sdl.moe.yabapi.packet.LiveMsgPacketType.CERTIFICATE
import sdl.moe.yabapi.packet.LiveMsgPacketType.HEARTBEAT
import sdl.moe.yabapi.packet.Sequence
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
    ): Unit =
        withContext(dispatcher) {
            val sequence = Sequence()
            client.wss(HttpMethod.Get, host = host.host, host.wssPort, "/sub") {
                val isSuccess = sendCertificatePacket(loginUserMid, realRoomId, token, sequence)
                if (isSuccess) launchHeartbeatJob(dispatcher, sequence)
            }
        }

    private suspend fun DefaultClientWebSocketSession.sendLiveDanmakuPacket(
        packet: LiveMsgPacket, sequence: Sequence,
    ): Boolean {
        var isSuccess = false
        outgoing.trySend(Frame.byType(true, BINARY, packet.encode())).also {
            logger.debug { "Try to send ${packet.head.type} packet." }
        }.apply {
            onFailure { logger.debug { "Failed to send ${packet.head.type} packet." } }
            onSuccess {
                logger.debug { "Sent ${packet.head.type} packet: $packet" }
                sequence.value.getAndIncrement()
                logger.verbose { "Now Sequence: $sequence" }
                isSuccess = true
            }
        }
        return isSuccess
    }

    private suspend inline fun DefaultClientWebSocketSession.launchHeartbeatJob(
        dispatcher: CoroutineDispatcher,
        sequence: Sequence,
    ): Job = launch(dispatcher) {
        while (true) {
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
