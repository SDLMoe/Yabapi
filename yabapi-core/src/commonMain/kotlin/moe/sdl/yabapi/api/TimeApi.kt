package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.GET_TIMESTAMP_URL
import moe.sdl.yabapi.data.time.TimestampGetResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("TimeApi") }

// 时间相关 API

/**
 * 從 API 服務器獲得當前時間戳
 * @return [TimestampGetResponse]
 */
public suspend fun BiliClient.getTimestamp(
    context: CoroutineContext = this.context,
): TimestampGetResponse = withContext(context) {
    logger.debug { "Getting timestamp" }
    client.get(GET_TIMESTAMP_URL)
        .body<String>()
        .deserializeJson<TimestampGetResponse>()
        .also { logger.debug { "Timestamp Get Response: $it" } }
}
