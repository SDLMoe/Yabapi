// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.time.GET_TIMESTAMP_URL
import sdl.moe.yabapi.data.time.TimestampGetResponse

private val logger = KotlinLogging.logger {}

public object TimeApi : BiliApi {
    init {
        BiliClient.registerApi(apiName, this)
    }

    override val apiName: String
        get() = "time"

    @Suppress("unused")
    public val BiliClient.passport: TimeApi
        get() = this@TimeApi

    /**
     * 從 API 服務器獲得當前時間戳
     */
    @JvmName("getTimestampExt")
    public suspend fun BiliClient.getTimestamp(): TimestampGetResponse = withContext(Dispatchers.IO) {
        logger.debug { "Getting timestamp" }
        client.get<TimestampGetResponse>(GET_TIMESTAMP_URL).also {
            logger.debug { "Timestamp Get Response: $it" }
        }
    }

    @JvmName("getTimestamp")
    public suspend inline fun getTimestamp(client: BiliClient): TimestampGetResponse = client.getTimestamp()
}
