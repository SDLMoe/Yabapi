// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.video.VIDEO_DESCRIPTION_GET_URL
import sdl.moe.yabapi.consts.video.VIDEO_INFO_GET_URL
import sdl.moe.yabapi.consts.video.VIDEO_PARTS_GET_URL
import sdl.moe.yabapi.data.video.VideoDescriptionGetResponse
import sdl.moe.yabapi.data.video.VideoInfoGetResponse
import sdl.moe.yabapi.data.video.VideoPartsGetResponse
import sdl.moe.yabapi.util.logger

public object VideoApi : BiliApi {
    override val apiName: String = "video"

    init {
        BiliClient.registerApi(this)
    }

    private fun checkVideoId(aid: Int?, bid: String?) {
        require(aid != null || bid != null) { "Must at LEAST one not null in param [aid, bid]." }
        require(!(aid != null && bid != null)) { "Can pass ONLY one param in [aid, bid]." }
        if (bid != null) require(bid.startsWith(prefix = "bv", ignoreCase = true))
    }

    private fun HttpRequestBuilder.putVideoId(aid: Int?, bid: String?) {
        aid?.let { parameter("aid", aid) }
        bid?.let { parameter("bvid", bid) }
    }

    private suspend fun BiliClient.getVideoInfo(
        aid: Int? = null,
        bid: String? = null,
    ): VideoInfoGetResponse =
        withContext(Platform.ioDispatcher) {
            checkVideoId(aid, bid)
            client.get(VIDEO_INFO_GET_URL) {
                putVideoId(aid, bid)
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

    private suspend fun BiliClient.getVideoParts(
        aid: Int? = null,
        bid: String? = null,
    ): VideoPartsGetResponse = withContext(Platform.ioDispatcher) {
        checkVideoId(aid, bid)
        client.get(VIDEO_PARTS_GET_URL) {
            putVideoId(aid, bid)
        }
    }

    public suspend fun BiliClient.getVideoParts(aid: Int): VideoPartsGetResponse = run {
        logger.debug { "Getting video parts for av$aid" }
        getVideoParts(aid, null).also {
            logger.debug { "Got video parts for av$aid: $it" }
        }
    }

    public suspend fun BiliClient.getVideoParts(bid: String): VideoPartsGetResponse = run {
        logger.debug { "Getting video parts for $bid" }
        getVideoParts(null, bid).also {
            logger.debug { "Got video parts for $bid: $it" }
        }
    }

    private suspend fun BiliClient.getVideoDescription(
        aid: Int? = null,
        bid: String? = null,
    ): VideoDescriptionGetResponse = withContext(Platform.ioDispatcher) {
        checkVideoId(aid, bid)
        client.get(VIDEO_DESCRIPTION_GET_URL) {
            putVideoId(aid, bid)
        }
    }

    public suspend fun BiliClient.getVideoDescription(aid: Int): VideoDescriptionGetResponse = run {
        logger.debug { "Getting video description for av$aid" }
        getVideoDescription(aid, null).also {
            logger.debug { "Got video description for av$aid: $it" }
        }
    }

    public suspend fun BiliClient.getVideoDescription(bid: String): VideoDescriptionGetResponse = run {
        logger.debug { "Getting video description for $bid" }
        getVideoDescription(null, bid).also {
            logger.debug { "Got video description for $bid: $it" }
        }
    }
}
