package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.PGC_STREAM_FETCH_URL
import moe.sdl.yabapi.consts.internal.VIDEO_COIN_CHECK_URL
import moe.sdl.yabapi.consts.internal.VIDEO_COIN_URL
import moe.sdl.yabapi.consts.internal.VIDEO_COLLECT_ACTION_URL
import moe.sdl.yabapi.consts.internal.VIDEO_COLLECT_CHECK_URL
import moe.sdl.yabapi.consts.internal.VIDEO_COMBO_LIKE_URL
import moe.sdl.yabapi.consts.internal.VIDEO_DESCRIPTION_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_HAS_LIKE_URL
import moe.sdl.yabapi.consts.internal.VIDEO_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_INTERACTIVE_INFO_URL
import moe.sdl.yabapi.consts.internal.VIDEO_LIKE_URL
import moe.sdl.yabapi.consts.internal.VIDEO_ONLINE_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_PARTS_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_PLAYER_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_RELATED_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_REPORT_PROGRESS_URL
import moe.sdl.yabapi.consts.internal.VIDEO_SHARE_URL
import moe.sdl.yabapi.consts.internal.VIDEO_STREAM_FETCH_URL
import moe.sdl.yabapi.consts.internal.VIDEO_TAG_GET_URL
import moe.sdl.yabapi.consts.internal.VIDEO_TIMELINE_HOT_URL
import moe.sdl.yabapi.data.stream.PgcStreamResponse
import moe.sdl.yabapi.data.stream.StreamRequest
import moe.sdl.yabapi.data.stream.VideoStreamResponse
import moe.sdl.yabapi.data.video.CoinVideoResponse
import moe.sdl.yabapi.data.video.HasLikedResponse
import moe.sdl.yabapi.data.video.ReportWatchResponse
import moe.sdl.yabapi.data.video.ShareVideoResponse
import moe.sdl.yabapi.data.video.SubtitleContent
import moe.sdl.yabapi.data.video.TimelineHotResponse
import moe.sdl.yabapi.data.video.VideoCoinCheckResponse
import moe.sdl.yabapi.data.video.VideoCollectCheck
import moe.sdl.yabapi.data.video.VideoCollectResponse
import moe.sdl.yabapi.data.video.VideoComboLikeResponse
import moe.sdl.yabapi.data.video.VideoDescriptionGetResponse
import moe.sdl.yabapi.data.video.VideoInfoGetResponse
import moe.sdl.yabapi.data.video.VideoInteractiveResponse
import moe.sdl.yabapi.data.video.VideoLikeResponse
import moe.sdl.yabapi.data.video.VideoOnlineGetResponse
import moe.sdl.yabapi.data.video.VideoPartsGetResponse
import moe.sdl.yabapi.data.video.VideoPlayerInfoResponse
import moe.sdl.yabapi.data.video.VideoRelatedGetResponse
import moe.sdl.yabapi.data.video.VideoTagsGetResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.video.CollectAction
import moe.sdl.yabapi.enums.video.CollectAction.ADD
import moe.sdl.yabapi.enums.video.CollectAction.REMOVE
import moe.sdl.yabapi.enums.video.LikeAction
import moe.sdl.yabapi.enums.video.LikeAction.LIKE
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.encoding.av
import moe.sdl.yabapi.util.requireLeastAndOnlyOne
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("VideoApi") }

// region ==================== Util ====================

private fun checkVideoId(aid: Long?, bid: String?) {
    requireLeastAndOnlyOne(aid, bid)
    if (bid != null) require(bid.startsWith(prefix = "bv", ignoreCase = true))
}

internal fun HttpRequestBuilder.putVideoId(
    aid: Long?,
    bid: String?,
    aidKey: String = "aid",
    bidKey: String = "bvid",
) {
    checkVideoId(aid, bid)
    aid?.let { parameter(aidKey, aid) }
    bid?.let { parameter(bidKey, bid) }
}

internal fun ParametersBuilder.putVideoId(aid: Long?, bid: String?) {
    checkVideoId(aid, bid)
    aid?.let { append("aid", aid.toString()) }
    bid?.let { append("bvid", bid.toString()) }
}

