package moe.sdl.yabapi.api

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.core.Input
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.ALBUM_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.ALBUM_UPLOAD_URL
import moe.sdl.yabapi.consts.internal.FEED_DOMAIN
import moe.sdl.yabapi.data.album.AlbumCategory
import moe.sdl.yabapi.data.album.AlbumCategory.DAILY
import moe.sdl.yabapi.data.album.AlbumInfoResponse
import moe.sdl.yabapi.data.album.AlbumUploadResponse
import moe.sdl.yabapi.data.album.AlbumUploadResponseCode.SUCCESS
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.string.quoteList
import moe.sdl.yabapi.util.string.toHeaderValue
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("AlbumApi") }

/**
 * 獲取相簿信息
 *
 * 此爲和 h.bilibili.com 相關的 Api, 請勿與主站 `相簿` 功能混淆
 * @param id 相簿id
 */
public suspend fun BiliClient.getVcAlbumInfo(
    id: Int,
    context: CoroutineContext = this.context,
): AlbumInfoResponse = withContext(context) {
    logger.debug { "Getting Album Info $id..." }
    client.get<String>(ALBUM_INFO_GET_URL) {
        parameter("doc_id", id)
    }.deserializeJson<AlbumInfoResponse>().also {
        logger.debug { "Got Album Info $id: $it" }
    }
}

public suspend fun BiliClient.uploadImage(
    category: AlbumCategory = DAILY,
    context: CoroutineContext = this.context,
    fileName: String = quoteList.random(),
    inputProvider: () -> Input,
): AlbumUploadResponse = withContext(context) {
    logger.debug { "Trying to upload " }
    client.post<String>(ALBUM_UPLOAD_URL) {
        headers {
            header(HttpHeaders.Origin, FEED_DOMAIN)
            header(HttpHeaders.Referrer, FEED_DOMAIN)
        }
        body = MultiPartFormDataContent(
            formData {
                appendInput(
                    "file_up",
                    Headers.build {
                        append(HttpHeaders.ContentType, ContentType.Image.Any.toHeaderValue())
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    },
                    block = inputProvider
                )
                append("category", category.code)
            }
        )
    }.deserializeJson<AlbumUploadResponse>().also {
        if (it.code == SUCCESS) logger.debug { "Successfully upload img: $it" }
        else logger.debug { "Failed to upload img: $it" }
    }
}
