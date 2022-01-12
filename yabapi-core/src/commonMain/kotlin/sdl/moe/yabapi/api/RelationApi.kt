// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.Parameters
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.BATCH_MODIFY_RELATION_URL
import sdl.moe.yabapi.consts.internal.BLACKLIST_GET_URL
import sdl.moe.yabapi.consts.internal.CO_FOLLOWING_GET_URL
import sdl.moe.yabapi.consts.internal.FANS_GET_URL
import sdl.moe.yabapi.consts.internal.FOLLOWING_GET_URL
import sdl.moe.yabapi.consts.internal.FOLLOWING_SEARCH_URL
import sdl.moe.yabapi.consts.internal.MODIFY_RELATION_URL
import sdl.moe.yabapi.consts.internal.QUIETLY_FOLLOWING_GET_URL
import sdl.moe.yabapi.consts.internal.RELATION_BATCH_QUERY_URL
import sdl.moe.yabapi.consts.internal.RELATION_QUERY_MUTUALLY
import sdl.moe.yabapi.consts.internal.RELATION_QUERY_SPECIAL
import sdl.moe.yabapi.consts.internal.RELATION_QUERY_URL
import sdl.moe.yabapi.data.relation.RelationBatchModifyResponse
import sdl.moe.yabapi.data.relation.RelationGetResponse
import sdl.moe.yabapi.data.relation.RelationModifyResponse
import sdl.moe.yabapi.data.relation.RelationQueryBatchResponse
import sdl.moe.yabapi.data.relation.RelationQueryMutuallyResponse
import sdl.moe.yabapi.data.relation.RelationQueryResponse
import sdl.moe.yabapi.data.relation.SpecialFollowingQueryResponse
import sdl.moe.yabapi.enums.relation.FollowingOrder
import sdl.moe.yabapi.enums.relation.FollowingOrder.TIME
import sdl.moe.yabapi.enums.relation.RelationAction
import sdl.moe.yabapi.enums.relation.RelationAction.ADD_BLACKLIST
import sdl.moe.yabapi.enums.relation.SubscribeSource
import sdl.moe.yabapi.enums.relation.SubscribeSource.SPACE
import sdl.moe.yabapi.util.Logger

private val logger = Logger("RelationApi")

/**
 * 獲取指定用戶的粉絲列表, 僅允許訪問前 250 位(前5頁)
 * @param mid 要查詢的用戶 mid
 * @param page 頁碼
 * @param pageCount 每頁多少項
 */
public suspend fun BiliClient.getFans(mid: Int, page: Int = 1, pageCount: Int = 50): RelationGetResponse =
    withContext(context) {
        logger.debug { "Getting fans of mid $mid, page $page for max $pageCount user(s)" }
        client.get<RelationGetResponse>(FANS_GET_URL) {
            parameter("vmid", mid)
            parameter("ps", pageCount)
            parameter("pn", page)
        }.also {
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
): RelationGetResponse =
    withContext(context) {
        logger.debug { "Getting mid $mid's following, page $page for max $pageCount by $order..." }
        client.get<RelationGetResponse>(FOLLOWING_GET_URL) {
            parameter("vmid", mid)
            if (order == FollowingOrder.MOST_FREQUENT) {
                parameter("order_type", "attention")
            }
            parameter("ps", pageCount)
            parameter("pn", page)
        }.also {
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
    client.get<RelationGetResponse>(FOLLOWING_SEARCH_URL) {
        parameter("vmid", mid)
        parameter("name", keyword)
        parameter("ps", pageCount)
        parameter("pn", page)
    }.also {
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
    needLogin()
    logger.debug { "Getting co-followings of mid $mid..." }
    client.get<RelationGetResponse>(CO_FOLLOWING_GET_URL) {
        parameter("vmid", mid)
        parameter("ps", pageCount)
        parameter("pn", page)
    }.also {
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
    needLogin()
    logger.debug { "Getting quietly following..." }
    client.get<RelationGetResponse>(QUIETLY_FOLLOWING_GET_URL) {
        parameter("ps", pageCount)
        parameter("pn", page)
    }.also {
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
    needLogin()
    logger.debug { "Getting blacklist..." }
    client.get<RelationGetResponse>(BLACKLIST_GET_URL) {
        parameter("ps", pageCount)
        parameter("pn", page)
    }.also {
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
): RelationModifyResponse = withContext(context) {
    needLogin()
    logger.debug { "Modify relation for $mid, action: $action, with source $source" }
    client.post<RelationModifyResponse>(MODIFY_RELATION_URL) {
        val params = Parameters.build {
            append("fid", mid.toString())
            append("act", action.code.toString())
            append("re_src", source.code.toString())
            putCsrf()
        }
        body = FormDataContent(params)
    }.also {
        logger.debug { "Modified relation for $mid, action: $action, with source $source: $it" }
    }
}

private val allowedBatchAction = listOf(RelationAction.SUB, ADD_BLACKLIST)

/**
 * 操作用戶關係
 * @param mids 目標用戶 mid List
 * @param action 操作 [RelationAction]
 * @param source 關注來源 [SubscribeSource]
 */
public suspend fun BiliClient.modifyRelation(
    mids: List<Int>,
    action: RelationAction,
    source: SubscribeSource = SPACE,
): RelationBatchModifyResponse = withContext(context) {
    needLogin()
    require(allowedBatchAction.contains(action))
    logger.debug { "Modify relation for $mids, action: $action, with source $source" }
    client.post<RelationBatchModifyResponse>(BATCH_MODIFY_RELATION_URL) {
        val params = Parameters.build {
            append("fids", mids.joinToString(","))
            append("act", action.code.toString())
            append("re_src", source.code.toString())
            putCsrf()
        }
        body = FormDataContent(params)
    }.also {
        logger.debug { "Modified relation for $mids, action: $action, with source $source: $it" }
    }
}

public suspend fun BiliClient.queryRelation(mid: Int): RelationQueryResponse = withContext(context) {
    logger.debug { "Querying relation to mid $mid..." }
    client.get<RelationQueryResponse>(RELATION_QUERY_URL) {
        parameter("fid", mid)
    }.also {
        logger.debug { "Queried relation to mid $mid: $it" }
    }
}

public suspend fun BiliClient.queryRelation(vararg mid: Int): RelationQueryBatchResponse =
    withContext(context) {
        logger.debug { "Querying relation to mids ${mid.contentToString()}..." }
        client.get<RelationQueryBatchResponse>(RELATION_BATCH_QUERY_URL) {
            parameter("fids", mid.joinToString(","))
        }.also {
            logger.debug { "Queried relation to mids ${mid.contentToString()}: $it" }
        }
    }

public suspend fun BiliClient.queryRelationMutually(mid: Int): RelationQueryMutuallyResponse =
    withContext(context) {
        logger.debug { "Querying relation mutually to mid $mid..." }
        client.get<RelationQueryMutuallyResponse>(RELATION_QUERY_MUTUALLY) {
            parameter("mid", mid)
        }.also {
            logger.debug { "Querying relation mutually to mid $mid..." }
        }
    }

public suspend fun BiliClient.querySpecialFollowing(): SpecialFollowingQueryResponse =
    withContext(context) {
        logger.debug { "Querying special relation for current user..." }
        client.get<SpecialFollowingQueryResponse>(RELATION_QUERY_SPECIAL).also {
            logger.debug { "Queried special relation: $it" }
        }
    }
