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
import sdl.moe.yabapi.consts.internal.VIDEO_COIN_CHECK_URL
import sdl.moe.yabapi.consts.internal.VIDEO_COIN_URL
import sdl.moe.yabapi.consts.internal.VIDEO_COLLECT_ACTION_URL
import sdl.moe.yabapi.consts.internal.VIDEO_COLLECT_CHECK_URL
import sdl.moe.yabapi.consts.internal.VIDEO_COMBO_LIKE_URL
import sdl.moe.yabapi.consts.internal.VIDEO_DESCRIPTION_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_HAS_LIKE_URL
import sdl.moe.yabapi.consts.internal.VIDEO_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_LIKE_URL
import sdl.moe.yabapi.consts.internal.VIDEO_ONLINE_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_PARTS_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_RELATED_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_REPORT_PROGRESS_URL
import sdl.moe.yabapi.consts.internal.VIDEO_SHARE_URL
import sdl.moe.yabapi.consts.internal.VIDEO_STREAM_FETCH_URL
import sdl.moe.yabapi.consts.internal.VIDEO_TAG_GET_URL
import sdl.moe.yabapi.consts.internal.VIDEO_TIMELINE_HOT_URL
import sdl.moe.yabapi.data.stream.StreamRequest
import sdl.moe.yabapi.data.stream.VideoStreamResponse
import sdl.moe.yabapi.data.video.CoinVideoResponse
import sdl.moe.yabapi.data.video.HasLikedResponse
import sdl.moe.yabapi.data.video.ReportWatchResponse
import sdl.moe.yabapi.data.video.ShareVideoResponse
import sdl.moe.yabapi.data.video.TimelineHotResponse
import sdl.moe.yabapi.data.video.VideoCoinCheckResponse
import sdl.moe.yabapi.data.video.VideoCollectCheck
import sdl.moe.yabapi.data.video.VideoCollectResponse
import sdl.moe.yabapi.data.video.VideoComboLikeResponse
import sdl.moe.yabapi.data.video.VideoDescriptionGetResponse
import sdl.moe.yabapi.data.video.VideoInfoGetResponse
import sdl.moe.yabapi.data.video.VideoLikeResponse
import sdl.moe.yabapi.data.video.VideoOnlineGetResponse
import sdl.moe.yabapi.data.video.VideoPartsGetResponse
import sdl.moe.yabapi.data.video.VideoRelatedGetResponse
import sdl.moe.yabapi.data.video.VideoTagsGetResponse
import sdl.moe.yabapi.enums.video.CollectAction
import sdl.moe.yabapi.enums.video.CollectAction.ADD
import sdl.moe.yabapi.enums.video.CollectAction.REMOVE
import sdl.moe.yabapi.enums.video.LikeAction
import sdl.moe.yabapi.enums.video.LikeAction.LIKE
import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.util.Logger
import sdl.moe.yabapi.util.avInt
import sdl.moe.yabapi.util.requireLeastAndOnlyOne
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("VideoApi")

// region ==================== Util ====================

