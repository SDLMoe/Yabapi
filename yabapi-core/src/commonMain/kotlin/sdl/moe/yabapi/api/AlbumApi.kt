package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.ALBUM_INFO_GET_URL
import sdl.moe.yabapi.data.album.AlbumInfoResponse
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("AlbumApi")

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
    client.get<AlbumInfoResponse>(ALBUM_INFO_GET_URL) {
        parameter("doc_id", id)
    }.also {
        logger.debug { "Got Album Info $id: $it" }
    }
}