private fun getActualId(aid: Long?, bid: String?): Number {
    checkVideoId(aid, bid)
    val actualId = bid?.av ?: aid
    requireNotNull(actualId)
    return actualId
}

// endregion

// region ==================== Info ====================

private suspend inline fun BiliClient.getVideoInfo(
    aid: Long? = null,
    bid: String? = null,
    cid: Long? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse = withContext(context) {
    client.get(VIDEO_INFO_GET_URL) {
        putVideoId(aid, bid)
        cid?.let { parameter("cid", cid) }
    }.body<String>().deserializeJson()
}

/**
 *  获取视频信息, 输入 av 号, 返回 [VideoInfoGetResponse]
 *  @see getVideoInfo
 *  @see VideoInfoGetResponse
 */
public suspend fun BiliClient.getVideoInfo(
    aid: Long,
    cid: Long? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for av$aid..." }
    return this.getVideoInfo(aid, null, cid, context).also {
        logger.debug { "Got video info for av$aid: $it" }
    }
}

/**
 * 获取视频信息, 输入 bv 号, 返回 [VideoInfoGetResponse]
 *  @see getVideoInfo
 *  @see VideoInfoGetResponse
 */
public suspend fun BiliClient.getVideoInfo(
    bid: String,
    cid: Long? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for $bid..." }
    return this.getVideoInfo(null, bid, cid, context).also {
        logger.debug { "Got video info for $bid: $it" }
    }
}

private suspend fun BiliClient.getVideoPlayerInfo(
    aid: Long?,
    bvid: String?,
    cid: Long,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse = withContext(context) {
    requireLeastAndOnlyOne(aid, bvid)
    client.get(VIDEO_PLAYER_INFO_GET_URL) {
        putVideoId(aid, bvid)
        parameter("cid", cid)
    }.body<String>().deserializeJson()
}

public suspend fun BiliClient.getVideoPlayerInfo(
    aid: Long,
    cid: Long,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse {
    logger.debug { "Getting VideoPlayerInfo for av$aid - $cid" }
    return getVideoPlayerInfo(aid, null, cid, context).also {
        logger.debug { "Got VideoPlayerInfo for av$aid - $cid: $it" }
    }
}

public suspend fun BiliClient.getVideoPlayerInfo(
    bvid: String,
    cid: Long,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse {
    logger.debug { "Getting VideoPlayerInfo for $bvid - $cid" }
    return getVideoPlayerInfo(null, bvid, cid, context).also {
        logger.debug { "Got VideoPlayerInfo for $bvid - $cid: $it" }
    }
}

private suspend inline fun BiliClient.getVideoParts(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoPartsGetResponse = withContext(context) {
    client.get(VIDEO_PARTS_GET_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频分P, 输入 av 号, 返回 [VideoPartsGetResponse]
 * @see getVideoParts
 * @see VideoPartsGetResponse
 */
public suspend fun BiliClient.getVideoParts(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoPartsGetResponse {
    logger.debug { "Getting video parts for av$aid" }
    return getVideoParts(aid, null, context).also {
        logger.debug { "Got video parts for av$aid: $it" }
    }
}

/**
 * 获取视频分P, 输入 bv 号, 返回 [VideoPartsGetResponse]
 * @see getVideoParts
 * @see VideoPartsGetResponse
 */
public suspend fun BiliClient.getVideoParts(
    bid: String,
    context: CoroutineContext = this.context,
): VideoPartsGetResponse {
    logger.debug { "Getting video parts for $bid" }
    return getVideoParts(null, bid, context).also {
        logger.debug { "Got video parts for $bid: $it" }
    }
}

private suspend inline fun BiliClient.getVideoDescription(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoDescriptionGetResponse = withContext(context) {
    client.get(VIDEO_DESCRIPTION_GET_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频简介, 输入 av 号, 返回 [VideoDescriptionGetResponse]
 * @see [getVideoDescription]
 * @see VideoDescriptionGetResponse
 */
public suspend fun BiliClient.getVideoDescription(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoDescriptionGetResponse {
    logger.debug { "Getting video description for av$aid" }
    return getVideoDescription(aid, null, context).also {
        logger.debug { "Got video description for av$aid: $it" }
    }
}

/**
 * 获取视频简介, 输入 bv 号, 返回 [VideoDescriptionGetResponse]
 * @see [getVideoDescription]
 * @see VideoDescriptionGetResponse
 */
public suspend fun BiliClient.getVideoDescription(
    bid: String,
    context: CoroutineContext = this.context,
): VideoDescriptionGetResponse {
    logger.debug { "Getting video description for $bid" }
    return getVideoDescription(null, bid, context).also {
        logger.debug { "Got video description for $bid: $it" }
    }
}

private suspend inline fun BiliClient.getVideoTags(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoTagsGetResponse = withContext(context) {
    client.get(VIDEO_TAG_GET_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频 Tags, 输入 av 号, 返回 [VideoTagsGetResponse]
 * @see getVideoTags
 * @see VideoTagsGetResponse
 */
public suspend fun BiliClient.getVideoTags(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoTagsGetResponse {
    logger.debug { "Getting Video Tags for av$aid" }
    return getVideoTags(aid, null, context).also {
        logger.debug { "Got Video Tags for av$aid: $it" }
    }
}

/**
 * 获取视频 Tags, 输入 bv 号, 返回 [VideoTagsGetResponse]
 * @see getVideoTags
 * @see VideoTagsGetResponse
 */
public suspend fun BiliClient.getVideoTags(
    bid: String,
    context: CoroutineContext = this.context,
): VideoTagsGetResponse {
    logger.debug { "Getting Video Tags for $bid" }
    return getVideoTags(null, bid, context).also {
        logger.debug { "Got Video Tags for $bid: $it" }
    }
}

// endregion

// region ==================== Interact & Check ====================

private suspend inline fun BiliClient.likeVideo(
    aid: Long? = null,
    bid: String? = null,
    action: LikeAction = LIKE,
    context: CoroutineContext = this.context,
): VideoLikeResponse = withContext(context) {
    client.post(VIDEO_LIKE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            append("like", action.code.toString())
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson()
}

/**
 * 点赞视频
 * @param aid avid
 * @param action [LikeAction]
 * @see VideoLikeResponse
 */
public suspend fun BiliClient.likeVideo(
    aid: Long,
    action: LikeAction = LIKE,
    context: CoroutineContext = this.context,
): VideoLikeResponse {
    logger.debug { "$action video av$aid" }
    return likeVideo(aid, null, action, context).also {
        logger.debug { "$action video av$aid response: $it" }
    }
}

/**
 * 点赞视频
 * @param bid bvid
 * @param action [LikeAction]
 * @see VideoLikeResponse
 */
public suspend fun BiliClient.likeVideo(
    bid: String,
    action: LikeAction = LIKE,
    context: CoroutineContext = this.context,
): VideoLikeResponse {
    logger.debug { "$action video $bid" }
    return likeVideo(null, bid, action, context).also {
        logger.debug { "$action video $bid response: $it" }
    }
}

private suspend inline fun BiliClient.checkVideoLike(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): HasLikedResponse = withContext(context) {
    client.get(VIDEO_HAS_LIKE_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 检查视频是否被点赞, 输入 bv 号, 返回 [HasLikedResponse]
 * @see checkVideoLike
 * @see HasLikedResponse
 */
public suspend fun BiliClient.checkVideoLike(
    aid: Long,
    context: CoroutineContext = this.context,
): HasLikedResponse {
    logger.debug { "Checking like status for av$aid" }
    return checkVideoLike(aid, null, context).also {
        logger.debug { "Get like status for av$aid: $it" }
    }
}

/**
 * 检查视频是否被点赞, 输入 bv 号, 返回 [HasLikedResponse]
 * @see checkVideoLike
 * @see HasLikedResponse
 */
public suspend fun BiliClient.checkVideoLike(
    bid: String,
    context: CoroutineContext = this.context,
): HasLikedResponse {
    logger.debug { "Checking like status for $bid" }
    return checkVideoLike(null, bid, context).also {
        logger.debug { "Get like status for $bid: $it" }
    }
}

private suspend inline fun BiliClient.coinVideo(
    aid: Long? = null,
    bid: String? = null,
    count: Int = 1,
    withLike: Boolean = false,
    context: CoroutineContext = this.context,
): CoinVideoResponse = withContext(context) {
    client.post(VIDEO_COIN_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            val actualCount = if (count >= 2) "2" else "1"
            append("multiply", actualCount)
            if (withLike) append("select_like", "1")
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson()
}

/**
 * 投币视频
 * @param aid av id
 * @param count 投币数量 in 1..2
 * @param withLike 是否同时点赞
 * @return [CoinVideoResponse]
 */
public suspend fun BiliClient.coinVideo(
    aid: Long? = null,
    count: Int = 1,
    withLike: Boolean = false,
    context: CoroutineContext = this.context,
): CoinVideoResponse {
    logger.debug { "Coin Video av$aid ${if (withLike) "with like" else ""}..." }
    return coinVideo(aid, null, count, withLike, context).also {
        logger.debug { "Coined video av$aid: $it" }
    }
}

/**
 * 投币视频
 * @param bid bv 号
 * @param count 投币数量 in 1..2
 * @param withLike 是否同时点赞
 * @return [CoinVideoResponse]
 */
public suspend fun BiliClient.coinVideo(
    bid: String? = null,
    count: Int = 1,
    withLike: Boolean = false,
    context: CoroutineContext = this.context,
): CoinVideoResponse {
    logger.debug { "Coin Video $bid ${if (withLike) "with like" else ""}..." }
    return coinVideo(null, bid, count, withLike, context).also {
        logger.debug { "Coined video $bid: $it" }
    }
}

private suspend inline fun BiliClient.checkVideoCoin(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoCoinCheckResponse = withContext(context) {
    client.get(VIDEO_COIN_CHECK_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 检查视频投币情况
 * @param aid av号
 * @return [VideoCoinCheckResponse]
 * @see checkVideoCoin
 */
public suspend fun BiliClient.checkVideoCoin(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoCoinCheckResponse {
    logger.debug { "Checking coin for video av$aid" }
    return checkVideoCoin(aid, null, context).also {
        logger.debug { "Checked coin for video av$aid: $it" }
    }
}

/**
 * 检查视频投币情况
 * @param bid bv 号
 * @return [VideoCoinCheckResponse]
 * @see checkVideoCoin
 */
public suspend fun BiliClient.checkVideoCoin(
    bid: String,
    context: CoroutineContext = this.context,
) {
    logger.debug { "Checking coin for video $bid" }
    checkVideoCoin(null, bid, context).also {
        logger.debug { "Checked coin for video $bid: $it" }
    }
}

private suspend inline fun BiliClient.collectVideo(
    aid: Long? = null,
    bid: String? = null,
    action: CollectAction = ADD,
    folderList: Collection<Long>,
    context: CoroutineContext = this.context,
): VideoCollectResponse = withContext(context) {
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
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson()
}

/**
 * 收藏视频
 * @param aid av 号
 * @param action 收藏操作, ADD / REMOVE
 * @param folderList 收藏夹id, 可能需要手动获取默认id
 */
public suspend fun BiliClient.collectVideo(
    aid: Long,
    action: CollectAction = ADD,
    folderList: Collection<Long>,
    context: CoroutineContext = this.context,
): VideoCollectResponse {
    logger.debug { "$action Collect video av$aid..." }
    return collectVideo(aid, null, action, folderList, context).also {
        logger.debug { "$action Collect video av$aid response: $it" }
    }
}

/**
 * 收藏视频
 * @param bid bv 号
 * @param action 收藏操作, ADD / REMOVE
 * @param folderList 收藏夹id, 可能需要手动获取默认id
 */
public suspend fun BiliClient.collectVideo(
    bid: String,
    action: CollectAction = ADD,
    folderList: Collection<Long>,
    context: CoroutineContext = this.context,
): VideoCollectResponse {
    logger.debug { "Collect video $bid..." }
    return collectVideo(null, bid, action, folderList, context).also {
        logger.debug { "$action Collect video $bid response: $it" }
    }
}

private suspend inline fun BiliClient.checkVideoCollect(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoCollectCheck = withContext(context) {
    client.get(VIDEO_COLLECT_CHECK_URL) {
        parameter("aid", getActualId(aid, bid))
    }.body<String>().deserializeJson()
}

/**
 * 检查视频收藏状态 输入 av 号, 返回 [VideoCollectCheck]
 */
public suspend fun BiliClient.checkVideoCollect(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoCollectCheck {
    logger.debug { "Checking video av$aid collect status..." }
    return checkVideoCollect(aid, null, context).also {
        logger.debug { "Checked video av$aid collect status: $it" }
    }
}

/**
 * 检查视频收藏状态 输入 bv 号, 返回 [VideoCollectCheck]
 */
public suspend fun BiliClient.checkVideoCollect(
    bid: String,
    context: CoroutineContext = this.context,
): VideoCollectCheck {
    logger.debug { "Checking video $bid collect status..." }
    return checkVideoCollect(null, bid, context).also {
        logger.debug { "Checked video $bid collect status: $it" }
    }
}

private suspend fun BiliClient.comboLike(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoComboLikeResponse = withContext(context) {
    client.post(VIDEO_COMBO_LIKE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson()
}

/**
 * 一键三连, 输入 aid, 返回 [VideoComboLikeResponse]
 */
public suspend fun BiliClient.comboLike(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoComboLikeResponse {
    logger.debug { "Combo liking video av$aid..." }
    return comboLike(aid, null, context).also {
        logger.debug { "Combo liked video av$aid: $it" }
    }
}

/**
 * 一键三连, 输入 bid, 返回 [VideoComboLikeResponse]
 */
public suspend fun BiliClient.comboLike(
    bid: String,
    context: CoroutineContext = this.context,
): VideoComboLikeResponse {
    logger.debug { "Combo liking video $bid..." }
    return comboLike(null, bid, context).also {
        logger.debug { "Combo liked video $bid: $it" }
    }
}

private suspend inline fun BiliClient.shareVideo(
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): ShareVideoResponse = withContext(context) {
    client.post(VIDEO_SHARE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        setBody(FormDataContent(params))
    }.body<String>().deserializeJson()
}

/**
 * 分享视频, 输入 av 号, 返回 [ShareVideoResponse]
 * 注意, 并不会返回分享链接, 只会增加视频数据
 */
public suspend fun BiliClient.shareVideo(
    aid: Long,
    context: CoroutineContext = this.context,
): ShareVideoResponse {
    logger.debug { "Sharing video av$aid..." }
    return shareVideo(aid, null, context).also {
        logger.debug { "Shared video av$aid: $it" }
    }
}

/**
 * 分享视频, 输入 bv 号, 返回 [ShareVideoResponse]
 * 注意, 并不会返回分享链接, 只会增加视频数据
 */
public suspend fun BiliClient.shareVideo(
    bid: String,
    context: CoroutineContext = this.context,
): ShareVideoResponse {
    logger.debug { "Sharing video $bid..." }
    return shareVideo(null, bid, context).also {
        logger.debug { "Shared video $bid: $it" }
    }
}

// endregion

// region ==================== Fetch Stream ====================

private suspend inline fun BiliClient.fetchVideoStream(
    aid: Long?,
    bid: String?,
    cid: Long,
    request: StreamRequest,
    context: CoroutineContext = this.context,
): VideoStreamResponse = withContext(context) {
    client.get(VIDEO_STREAM_FETCH_URL) {
        putVideoId(aid, bid, "avid")
        parameter("cid", cid)
        request.putParameter(this)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频流
 * @param aid av 号
 * @param cid 分p id
 * @param request 请求内容
 * @return 返回 VideoStreamResponse
 * @see StreamRequest
 */
public suspend fun BiliClient.fetchVideoStream(
    aid: Long,
    cid: Long,
    request: StreamRequest = StreamRequest(),
    context: CoroutineContext = this.context,
): VideoStreamResponse {
    logger.debug { "Fetching Video Stream for av$aid..." }
    return fetchVideoStream(aid, null, cid, request, context).also {
        logger.debug { "Fetched video stream for av$aid: $it" }
    }
}

/**
 * 获取视频流
 * @param bid bv 号
 * @param cid 分p id
 * @param request 请求内容
 * @return 返回 VideoStreamResponse
 * @see StreamRequest
 */
public suspend fun BiliClient.fetchVideoStream(
    bid: String,
    cid: Long,
    request: StreamRequest = StreamRequest(),
    context: CoroutineContext = this.context,
): VideoStreamResponse {
    logger.debug { "Fetching Video Stream for $bid..." }
    return fetchVideoStream(null, bid, cid, request, context).also {
        logger.debug { "Fetched video stream for $bid: $it" }
    }
}

public suspend fun BiliClient.fetchPgcStream(
    ep: Long,
    request: StreamRequest = StreamRequest(),
    context: CoroutineContext = this.context,
): PgcStreamResponse = withContext(context) {
    logger.debug { "Fetching Pgc Stream for ep$ep..." }
    client.get(PGC_STREAM_FETCH_URL) {
        parameter("ep_id", ep)
        request.putParameter(this)
    }.body<String>().deserializeJson<PgcStreamResponse>().also {
        logger.debug { "Fetched Pgc Stream for ep$ep..." }
    }
}

// endregion

// region ==================== Hot ====================

/**
 * 获取视频时间线热度
 */
private suspend inline fun BiliClient.getTimelineHot(
    cid: Long,
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): TimelineHotResponse = withContext(context) {
    client.get(VIDEO_TIMELINE_HOT_URL) {
        parameter("cid", cid)
        if (aid != null || bid != null) putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频分p 高能进度条
 * @param cid 分 p 号码
 * @return [TimelineHotResponse]
 */
public suspend fun BiliClient.getTimelineHot(
    cid: Long,
    context: CoroutineContext = this.context,
): TimelineHotResponse {
    logger.debug { "Getting Timeline Hot for cid $cid..." }
    return getTimelineHot(cid, null, null, context).also {
        logger.debug { "Got timeline hot for cid $cid: $it" }
    }
}

/**
 * 获取视频分p 高能进度条
 * @param cid 分 p 号码
 * @param aid av 号
 * @return [TimelineHotResponse]
 */
public suspend fun BiliClient.getTimelineHot(
    cid: Long,
    aid: Long,
    context: CoroutineContext = this.context,
): TimelineHotResponse = run {
    logger.debug { "Getting Timeline Hot for cid $cid (av$aid)..." }
    getTimelineHot(cid, aid, null, context).also {
        logger.debug { "Got Timeline Hot for cid $cid (av$aid): $it" }
    }
}

/**
 * 获取视频分p 高能进度条
 * @param cid 分 p 号码
 * @param bid bv 号
 * @return [TimelineHotResponse]
 */
public suspend fun BiliClient.getTimelineHot(
    cid: Long,
    bid: String,
    context: CoroutineContext = this.context,
): TimelineHotResponse = run {
    logger.debug { "Getting Timeline Hot for cid $cid ($bid)..." }
    getTimelineHot(cid, null, bid, context).also {
        logger.debug { "Got Timeline Hot for cid $cid ($bid): $it" }
    }
}

private suspend inline fun BiliClient.getVideoOnline(
    cid: Long,
    aid: Long? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoOnlineGetResponse = withContext(context) {
    client.get(VIDEO_ONLINE_GET_URL) {
        putVideoId(aid, bid)
        parameter("cid", cid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频在线人数
 * @param aid av 号
 * @param cid 分 p 号
 * @return [VideoOnlineGetResponse]
 */
public suspend fun BiliClient.getVideoOnline(
    aid: Long,
    cid: Long,
    context: CoroutineContext = this.context,
): VideoOnlineGetResponse {
    logger.debug { "Getting Video Online for av$aid cid $cid..." }
    return getVideoOnline(cid, aid, null, context).also {
        logger.debug { "Got Video Online for av$aid cid $cid: $it" }
    }
}

/**
 * 获取视频在线人数
 * @param bid bv 号
 * @param cid 分 p 号
 * @return [VideoOnlineGetResponse]
 */
public suspend fun BiliClient.getVideoOnline(
    bid: String,
    cid: Long,
    context: CoroutineContext = this.context,
): VideoOnlineGetResponse {
    logger.debug { "Getting Video Online for $bid cid $cid..." }
    return getVideoOnline(cid, null, bid, context).also {
        logger.debug { "Got Video Online for $bid cid $cid: $it" }
    }
}

// endregion

// region ==================== Recommended ====================

private suspend inline fun BiliClient.getVideoRelated(
    aid: Long?,
    bid: String?,
    context: CoroutineContext = this.context,
): VideoRelatedGetResponse = withContext(context) {
    client.get(VIDEO_RELATED_GET_URL) {
        putVideoId(aid, bid)
    }.body<String>().deserializeJson()
}

/**
 * 获取视频的相关推荐, 输入 av 号, 返回 [VideoRelatedGetResponse]
 */
public suspend fun BiliClient.getVideoRelated(
    aid: Long,
    context: CoroutineContext = this.context,
): VideoRelatedGetResponse {
    logger.debug { "Getting Related Video for av$aid" }
    return getVideoRelated(aid, null, context).also {
        logger.debug { "Got Related Video for av$aid: $it" }
    }
}

/**
 * 获取视频的相关推荐, 输入 bv 号, 返回 [VideoRelatedGetResponse]
 */
public suspend fun BiliClient.getVideoRelated(
    bid: String,
    context: CoroutineContext = this.context,
): VideoRelatedGetResponse {
    logger.debug { "Getting Related Video for $bid" }
    return getVideoRelated(null, bid, context).also {
        logger.debug { "Got Related Video for $bid: $it" }
    }
}

// endregion

// region ==================== Report ====================

/**
 * 上报观看进度
 * @param aid av号
 * @param cid 分p id
 * @param progress 进度, 单位秒
 */
public suspend fun BiliClient.reportVideoProgress(
    aid: Long,
    cid: Long,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = withContext(context) {
    logger.debug { "Reporting Video Watch Progress for av$aid, cid$cid." }
    client.post(VIDEO_REPORT_PROGRESS_URL) {
        setBody(
            FormDataContent(
                Parameters.build {
                    append("aid", aid.toString())
                    append("cid", cid.toString())
                    append("progress", progress.toString())
                    putCsrf()
                }
            )
        )
    }.body<String>().deserializeJson<ReportWatchResponse>().also {
        logger.debug { "Reported Video Watch Progress for av$aid, cid$cid: $it" }
    }
}

/**
 * 上报观看进度
 * @param bid bv号
 * @param cid 分p id
 * @param progress 进度, 单位秒
 */
public suspend inline fun BiliClient.reportVideoProgress(
    bid: String,
    cid: Long,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = reportVideoProgress(bid.av, cid, progress, context)

// endregion

// region ==================== Subtitle ====================

public suspend fun BiliClient.getSubtitleContent(
    url: String,
    context: CoroutineContext = this.context,
): SubtitleContent = withContext(context) {
    logger.debug { "Getting subtitle at $url" }
    client.get(url).body<String>().deserializeJson<SubtitleContent>().also {
        logger.debug { "Got subtitle content from $url: $it" }
    }
}

// endregion

// region ==================== Interactive Video ====================

/**
 * @param bvid video id
 * @param graphVersion, get from [getVideoPlayerInfo], [VideoPlayerInfoResponse] resp.data.interactionInfo
 * @param edgeId, get from choices of [VideoInteractiveResponse]
 */
public suspend fun BiliClient.getInteractiveInfo(
    bvid: String,
    graphVersion: Int,
    edgeId: Long? = null,
    platform: String = "pc",
    context: CoroutineContext = this.context,
): VideoInteractiveResponse = withContext(context) {
    logger.debug { "Try to get interactive video info for $bvid" }
    client.get(VIDEO_INTERACTIVE_INFO_URL) {
        parameter("bvid", bvid)
        parameter("graph_version", graphVersion)
        edgeId?.let { parameter("edge_id", edgeId) }
        parameter("platform", platform)
    }.body<String>().deserializeJson<VideoInteractiveResponse>().also {
        logger.debug { "Got interactive video info for $bvid graph $graphVersion edge $edgeId: $it" }
    }
}

// endregion
