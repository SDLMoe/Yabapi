// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.GET_TIMESTAMP_URL
import sdl.moe.yabapi.data.time.TimestampGetResponse
import sdl.moe.yabapi.util.Logger

private val logger = Logger("TimeApi")

/**
 * 时间相关 API
 */
public object TimeApi : BiliApi {
    override val apiName: String = "time"

    init {
        BiliClient.registerApi(this)
    }

    @Suppress("unused")
    public val BiliClient.timeApi: TimeApi
        get() = this@TimeApi

    /**
     * 從 API 服務器獲得當前時間戳
     * @return [TimestampGetResponse]
     */
    public suspend fun BiliClient.getTimestamp(): TimestampGetResponse = withContext(dispatcher) {
        logger.debug { "Getting timestamp" }
        client.get<TimestampGetResponse>(GET_TIMESTAMP_URL).also {
            logger.debug { "Timestamp Get Response: $it" }
        }
    }
}
