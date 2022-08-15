package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.HISTORY_CLEAR_URL
import moe.sdl.yabapi.consts.internal.HISTORY_DELETE_URL
import moe.sdl.yabapi.consts.internal.HISTORY_GET_URL
import moe.sdl.yabapi.consts.internal.HISTORY_STATUS_QUERY_URL
import moe.sdl.yabapi.consts.internal.HISTORY_STOP_URL
import moe.sdl.yabapi.consts.internal.LATER_WATCH_ADD_CHANNEL_URL
import moe.sdl.yabapi.consts.internal.LATER_WATCH_ADD_URL
import moe.sdl.yabapi.consts.internal.LATER_WATCH_CLEAR_URL
import moe.sdl.yabapi.consts.internal.LATER_WATCH_DELETE_URL
import moe.sdl.yabapi.consts.internal.LATER_WATCH_GET_URL
import moe.sdl.yabapi.consts.internal.WWW
import moe.sdl.yabapi.data.history.HistoryDeleteResponse
import moe.sdl.yabapi.data.history.HistoryGetResponse
import moe.sdl.yabapi.data.history.HistoryStatusResponse
import moe.sdl.yabapi.data.history.HistoryStopResponse
import moe.sdl.yabapi.data.history.LaterWatchAddChannelResponse
import moe.sdl.yabapi.data.history.LaterWatchAddResponse
import moe.sdl.yabapi.data.history.LaterWatchClearResponse
import moe.sdl.yabapi.data.history.LaterWatchDeleteResponse
import moe.sdl.yabapi.data.history.LaterWatchGetResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.history.HistoryType
import moe.sdl.yabapi.enums.history.HistoryType.ARTICLE
import moe.sdl.yabapi.enums.history.HistoryType.LIVE
import moe.sdl.yabapi.enums.history.HistoryType.VIDEO
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.encoding.avInt
import moe.sdl.yabapi.util.requireXnorNullable
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("HistoryApi") }

/**
 * 獲取歷史記錄
 *
 * [filterType] 是对 **历史数据类型** 的限制,
 * 已知可传入的有效值为 [VIDEO] | [LIVE] | [ARTICLE]
 *
 * [fromId] 及 [idType] 是对 **获取范围** 的限制,
 * 两个必须同时传入或同时不传入(即 XNOR 可空性), 否则会抛出 [IllegalArgumentException]
 *
 * [fromTime] 也是对 **获取范围** 的限制, 获取某个时间点之后的历史记录,
 * 需传入 UNIX 时间戳 (精确到秒)
 *
 * [pageCount] 为请求获取的个数
 *
 * @param filterType 过滤请求
 * @param fromId 特定目標 id 之後
 * @param idType 目標 id 類型
 * @param fromTime 特定時間點之後
 * @param pageCount 每頁項數
 */
public suspend fun BiliClient.getHistory(
    filterType: HistoryType? = null,
    fromId: Int? = null,
    idType: HistoryType? = null,
    fromTime: Long = 0,
    pageCount: Int = 20,
    context: CoroutineContext = this.context,
): HistoryGetResponse = withContext(context) {
    requireXnorNullable(fromId, idType)
    logger.debug { "Getting history watched..." }
    client.get(HISTORY_GET_URL) {
        filterType?.let { parameter("type", filterType) }
        if (fromId != null && idType != null) {
            parameter("max", fromId)
            parameter("business", idType.code)
        }
        parameter("view_at", fromTime)
        parameter("ps", pageCount)
    }.body<String>().deserializeJson<HistoryGetResponse>().also {
        logger.debug { "Got history watched: $it" }
    }
}

