package moe.sdl.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.SEARCH_ALL_URL
import moe.sdl.yabapi.consts.internal.SEARCH_BY_TYPE_URL
import moe.sdl.yabapi.data.search.SearchAllResponse
import moe.sdl.yabapi.data.search.SearchLiveResponse
import moe.sdl.yabapi.data.search.SearchNormalResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.search.SearchType
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("SearchApi") }

public suspend fun BiliClient.searchAll(
    keyword: String,
    context: CoroutineContext = this.context,
): SearchAllResponse = withContext(context) {
    logger.debug { "Search for '$keyword'..." }
    client.get<String>(SEARCH_ALL_URL) {
        parameter("keyword", keyword)
    }.deserializeJson<SearchAllResponse>().also {
        logger.debug { "Search Reponse for '$keyword': $it" }
    }
}

/**
 * 适用于非直播的搜索
 *
 * 若要搜索直播相关请使用 [searchLive]
 */
public suspend fun BiliClient.searchByType(
    keyword: String,
    type: SearchType,
    context: CoroutineContext = this.context,
): SearchNormalResponse = withContext(context) {
    logger.debug { "Searching '$keyword' by type $type..." }
    require(type != SearchType.LIVE || type != SearchType.LIVE_USER) { "For live related search, use [BiliClient.searchLive] instead." }
    client.get<String>(SEARCH_BY_TYPE_URL) {
        parameter("search_type", type.code)
        parameter("keyword", keyword)
    }.deserializeJson<SearchNormalResponse>().also {
        logger.debug { "Search '$keyword' by type $type Response: $it" }
    }
}

public suspend fun BiliClient.searchLive(
    keyword: String,
    type: SearchType,
    context: CoroutineContext = this.context,
): SearchLiveResponse = withContext(context) {
    logger.debug { "Searching '$keyword' by type $type..." }
    require(type == SearchType.LIVE || type == SearchType.LIVE_USER) { "For non-live search, use [BiliClient.searchByType] instead" }
    client.get<String>(SEARCH_BY_TYPE_URL) {
        parameter("search_type", type.code)
        parameter("keyword", keyword)
    }.deserializeJson<SearchLiveResponse>().also {
        logger.debug { "Search '$keyword' by type $type Response: $it" }
    }
}
