package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.BATCH_MODIFY_RELATION_URL
import moe.sdl.yabapi.consts.internal.BLACKLIST_GET_URL
import moe.sdl.yabapi.consts.internal.CO_FOLLOWING_GET_URL
import moe.sdl.yabapi.consts.internal.FANS_GET_URL
import moe.sdl.yabapi.consts.internal.FOLLOWING_GET_URL
import moe.sdl.yabapi.consts.internal.FOLLOWING_SEARCH_URL
import moe.sdl.yabapi.consts.internal.MODIFY_RELATION_URL
import moe.sdl.yabapi.consts.internal.QUIETLY_FOLLOWING_GET_URL
import moe.sdl.yabapi.consts.internal.RELATION_BATCH_QUERY_URL
import moe.sdl.yabapi.consts.internal.RELATION_QUERY_MUTUALLY
import moe.sdl.yabapi.consts.internal.RELATION_QUERY_SPECIAL
import moe.sdl.yabapi.consts.internal.RELATION_QUERY_URL
import moe.sdl.yabapi.data.relation.RelationBatchModifyResponse
import moe.sdl.yabapi.data.relation.RelationGetResponse
import moe.sdl.yabapi.data.relation.RelationModifyResponse
import moe.sdl.yabapi.data.relation.RelationQueryBatchResponse
import moe.sdl.yabapi.data.relation.RelationQueryMutuallyResponse
import moe.sdl.yabapi.data.relation.RelationQueryResponse
import moe.sdl.yabapi.data.relation.SpecialFollowingQueryResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.relation.FollowingOrder
import moe.sdl.yabapi.enums.relation.FollowingOrder.TIME
import moe.sdl.yabapi.enums.relation.RelationAction
import moe.sdl.yabapi.enums.relation.RelationAction.ADD_BLACKLIST
import moe.sdl.yabapi.enums.relation.SubscribeSource
import moe.sdl.yabapi.enums.relation.SubscribeSource.SPACE
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("RelationApi") }

/**
 * 獲取指定用戶的粉絲列表, 僅允許訪問前 250 位(前5頁)
 * @param mid 要查詢的用戶 mid
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.getFans(
    mid: Int,
    page: Int = 1,
    pageCount: Int = 50,
): RelationGetResponse = withContext(context) {
    logger.debug { "Getting fans of mid $mid, page $page for max $pageCount user(s)" }
    client.get(FANS_GET_URL) {
        parameter("vmid", mid)
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Got fans of mid $mid, page $page: $it" }
    }
}

/**
 * 獲取指定用戶的關注列表
 * @param mid 要查詢的用戶 mid
 * @param page 頁碼
 * @param pageCount 每頁多少項
 * @param order 順序 [FollowingOrder], 可選 [FollowingOrder.MOST_FREQUENT] 按訪問頻次排序
 */
public suspend fun BiliClient.getFollowing(
    mid: Int,
    page: Int = 1,
    pageCount: Int = 50,
    order: FollowingOrder = TIME,
): RelationGetResponse = withContext(context) {
    logger.debug { "Getting mid $mid's following, page $page for max $pageCount by $order..." }
    client.get(FOLLOWING_GET_URL) {
        parameter("vmid", mid)
        if (order == FollowingOrder.MOST_FREQUENT) {
            parameter("order_type", "attention")
        }
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Got followings of mid $mid, page $page: $it" }
    }
}

/**
 * 搜索某位用戶的關注列表, 似乎只能返回當前登錄的帳號
 * @param mid 要查詢的用戶 mid
 * @param keyword 搜索關鍵詞
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.searchFollowing(
    mid: Int,
    keyword: String,
    page: Int = 1,
    pageCount: Int = 50,
): RelationGetResponse = withContext(context) {
    logger.debug { "Searching Searched followings of mid $mid by keyword \"$keyword\"..." }
    client.get(FOLLOWING_SEARCH_URL) {
        parameter("vmid", mid)
        parameter("name", keyword)
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Searched followings of mid $mid by keyword \"$keyword\", page $page: $it" }
    }
}

/**
 * 獲取共同關注
 * @param mid 要查詢的用戶 mid
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.getCoFollowing(
    mid: Int,
    page: Int = 1,
    pageCount: Int = 50,
): RelationGetResponse = withContext(context) {
    logger.debug { "Getting co-followings of mid $mid..." }
    client.get(CO_FOLLOWING_GET_URL) {
        parameter("vmid", mid)
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Got co-followings of mid $mid, page $page: $it" }
    }
}

/**
 * 獲取悄悄關注, 顯然, 只能獲取當前用戶的
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.getQuietlyFollowing(
    page: Int = 1,
    pageCount: Int = 50,
): RelationGetResponse = withContext(context) {
    logger.debug { "Getting quietly following..." }
    client.get(QUIETLY_FOLLOWING_GET_URL) {
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Got quietly following: $it" }
    }
}

/**
 * 獲取黑名單明細
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.getBlacklist(
    page: Int = 1,
    pageCount: Int = 50,
): RelationGetResponse = withContext(context) {
    logger.debug { "Getting blacklist..." }
    client.get(BLACKLIST_GET_URL) {
        parameter("ps", pageCount)
        parameter("pn", page)
    }.body<String>().deserializeJson<RelationGetResponse>().also {
        logger.debug { "Got blacklist: $it" }
    }
}

/**
 * 操作用戶關係
 * @param mid 目標用戶 mid
 * @param action 操作 [RelationAction]
 * @param source 關注來源 [SubscribeSource]
 */