public suspend fun BiliClient.deleteHistory(
    targetId: Int,
    type: HistoryType,
    context: CoroutineContext = this.context,
): HistoryDeleteResponse = withContext(context) {
    logger.debug { "Deleting Watch History for $type $targetId" }
    client.post(HISTORY_DELETE_URL) {
        setBody(FormDataContent(
            Parameters.build {
                append("kid", "${type.code}_$targetId")
                append("jsonp", "jsonp")
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<HistoryDeleteResponse>().also {
        logger.debug { "Delete History Response: $it" }
    }
}

public suspend fun BiliClient.clearHistory(
    context: CoroutineContext = this.context,
): HistoryDeleteResponse = withContext(context) {
    logger.debug { "Clearing Watch History..." }
    client.post(HISTORY_CLEAR_URL) {
        setBody(FormDataContent(
            Parameters.build {
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<HistoryDeleteResponse>().also {
        logger.debug { "Clear History Response: $it" }
    }
}

public suspend fun BiliClient.setStopHistory(
    isStop: Boolean = true,
    context: CoroutineContext = this.context,
): HistoryStopResponse = withContext(context) {
    logger.debug { "Setting History Stop Status to $isStop..." }
    client.post(HISTORY_STOP_URL) {
        setBody(FormDataContent(
            Parameters.build {
                parameter("switch", isStop)
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<HistoryStopResponse>().also {
        logger.debug { "Set History Stop Status Response: $it" }
    }
}

public suspend fun BiliClient.getHistoryStatus(
    context: CoroutineContext = this.context,
): HistoryStatusResponse = withContext(context) {
    logger.debug { "Getting History Status..." }
    client.get(HISTORY_STATUS_QUERY_URL)
        .body<String>()
        .deserializeJson<HistoryStatusResponse>().also {
            logger.debug { "Got History Status: $it" }
        }
}

public suspend fun BiliClient.getLaterWatch(
    context: CoroutineContext = this.context,
): LaterWatchGetResponse = withContext(context) {
    logger.debug { "Getting Later Watch List" }
    client.get(LATER_WATCH_GET_URL)
        .body<String>()
        .deserializeJson<LaterWatchGetResponse>()
        .also { logger.debug { "Got Later Watch List: $it" } }
}

private suspend fun BiliClient.addLaterWatch(
    aid: Int?,
    bid: String?,
    context: CoroutineContext = this.context,
): LaterWatchAddResponse = withContext(context) {
    client.post(LATER_WATCH_ADD_URL) {
        setBody(FormDataContent(
            Parameters.build {
                putVideoId(aid, bid)
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson()
}

public suspend fun BiliClient.addLaterWatch(
    aid: Int,
    context: CoroutineContext = this.context,
): LaterWatchAddResponse {
    logger.debug { "Adding Later Watch For av$aid..." }
    return addLaterWatch(aid, null, context).also {
        logger.debug { "Add Later Watch For av$aid Response: $it" }
    }
}

public suspend fun BiliClient.addLaterWatch(
    bid: String,
    context: CoroutineContext = this.context,
): LaterWatchAddResponse {
    logger.debug { "Adding Later Watch For $bid..." }
    return addLaterWatch(null, bid, context).also {
        logger.debug { "Add Later Watch For $bid Response: $it" }
    }
}

public suspend fun BiliClient.addChannelToLaterWatch(
    collectionId: Int,
    targetUid: Int,
    context: CoroutineContext = this.context,
): LaterWatchAddChannelResponse = withContext(context) {
    logger.debug { "Adding channel $collectionId mid $targetUid to later watching..." }
    client.post(LATER_WATCH_ADD_CHANNEL_URL) {
        headers {
            append(HttpHeaders.Referrer, WWW)
        }
        setBody(FormDataContent(
            Parameters.build {
                append("cid", collectionId.toString())
                append("mid", targetUid.toString())
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<LaterWatchAddChannelResponse>().also {
        logger.debug { "Add channel $collectionId mid $targetUid to later watching response: $it" }
    }
}

private suspend fun BiliClient.deleteLaterWatchRaw(
    avToDelete: Int? = null,
    deleteViewed: Boolean = false,
    context: CoroutineContext = this.context,
) = withContext(context) {
    logger.debug { "Deleting LaterWatch for av$avToDelete${if (deleteViewed) " and viewed" else ""}..." }
    client.post(LATER_WATCH_DELETE_URL) {
        setBody(FormDataContent(
            Parameters.build {
                avToDelete?.let { append("aid", it.toString()) }
                append("viewed", deleteViewed.toString())
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<LaterWatchDeleteResponse>().also {
        logger.debug { "Delete Later Watch Response: $it" }
    }
}

public suspend fun BiliClient.deleteLaterWatch(
    avToDelete: Int,
    deleteViewed: Boolean = false,
    context: CoroutineContext = this.context,
): LaterWatchDeleteResponse = deleteLaterWatchRaw(avToDelete, deleteViewed, context)

public suspend fun BiliClient.deleteLaterWatch(
    bvToDelete: String,
    deleteViewed: Boolean = false,
    context: CoroutineContext = this.context,
): LaterWatchDeleteResponse = deleteLaterWatchRaw(bvToDelete.avInt, deleteViewed, context)

public suspend fun BiliClient.deleteViewedLaterWatch(
    context: CoroutineContext = this.context,
): LaterWatchDeleteResponse = deleteLaterWatchRaw(null, true, context)

public suspend fun BiliClient.clearLaterWatch(
    context: CoroutineContext = this.context,
): LaterWatchClearResponse = withContext(context) {
    logger.debug { "Clearing Later Watch..." }
    client.post(LATER_WATCH_CLEAR_URL) {
        setBody(FormDataContent(
            Parameters.build {
                putCsrf()
            }
        ))
    }.body<String>().deserializeJson<LaterWatchClearResponse>().also {
        logger.debug { "Clear Later Watch Response: $it" }
    }
}
