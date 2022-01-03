// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.video.VIDEO_COIN_CHECK_URL
import sdl.moe.yabapi.consts.video.VIDEO_COIN_URL
import sdl.moe.yabapi.consts.video.VIDEO_COLLECT_ACTION_URL
import sdl.moe.yabapi.consts.video.VIDEO_COLLECT_CHECK_URL
import sdl.moe.yabapi.consts.video.VIDEO_COMBO_LIKE_URL
import sdl.moe.yabapi.consts.video.VIDEO_DESCRIPTION_GET_URL
import sdl.moe.yabapi.consts.video.VIDEO_HAS_LIKE_URL
import sdl.moe.yabapi.consts.video.VIDEO_INFO_GET_URL
import sdl.moe.yabapi.consts.video.VIDEO_LIKE_URL
import sdl.moe.yabapi.consts.video.VIDEO_PARTS_GET_URL
import sdl.moe.yabapi.consts.video.VIDEO_SHARE_URL
import sdl.moe.yabapi.consts.video.VIDEO_STREAM_FETCH_URL
import sdl.moe.yabapi.data.stream.StreamRequest
import sdl.moe.yabapi.data.stream.VideoStreamResponse
import sdl.moe.yabapi.data.video.CoinVideoResponse
import sdl.moe.yabapi.data.video.HasLikedResponse
import sdl.moe.yabapi.data.video.ShareVideoResponse
import sdl.moe.yabapi.data.video.VideoCoinCheckResponse
import sdl.moe.yabapi.data.video.VideoCollectCheck
import sdl.moe.yabapi.data.video.VideoCollectResponse
import sdl.moe.yabapi.data.video.VideoComboLikeResponse
import sdl.moe.yabapi.data.video.VideoDescriptionGetResponse
import sdl.moe.yabapi.data.video.VideoInfoGetResponse
import sdl.moe.yabapi.data.video.VideoLikeResponse
import sdl.moe.yabapi.data.video.VideoPartsGetResponse
import sdl.moe.yabapi.enums.video.CollectAction
import sdl.moe.yabapi.enums.video.CollectAction.ADD
import sdl.moe.yabapi.enums.video.CollectAction.REMOVE
import sdl.moe.yabapi.enums.video.LikeAction
import sdl.moe.yabapi.enums.video.LikeAction.LIKE
import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.util.avInt
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
        checkVideoId(aid, bid)
        aid?.let { parameter("aid", aid) }
        bid?.let { parameter("bvid", bid) }
    }

    private fun ParametersBuilder.putVideoId(aid: Int?, bid: String?) {
        checkVideoId(aid, bid)
        aid?.let { append("aid", aid.toString()) }
        bid?.let { append("bvid", bid.toString()) }
    }

    private fun getActualId(aid: Int?, bid: String?): Int {
        checkVideoId(aid, bid)
        val actualId = bid?.avInt ?: aid
        requireNotNull(actualId)
        return actualId
    }

    private suspend inline fun BiliClient.getVideoInfo(
        aid: Int? = null,
        bid: String? = null,
    ): VideoInfoGetResponse = withContext(Platform.ioDispatcher) {
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

    private suspend inline fun BiliClient.getVideoParts(
        aid: Int? = null,
        bid: String? = null,
    ): VideoPartsGetResponse = withContext(Platform.ioDispatcher) {
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

    private suspend inline fun BiliClient.getVideoDescription(
        aid: Int? = null,
        bid: String? = null,
    ): VideoDescriptionGetResponse = withContext(Platform.ioDispatcher) {
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

    private suspend inline fun BiliClient.likeVideo(
        aid: Int? = null,
        bid: String? = null,
        action: LikeAction = LIKE,
    ): VideoLikeResponse = withContext(Platform.ioDispatcher) {
        client.post(VIDEO_LIKE_URL) {
            val params = Parameters.build {
                putVideoId(aid, bid)
                append("like", action.code.toString())
                putCsrf()
            }
            body = FormDataContent(params)
        }
    }

    /**
     * @param aid avid
     * @param action [LikeAction]
     */
    public suspend fun BiliClient.likeVideo(
        aid: Int,
        action: LikeAction = LIKE,
    ): VideoLikeResponse = run {
        logger.debug { "$action video av$aid" }
        likeVideo(aid, null, action).also {
            logger.debug { "$action video av$aid response: $it" }
        }
    }

    /**
     * @param bid bvid
     * @param action [LikeAction]
     */
    public suspend fun BiliClient.likeVideo(
        bid: String,
        action: LikeAction = LIKE,
    ): VideoLikeResponse = run {
        logger.debug { "$action video $bid" }
        likeVideo(null, bid, action).also {
            logger.debug { "$action video $bid response: $it" }
        }
    }

    private suspend inline fun BiliClient.checkVideoLike(
        aid: Int? = null,
        bid: String? = null,
    ): HasLikedResponse = withContext(Platform.ioDispatcher) {
        client.get(VIDEO_HAS_LIKE_URL) {
            putVideoId(aid, bid)
        }
    }

    public suspend fun BiliClient.checkVideoLike(aid: Int) {
        logger.debug { "Checking like status for av$aid" }
        checkVideoLike(aid, null).also {
            logger.debug { "Get like status for av$aid: $it" }
        }
    }

    public suspend fun BiliClient.checkVideoLike(bid: String) {
        logger.debug { "Checking like status for $bid" }
        checkVideoLike(null, bid).also {
            logger.debug { "Get like status for $bid: $it" }
        }
    }

    private suspend inline fun BiliClient.coinVideo(
        aid: Int? = null,
        bid: String? = null,
        count: Int = 1,
        withLike: Boolean = false,
    ): CoinVideoResponse = withContext(Platform.ioDispatcher) {
        client.post(VIDEO_COIN_URL) {
            val params = Parameters.build {
                putVideoId(aid, bid)
                append("multiply", count.toString())
                if (withLike) append("select_like", "1")
                putCsrf()
            }
            body = FormDataContent(params)
        }
    }

    public suspend fun BiliClient.coinVideo(
        aid: Int? = null,
        count: Int = 1,
        withLike: Boolean = false,
    ): CoinVideoResponse = run {
        logger.debug { "Coin Video av$aid ${if (withLike) "with like" else ""}..." }
        coinVideo(aid, null, count, withLike).also {
            logger.debug { "Coined video av$aid: $it" }
        }
    }

    public suspend fun BiliClient.coinVideo(
        bid: String? = null,
        count: Int = 1,
        withLike: Boolean = false,
    ): CoinVideoResponse = run {
        logger.debug { "Coin Video $bid ${if (withLike) "with like" else ""}..." }
        coinVideo(null, bid, count, withLike).also {
            logger.debug { "Coined video $bid: $it" }
        }
    }

    private suspend inline fun BiliClient.checkVideoCoin(
        aid: Int? = null,
        bid: String? = null,
    ): VideoCoinCheckResponse = withContext(Platform.ioDispatcher) {
        client.get(VIDEO_COIN_CHECK_URL) {
            putVideoId(aid, bid)
        }
    }

    public suspend fun BiliClient.checkVideoCoin(aid: Int): VideoCoinCheckResponse = run {
        logger.debug { "Checking coin for video av$aid" }
        checkVideoCoin(aid, null).also {
            logger.debug { "Checked coin for video av$aid: $it" }
        }
    }

    public suspend fun BiliClient.checkVideoCoin(bid: String) {
        logger.debug { "Checking coin for video $bid" }
        checkVideoCoin(null, bid).also {
            logger.debug { "Checked coin for video $bid: $it" }
        }
    }

    private suspend inline fun BiliClient.collectVideo(
        aid: Int? = null,
        bid: String? = null,
        action: CollectAction = ADD,
        folderList: Collection<Int>,
    ): VideoCollectResponse = withContext(Platform.ioDispatcher) {
        client.post(VIDEO_COLLECT_ACTION_URL) {
            headers {
                header(HttpHeaders.Referrer, "https://www.bilibili.com/")
            }
            val params = Parameters.build {
                append("rid", getActualId(aid, bid).toString())
                append("type", "2")
                val mediaKey = when (action) {
                    ADD -> "add_media_ids"
                    REMOVE -> "del_media_ids"
                }
                if (folderList.isNotEmpty()) {
                    append(mediaKey, folderList.joinToString(","))
                }
                putCsrf()
            }
            body = FormDataContent(params)
        }
    }

    public suspend fun BiliClient.collectVideo(
        aid: Int,
        action: CollectAction = ADD,
        folderList: Collection<Int>,
    ): VideoCollectResponse = run {
        logger.debug { "$action Collect video av$aid..." }
        collectVideo(aid, null, action, folderList).also {
            logger.debug { "$action Collect video av$aid response: $it" }
        }
    }

    public suspend fun BiliClient.collectVideo(
        bid: String,
        action: CollectAction = ADD,
        folderList: Collection<Int>,
    ): VideoCollectResponse = run {
        logger.debug { "Collect video $bid..." }
        collectVideo(null, bid, action, folderList).also {
            logger.debug { "$action Collect video $bid response: $it" }
        }
    }

    private suspend inline fun BiliClient.checkVideoCollect(
        aid: Int? = null,
        bid: String? = null,
    ): VideoCollectCheck = withContext(Platform.ioDispatcher) {
        client.get(VIDEO_COLLECT_CHECK_URL) {
            parameter("aid", getActualId(aid, bid))
        }
    }

    public suspend fun BiliClient.checkVideoCollect(aid: Int) {
        logger.debug { "Checking video av$aid collect status..." }
        checkVideoCollect(aid, null).also {
            logger.debug { "Checked video av$aid collect status: $it" }
        }
    }

    public suspend fun BiliClient.checkVideoCollect(bid: String) {
        logger.debug { "Checking video $bid collect status..." }
        checkVideoCollect(null, bid).also {
            logger.debug { "Checked video $bid collect status: $it" }
        }
    }

    private suspend fun BiliClient.comboLike(
        aid: Int? = null,
        bid: String? = null,
    ): VideoComboLikeResponse = withContext(Platform.ioDispatcher) {
        client.post(VIDEO_COMBO_LIKE_URL) {
            val params = Parameters.build {
                putVideoId(aid, bid)
                putCsrf()
            }
            body = FormDataContent(params)
        }
    }

    public suspend fun BiliClient.comboLike(aid: Int): VideoComboLikeResponse = run {
        logger.debug { "Combo liking video av$aid..." }
        comboLike(aid, null).also {
            logger.debug { "Combo liked video av$aid: $it" }
        }
    }

    public suspend fun BiliClient.comboLike(bid: String): VideoComboLikeResponse = run {
        logger.debug { "Combo liking video $bid..." }
        comboLike(null, bid).also {
            logger.debug { "Combo liked video $bid: $it" }
        }
    }

    private suspend inline fun BiliClient.shareVideo(
        aid: Int? = null,
        bid: String? = null,
    ): ShareVideoResponse = withContext(Platform.ioDispatcher) {
        client.post(VIDEO_SHARE_URL) {
            val params = Parameters.build {
                putVideoId(aid, bid)
                putCsrf()
            }
            body = FormDataContent(params)
        }
    }

    public suspend fun BiliClient.shareVideo(aid: Int): ShareVideoResponse = run {
        logger.debug { "Sharing video av$aid..." }
        shareVideo(aid, null).also {
            logger.debug { "Shared video av$aid: $it" }
        }
    }

    public suspend fun BiliClient.shareVideo(bid: String): ShareVideoResponse = run {
        logger.debug { "Sharing video $bid..." }
        shareVideo(null, bid).also {
            logger.debug { "Shared video $bid: $it" }
        }
    }

    private suspend inline fun BiliClient.fetchVideoStream(
        aid: Int?,
        bid: String?,
        cid: Int,
        request: StreamRequest,
    ): VideoStreamResponse =
        withContext(Platform.ioDispatcher) {
            client.get(VIDEO_STREAM_FETCH_URL) {
                putVideoId(aid, bid)
                parameter("cid", cid)
                if (request.fnvalFormat.format != DASH) parameter("qn", request.qnQuality.code)
                parameter("fnval", request.fnvalFormat.toBinary())
                parameter("fnver", "0")
                parameter("fourk", request.fourk)
            }
        }

    public suspend fun BiliClient.fetchVideoStream(
        aid: Int,
        cid: Int,
        request: StreamRequest = StreamRequest(),
    ): VideoStreamResponse = run {
        logger.debug { "Fetching Video Stream for av$aid..." }
        fetchVideoStream(aid, null, cid, request).also {
            logger.debug { "Fetched video stream for av$aid: $it" }
        }
    }

    public suspend fun BiliClient.fetchVideoStream(
        bid: String,
        cid: Int,
        request: StreamRequest = StreamRequest(),
    ): VideoStreamResponse = run {
        logger.debug { "Fetching Video Stream for $bid..." }
        fetchVideoStream(null, bid, cid, request).also {
            logger.debug { "Fetched video stream for $bid: $it" }
        }
    }
}