public suspend fun BiliClient.modifyRelation(
    mid: Int,
    action: RelationAction,
    source: SubscribeSource = SPACE,
    context: CoroutineContext = this.context,
): RelationModifyResponse = withContext(context) {
    logger.debug { "Modify relation for $mid, action: $action, with source $source" }
    client.post(MODIFY_RELATION_URL) {
        val params = Parameters.build {
            append("fid", mid.toString())
            append("act", action.code.toString())
            append("re_src", source.code.toString())
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson<RelationModifyResponse>().also {
        logger.debug { "Modified relation for $mid, action: $action, with source $source: $it" }
    }
}

@SharedImmutable
private val allowedBatchAction by lazy { listOf(RelationAction.SUB, ADD_BLACKLIST) }

/**
 * 操作用戶關係
 * @param mids 目標用戶 mid List
 * @param action 操作 [RelationAction], 必須是 [allowedBatchAction] 中的
 * @param source 關注來源 [SubscribeSource]
 */
public suspend fun BiliClient.modifyRelation(
    mids: IntArray,
    action: RelationAction,
    source: SubscribeSource = SPACE,
    context: CoroutineContext = this.context,
): RelationBatchModifyResponse = withContext(context) {
    require(allowedBatchAction.contains(action))
    logger.debug { "Modify relation for ${mids.contentToString()}, action: $action, with source $source" }
    client.post(BATCH_MODIFY_RELATION_URL) {
        val params = Parameters.build {
            append("fids", mids.joinToString(","))
            append("act", action.code.toString())
            append("re_src", source.code.toString())
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson<RelationBatchModifyResponse>().also {
        logger.debug { "Modified relation for $mids, action: $action, with source $source: $it" }
    }
}

/**
 * 查询和目标用户的单向关系
 * @param mid 目标用户 mid
 * @see RelationQueryResponse
 */
public suspend fun BiliClient.queryRelation(
    mid: Int,
    context: CoroutineContext = this.context,
): RelationQueryResponse = withContext(context) {
    logger.debug { "Querying relation to mid $mid..." }
    client.get(RELATION_QUERY_URL) {
        parameter("fid", mid)
    }.body<String>().deserializeJson<RelationQueryResponse>().also {
        logger.debug { "Queried relation to mid $mid: $it" }
    }
}

/**
 * 查询多个用户的关系
 * @param mid vararg 属性, 传入多个用户
 * @see RelationQueryBatchResponse
 */
public suspend fun BiliClient.queryRelation(
    vararg mid: Int,
    context: CoroutineContext = this.context,
): RelationQueryBatchResponse = withContext(context) {
    logger.debug { "Querying relation to mids ${mid.contentToString()}..." }
    client.get(RELATION_BATCH_QUERY_URL) {
        parameter("fids", mid.joinToString(","))
    }.body<String>().deserializeJson<RelationQueryBatchResponse>().also {
        logger.debug { "Queried relation to mids ${mid.contentToString()}: $it" }
    }
}

/**
 * 查询双向关系
 * @param mid 目标用户 mid
 * @see RelationQueryMutuallyResponse
 */
public suspend fun BiliClient.queryRelationMutually(
    mid: Int,
    context: CoroutineContext = this.context,
): RelationQueryMutuallyResponse = withContext(context) {
    logger.debug { "Querying relation mutually to mid $mid..." }
    client.get(RELATION_QUERY_MUTUALLY) {
        parameter("mid", mid)
    }.body<String>().deserializeJson<RelationQueryMutuallyResponse>().also {
        logger.debug { "Querying relation mutually to mid $mid..." }
    }
}

/**
 * 查询自身特别关注
 * @see SpecialFollowingQueryResponse
 */
public suspend fun BiliClient.querySpecialFollowing(
    context: CoroutineContext = this.context,
): SpecialFollowingQueryResponse = withContext(context) {
    logger.debug { "Querying special relation for current user..." }
    client.get(RELATION_QUERY_SPECIAL)
        .body<String>()
        .deserializeJson<SpecialFollowingQueryResponse>()
        .also { logger.debug { "Queried special relation: $it" } }
}
