package moe.sdl.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.SEARCH_ALL_URL
import moe.sdl.yabapi.data.search.SearchAllResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("SearchApi") }

public suspend fun BiliClient.getWebSearchDefault(
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