private fun checkVideoId(aid: Int?, bid: String?) {
    requireLeastAndOnlyOne(aid, bid)
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

// endregion

// region ==================== Info ====================

private suspend inline fun BiliClient.getVideoInfo(
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse = withContext(context) {
    client.get(VIDEO_INFO_GET_URL) {
        putVideoId(aid, bid)
    }
}

/**
 *  获取视频信息, 输入 av 号, 返回 [VideoInfoGetResponse]
 *  @see getVideoInfo
 *  @see VideoInfoGetResponse
 */
public suspend fun BiliClient.getVideoInfo(
    aid: Int,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for av$aid..." }
    return this.getVideoInfo(aid, null, context).also {
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
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for $bid..." }
    return this.getVideoInfo(null, bid, context).also {
        logger.debug { "Got video info for $bid: $it" }
    }
}

private suspend inline fun BiliClient.getVideoParts(
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoPartsGetResponse = withContext(context) {
    client.get(VIDEO_PARTS_GET_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 获取视频分P, 输入 av 号, 返回 [VideoPartsGetResponse]
 * @see getVideoParts
 * @see VideoPartsGetResponse
 */
public suspend fun BiliClient.getVideoParts(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoDescriptionGetResponse = withContext(context) {
    client.get(VIDEO_DESCRIPTION_GET_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 获取视频简介, 输入 av 号, 返回 [VideoDescriptionGetResponse]
 * @see [getVideoDescription]
 * @see VideoDescriptionGetResponse
 */
public suspend fun BiliClient.getVideoDescription(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoTagsGetResponse = withContext(context) {
    client.get(VIDEO_TAG_GET_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 获取视频 Tags, 输入 av 号, 返回 [VideoTagsGetResponse]
 * @see getVideoTags
 * @see VideoTagsGetResponse
 */
public suspend fun BiliClient.getVideoTags(
    aid: Int,
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
    aid: Int? = null,
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
        body = FormDataContent(params)
    }
}

/**
 * 点赞视频
 * @param aid avid
 * @param action [LikeAction]
 * @see VideoLikeResponse
 */
public suspend fun BiliClient.likeVideo(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): HasLikedResponse = withContext(context) {
    client.get(VIDEO_HAS_LIKE_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 检查视频是否被点赞, 输入 bv 号, 返回 [HasLikedResponse]
 * @see checkVideoLike
 * @see HasLikedResponse
 */
public suspend fun BiliClient.checkVideoLike(
    aid: Int,
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
    aid: Int? = null,
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
        body = FormDataContent(params)
    }
}

/**
 * 投币视频
 * @param aid av id
 * @param count 投币数量 in 1..2
 * @param withLike 是否同时点赞
 * @return [CoinVideoResponse]
 */
public suspend fun BiliClient.coinVideo(
    aid: Int? = null,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoCoinCheckResponse = withContext(context) {
    client.get(VIDEO_COIN_CHECK_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 检查视频投币情况
 * @param aid av号
 * @return [VideoCoinCheckResponse]
 * @see checkVideoCoin
 */
public suspend fun BiliClient.checkVideoCoin(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    action: CollectAction = ADD,
    folderList: Collection<Int>,
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
        body = FormDataContent(params)
    }
}

/**
 * 收藏视频
 * @param aid av 号
 * @param action 收藏操作, ADD / REMOVE
 * @param folderList 收藏夹id, 可能需要手动获取默认id
 */
public suspend fun BiliClient.collectVideo(
    aid: Int,
    action: CollectAction = ADD,
    folderList: Collection<Int>,
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
    folderList: Collection<Int>,
    context: CoroutineContext = this.context,
): VideoCollectResponse {
    logger.debug { "Collect video $bid..." }
    return collectVideo(null, bid, action, folderList, context).also {
        logger.debug { "$action Collect video $bid response: $it" }
    }
}

private suspend inline fun BiliClient.checkVideoCollect(
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoCollectCheck = withContext(context) {
    client.get(VIDEO_COLLECT_CHECK_URL) {
        parameter("aid", getActualId(aid, bid))
    }
}

/**
 * 检查视频收藏状态 输入 av 号, 返回 [VideoCollectCheck]
 */
public suspend fun BiliClient.checkVideoCollect(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoComboLikeResponse = withContext(context) {
    client.post(VIDEO_COMBO_LIKE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        body = FormDataContent(params)
    }
}

/**
 * 一键三连, 输入 aid, 返回 [VideoComboLikeResponse]
 */
public suspend fun BiliClient.comboLike(
    aid: Int,
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
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): ShareVideoResponse = withContext(context) {
    client.post(VIDEO_SHARE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        body = FormDataContent(params)
    }
}

/**
 * 分享视频, 输入 av 号, 返回 [ShareVideoResponse]
 * 注意, 并不会返回分享链接, 只会增加视频数据
 */
public suspend fun BiliClient.shareVideo(
    aid: Int,
    context: CoroutineContext = this.context,
): ShareVideoResponse = run {
    logger.debug { "Sharing video av$aid..." }
    shareVideo(aid, null, context).also {
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
    aid: Int?,
    bid: String?,
    cid: Int,
    request: StreamRequest,
    context: CoroutineContext = this.context,
): VideoStreamResponse =
    withContext(context) {
        client.get(VIDEO_STREAM_FETCH_URL) {
            putVideoId(aid, bid)
            parameter("cid", cid)
            if (request.fnvalFormat.format != DASH) parameter("qn", request.qnQuality.code)
            parameter("fnval", request.fnvalFormat.toBinary())
            parameter("fnver", "0")
            parameter("fourk", request.fourk)
        }
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
    aid: Int,
    cid: Int,
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
    cid: Int,
    request: StreamRequest = StreamRequest(),
    context: CoroutineContext = this.context,
): VideoStreamResponse {
    logger.debug { "Fetching Video Stream for $bid..." }
    return fetchVideoStream(null, bid, cid, request, context).also {
        logger.debug { "Fetched video stream for $bid: $it" }
    }
}

// endregion

// region ==================== Hot ====================

/**
 * 获取视频时间线热度
 */
private suspend inline fun BiliClient.getTimelineHot(
    cid: Int,
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): TimelineHotResponse = withContext(context) {
    client.get(VIDEO_TIMELINE_HOT_URL) {
        parameter("cid", cid)
        if (aid != null || bid != null) putVideoId(aid, bid)
    }
}

/**
 * 获取视频分p 高能进度条
 * @param cid 分 p 号码
 * @return [TimelineHotResponse]
 */
public suspend fun BiliClient.getTimelineHot(
    cid: Int,
    context: CoroutineContext = this.context,
): TimelineHotResponse = run {
    logger.debug { "Getting Timeline Hot for cid $cid..." }
    getTimelineHot(cid, null, null, context).also {
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
    cid: Int,
    aid: Int,
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
    cid: Int,
    bid: String,
    context: CoroutineContext = this.context,
): TimelineHotResponse = run {
    logger.debug { "Getting Timeline Hot for cid $cid ($bid)..." }
    getTimelineHot(cid, null, bid, context).also {
        logger.debug { "Got Timeline Hot for cid $cid ($bid): $it" }
    }
}

private suspend inline fun BiliClient.getVideoOnline(
    cid: Int,
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoOnlineGetResponse = withContext(context) {
    client.get(VIDEO_ONLINE_GET_URL) {
        putVideoId(aid, bid)
        parameter("cid", cid)
    }
}

/**
 * 获取视频在线人数
 * @param aid av 号
 * @param cid 分 p 号
 * @return [VideoOnlineGetResponse]
 */
public suspend fun BiliClient.getVideoOnline(
    aid: Int,
    cid: Int,
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
    cid: Int,
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
    aid: Int?,
    bid: String?,
    context: CoroutineContext = this.context,
): VideoRelatedGetResponse = withContext(context) {
    client.get(VIDEO_RELATED_GET_URL) {
        putVideoId(aid, bid)
    }
}

/**
 * 获取视频的相关推荐, 输入 av 号, 返回 [VideoRelatedGetResponse]
 */
public suspend fun BiliClient.getVideoRelated(
    aid: Int,
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
    aid: Int,
    cid: Int,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = withContext(context) {
    logger.debug { "Reporting Video Watch Progress for av$aid, cid$cid." }
    client.post<ReportWatchResponse>(VIDEO_REPORT_PROGRESS_URL) {
        body = FormDataContent(Parameters.build {
            append("aid", aid.toString())
            append("cid", cid.toString())
            append("progress", progress.toString())
            putCsrf()
        })
    }.also {
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
    cid: Int,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = reportVideoProgress(bid.avInt, cid, progress, context)

// endregion
