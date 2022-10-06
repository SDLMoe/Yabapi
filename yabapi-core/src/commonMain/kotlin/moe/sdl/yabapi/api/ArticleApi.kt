package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.Platform
import moe.sdl.yabapi.consts.internal.ARTICLE_BASIC_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.ARTICLE_PAGE_URL
import moe.sdl.yabapi.consts.internal.ARTICLE_SET_INFO_GET_URL
import moe.sdl.yabapi.data.article.ArticleDetailedData
import moe.sdl.yabapi.data.article.ArticleInfoGetResponse
import moe.sdl.yabapi.data.article.ArticleSetInfoResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.string.findInitialState
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("ArticleApi") }

public suspend fun BiliClient.getArticleInfo(
    cvId: Long,
    context: CoroutineContext = Platform.ioDispatcher,
): ArticleInfoGetResponse = withContext(context) {
    logger.debug { "Getting article info cv$cvId..." }
    client.get(ARTICLE_BASIC_INFO_GET_URL) {
        parameter("id", cvId)
    }.body<String>().deserializeJson<ArticleInfoGetResponse>().also {
        println("Got article info cv$cvId: $it")
    }
}

public suspend fun BiliClient.getArticleDetailed(
    cvId: Long,
    context: CoroutineContext = Platform.ioDispatcher,
): ArticleDetailedData? = withContext(context) {
    logger.debug { "Trying to capture article detail data for cv$cvId" }
    client.get("$ARTICLE_PAGE_URL/cv$cvId").body<String>()
        .findInitialState().also {
            logger.debug { "Captured Article Detailed Raw Data: $it" }
        }?.deserializeJson<ArticleDetailedData>().also {
            logger.debug { "Parsed Article Detailed Data cv$cvId: $it" }
        }
}

public suspend fun BiliClient.getArticleSetInfo(
    id: Long,
    context: CoroutineContext = Platform.ioDispatcher,
): ArticleSetInfoResponse = withContext(context) {
    logger.debug { "Getting Article Set Info id$id" }
    client.get(ARTICLE_SET_INFO_GET_URL) {
        parameter("id", id)
    }.body<String>().deserializeJson<ArticleSetInfoResponse>().also {
        logger.debug { "Got Article Set Info id$id: $it" }
    }
}
