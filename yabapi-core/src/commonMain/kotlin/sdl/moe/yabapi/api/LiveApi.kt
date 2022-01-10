// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.connect.LiveDanmakuConnectConfig
import sdl.moe.yabapi.connect.LiveMessageConnection
import sdl.moe.yabapi.consts.internal.LIVE_DANMAKU_INFO_URL
import sdl.moe.yabapi.consts.internal.LIVE_INIT_INFO_GET_URL
import sdl.moe.yabapi.data.live.LiveDanmakuHost
import sdl.moe.yabapi.data.live.LiveDanmakuInfoGetResponse
import sdl.moe.yabapi.data.live.LiveInitGetResponse
import sdl.moe.yabapi.util.Logger

private val logger = Logger("LiveApi")

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
    config: LiveDanmakuConnectConfig.() -> Unit = {},
): Unit =
    withContext(dispatcher) {
        val bClient = this@createLiveDanmakuConnection
        LiveMessageConnection(
            loginUserMid,
            realRoomId,
            token,
            host,
            bClient.client,
            bClient.json,
            bClient.dispatcher,
            config
        ).start().join()
    }
