package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.internal.ARTICLE_BASIC_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.ARTICLE_COLLECTION_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.ARTICLE_PAGE_URL
import sdl.moe.yabapi.data.article.ArticleCollectionInfoResponse
import sdl.moe.yabapi.data.article.ArticleDetailedData
import sdl.moe.yabapi.data.article.ArticleInfoGetResponse
import sdl.moe.yabapi.deserializeJson
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("ArticleApi") }

public suspend fun BiliClient.getArticleInfo(
    cvId: Int,
    context: CoroutineContext = Platform.ioDispatcher
): ArticleInfoGetResponse = withContext(context) {
    logger.debug { "Getting article info cv$cvId..." }
    client.get<String>(ARTICLE_BASIC_INFO_GET_URL) {
        parameter("id", cvId)
    }.deserializeJson<ArticleInfoGetResponse>().also {
        println("Got article info cv$cvId: $it")
    }
}

private val articleDataRegex = Regex("""window.__INITIAL_STATE__=(\{[\S\s\r\n]+});""")

public suspend fun BiliClient.getArticleDetailed(
    cvId: Int,
    context: CoroutineContext = Platform.ioDispatcher,
): ArticleDetailedData? = withContext(context) {
    logger.debug { "Trying to capture article detail data for cv$cvId" }
    client.get<String>("$ARTICLE_PAGE_URL/cv$cvId").let {
        articleDataRegex.find(it)?.groups?.get(1)?.value
    }.also {
        logger.debug { "Captured Article Detailed Raw Data: $it" }
    }?.deserializeJson<ArticleDetailedData>().also {
        logger.debug { "Parsed Article Detailed Data cv$cvId: $it" }
    }
}

public suspend fun BiliClient.getCollectionInfo(
    id: Int,
    context: CoroutineContext = Platform.ioDispatcher
): ArticleCollectionInfoResponse = withContext(context) {
    logger.debug { "Getting Article Collection Info id$id" }
    client.get<String>(ARTICLE_COLLECTION_INFO_GET_URL) {
        parameter("id", id)
    }.deserializeJson<ArticleCollectionInfoResponse>().also {
        logger.debug { "Got Article Collection Info id$id: $it" }
    }
}