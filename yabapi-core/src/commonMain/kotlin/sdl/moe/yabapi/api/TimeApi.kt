package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.GET_TIMESTAMP_URL
import sdl.moe.yabapi.data.time.TimestampGetResponse
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("TimeApi")

// 时间相关 API

/**
 * 從 API 服務器獲得當前時間戳
 * @return [TimestampGetResponse]
 */
public suspend fun BiliClient.getTimestamp(
    context: CoroutineContext = this.context,
): TimestampGetResponse = withContext(context) {
    logger.debug { "Getting timestamp" }
    client.get<TimestampGetResponse>(GET_TIMESTAMP_URL).also {
        logger.debug { "Timestamp Get Response: $it" }
    }
}
