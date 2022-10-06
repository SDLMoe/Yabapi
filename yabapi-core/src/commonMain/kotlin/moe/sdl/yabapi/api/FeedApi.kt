package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.FEED_CONTENT_GET_URL
import moe.sdl.yabapi.consts.internal.FEED_HISTORY_GET_URL
import moe.sdl.yabapi.consts.internal.FEED_LIVING_GET_URL
import moe.sdl.yabapi.consts.internal.FEED_NEW_GET_URL
import moe.sdl.yabapi.consts.internal.FEED_SPACE_GET_URL
import moe.sdl.yabapi.consts.internal.FEED_UPDATED_GET_URL
import moe.sdl.yabapi.data.feed.FeedContentResponse
import moe.sdl.yabapi.data.feed.FeedHistoryResponse
import moe.sdl.yabapi.data.feed.FeedLivingResponse
import moe.sdl.yabapi.data.feed.FeedUpdatedResponse
import moe.sdl.yabapi.data.feed.NewFeedResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("FeedApi") }

public suspend fun BiliClient.getFeedContent(
    id: ULong,
    context: CoroutineContext = this.context,
): FeedContentResponse = withContext(context) {
    logger.debug { "Getting feed content for id $id" }
    client.get(FEED_CONTENT_GET_URL) {
        parameter("dynamic_id", id)
    }.body<String>().deserializeJson<FeedContentResponse>().also {
        logger.debug { "Got feed content for id $id: $it" }
    }
}

public suspend fun BiliClient.getNewFeed(
    currentUid: Long,
    types: IntArray,
    from: String = "weball",
    platform: String = "web",
    context: CoroutineContext = this.context,
): NewFeedResponse = withContext(context) {
    val typesStr = types.joinToString(",")
    logger.debug { "Getting new feed for uid $currentUid|types[$typesStr]..." }
    client.get(FEED_NEW_GET_URL) {
        parameter("uid", currentUid)
        parameter("type_list", typesStr)
        parameter("from", from)
        parameter("platform", platform)
    }.body<String>().deserializeJson<NewFeedResponse>().also {
        logger.debug { "Got new feed for uid $currentUid|types[$typesStr]: $it" }
    }
}

public suspend fun BiliClient.getHistoryFeed(
    currentUid: Long,
    types: IntArray,
    offset: ULong,
    from: String = "weball",
    platform: String = "web",
    context: CoroutineContext = this.context,
): FeedHistoryResponse = withContext(context) {
    val typesStr = types.joinToString(",")
    logger.debug { "Getting new feed for uid $currentUid|types[$typesStr]|offset$offset..." }
    client.get(FEED_HISTORY_GET_URL) {
        parameter("uid", currentUid)
        parameter("offset_dynamic_id", offset)
        parameter("type", typesStr)
        parameter("from", from)
        parameter("platform", platform)
    }.body<String>().deserializeJson<FeedHistoryResponse>().also {
        logger.debug { "Got new feed for uid $currentUid|types[$typesStr]|offset$offset: $it" }
    }
}

public suspend fun BiliClient.getFeedByUid(
    currentUid: Long,
    targetUid: Long,
    offset: ULong = 0uL,
    platform: String = "web",
    needTop: Boolean = true,
    context: CoroutineContext = this.context,
): FeedHistoryResponse = withContext(context) {
    logger.debug { "Getting new feed by uid $targetUid|offset$offset..." }
    client.get(FEED_SPACE_GET_URL) {
        parameter("visitor_uid", currentUid)
        parameter("host_uid", targetUid)
        parameter("offset_dynamic_id", offset)
        parameter("need_top", if (needTop) "1" else "0")
        parameter("platform", platform)
    }.body<String>().deserializeJson<FeedHistoryResponse>().also {
        logger.debug { "Got new feed by uid $targetUid|offset$offset: $it" }
    }
}

public suspend fun BiliClient.getLivingUser(
    page: Int = 1,
    pageSize: Int = 10,
    context: CoroutineContext = this.context,
): FeedLivingResponse = withContext(context) {
    logger.debug { "Getting Living User..." }
    client.get(FEED_LIVING_GET_URL) {
        parameter("page", page)
        parameter("pagesize", pageSize)
    }.body<String>().deserializeJson<FeedLivingResponse>().also {
        logger.debug { "Got Living User: $it" }
    }
}

public suspend fun BiliClient.getFeedUpdated(
    context: CoroutineContext = this.context,
): FeedUpdatedResponse = withContext(context) {
    logger.debug { "Getting Updated Feed info..." }
    client.get(FEED_UPDATED_GET_URL)
        .body<String>()
        .deserializeJson<FeedUpdatedResponse>()
        .also { logger.debug { "Got Feed Updated Response: $it" } }
}
