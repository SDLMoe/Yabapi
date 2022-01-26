package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.FEED_CONTENT_GET_URL
import sdl.moe.yabapi.consts.internal.FEED_NEW_GET_URL
import sdl.moe.yabapi.data.feed.FeedContentResponse
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("FeedApi") }

public suspend fun BiliClient.getFeedContent(
    id: ULong,
    context: CoroutineContext = this.context,
): FeedContentResponse = withContext(context) {
    logger.debug { "Getting feed content for id $id" }
    client.get<String>(FEED_CONTENT_GET_URL) {
        parameter("dynamic_id", id)
    }.deserializeJson<FeedContentResponse>().also {
        logger.debug { "Got feed content for id $id: $it" }
    }
}

public suspend fun BiliClient.getNewFeed(
    uid: Int,
    types: IntArray,
    context: CoroutineContext = this.context,
): String {
    val typesStr = types.joinToString(",")
    return withContext(context) {
        logger.debug { "Getting new feed for uid $uid|types[$typesStr]..." }
        client.get<String>(FEED_NEW_GET_URL) {
            parameter("uid", uid)
            parameter("type_list", typesStr)
        }.also {
            logger.debug { "Got new feed for uid $uid|types[$typesStr]: $it" }
        }
    }
}
