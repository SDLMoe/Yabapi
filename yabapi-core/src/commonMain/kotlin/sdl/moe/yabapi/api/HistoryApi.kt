package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.HISTORY_CLEAR_URL
import sdl.moe.yabapi.consts.internal.HISTORY_DELETE_URL
import sdl.moe.yabapi.consts.internal.HISTORY_GET_URL
import sdl.moe.yabapi.consts.internal.HISTORY_STATUS_QUERY_URL
import sdl.moe.yabapi.consts.internal.HISTORY_STOP_URL
import sdl.moe.yabapi.data.history.HistoryDeleteResponse
import sdl.moe.yabapi.data.history.HistoryGetResponse
import sdl.moe.yabapi.data.history.HistoryStatusResponse
import sdl.moe.yabapi.data.history.HistoryStopResponse
import sdl.moe.yabapi.deserializeJson
import sdl.moe.yabapi.enums.history.HistoryType
import sdl.moe.yabapi.util.Logger
import sdl.moe.yabapi.util.requireXnorNullable
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("HistoryApi") }

/**
 * 獲取歷史記錄
 * @param fromId 特定目標id之後
 * @param idType 目標 id 類型
 * @param fromTime 特定時間點之後
 * @param pageCount 每頁項數
 */
public suspend fun BiliClient.getHistory(
    fromId: Int? = null,
    idType: HistoryType? = null,
    fromTime: Long = 0,
    pageCount: Int = 20,
    context: CoroutineContext = this.context,
): HistoryGetResponse = withContext(context) {
    requireXnorNullable(fromId, idType)
    logger.debug { "Getting history watched..." }
    client.get<String>(HISTORY_GET_URL) {
        if (fromId != null && idType != null) {
            parameter("max", fromId)
            parameter("business", idType.code)
        }
        parameter("view_at", fromTime)
        parameter("ps", pageCount)
    }.deserializeJson<HistoryGetResponse>().also {
        logger.debug { "Got history watched: $it" }
    }
}

public suspend fun BiliClient.deleteHistory(
    targetId: Int,
    type: HistoryType,
    context: CoroutineContext = this.context,
): HistoryDeleteResponse = withContext(context) {
    logger.debug { "Deleting Watch History for $type $targetId" }
    client.post<String>(HISTORY_DELETE_URL) {
        body = FormDataContent(Parameters.build {
            append("kid", "${type.code}_$targetId")
            append("jsonp", "jsonp")
            putCsrf()
        })
    }.deserializeJson<HistoryDeleteResponse>().also {
        logger.debug { "Delete History Response: $it" }
    }
}

public suspend fun BiliClient.clearHistory(
    context: CoroutineContext = this.context,
): HistoryDeleteResponse = withContext(context) {
    logger.debug { "Clearing Watch History..." }
    client.post<String>(HISTORY_CLEAR_URL) {
        body = FormDataContent(Parameters.build {
            putCsrf()
        })
    }.deserializeJson<HistoryDeleteResponse>().also {
        logger.debug { "Clear History Response: $it" }
    }
}

public suspend fun BiliClient.setStopHistory(
    isStop: Boolean = true,
    context: CoroutineContext = this.context,
): HistoryStopResponse = withContext(context) {
    logger.debug { "Setting History Stop Status to $isStop..." }
    client.post<String>(HISTORY_STOP_URL) {
        body = FormDataContent(Parameters.build {
            parameter("switch", isStop)
            putCsrf()
        })
    }.deserializeJson<HistoryStopResponse>().also {
        logger.debug { "Set History Stop Status Response: $it" }
    }
}

public suspend fun BiliClient.getHistoryStatus(
    context: CoroutineContext = this.context,
): HistoryStatusResponse = withContext(context){
    logger.debug { "Getting History Status..." }
    client.get<String>(HISTORY_STATUS_QUERY_URL)
        .deserializeJson<HistoryStatusResponse>().also {
            logger.debug { "Got History Status: $it" }
        }
}
