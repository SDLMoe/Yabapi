// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.video.VIDEO_INFO_GET_URL
import sdl.moe.yabapi.data.video.VideoInfoGetResponse
import sdl.moe.yabapi.util.logger

public object VideoApi : BiliApi {
    override val apiName: String = "video"

    init {
        BiliClient.registerApi(this)
    }

    private suspend fun BiliClient.getVideoInfo(
        aid: Int? = null,
        bid: String? = null,
    ): VideoInfoGetResponse = withContext(Platform.ioDispatcher) {
        require(aid != null || bid != null) { "Must at LEAST one not null in param [aid, bid]." }
        require(!(aid != null && bid != null)) { "Can pass ONLY one param in [aid, bid]." }
        client.get<String>(VIDEO_INFO_GET_URL) {
            aid?.let { parameter("aid", aid) }
            bid?.let { parameter("bvid", bid) }
        }.also(::println)
        client.get(VIDEO_INFO_GET_URL) {
            aid?.let { parameter("aid", aid) }
            bid?.let { parameter("bvid", bid) }
        }
    }

    public suspend fun BiliClient.getVideoInfo(aid: Int): VideoInfoGetResponse = run {
        logger.debug { "Getting video info for av$aid..." }
        this.getVideoInfo(aid, null).also {
            logger.debug { "Got video info for av$aid: $it" }
        }
    }

    public suspend fun BiliClient.getVideoInfo(bid: String): VideoInfoGetResponse = run {
        logger.debug { "Getting video info for $bid..." }
        this.getVideoInfo(null, bid).also {
            logger.debug { "Got video info for $bid: $it" }
        }
    }
}
