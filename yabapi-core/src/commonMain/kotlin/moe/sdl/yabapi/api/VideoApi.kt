package moe.sdl.yabapi.api

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
import moe.sdl.yabapi.util.encoding.avInt
import moe.sdl.yabapi.util.requireLeastAndOnlyOne
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("VideoApi") }

// region ==================== Util ====================

private fun checkVideoId(aid: Int?, bid: String?) {
    requireLeastAndOnlyOne(aid, bid)
    if (bid != null) require(bid.startsWith(prefix = "bv", ignoreCase = true))
}

internal fun HttpRequestBuilder.putVideoId(
    aid: Int?,
    bid: String?,
    aidKey: String = "aid",
    bidKey: String = "bvid",
) {
    checkVideoId(aid, bid)
    aid?.let { parameter(aidKey, aid) }
    bid?.let { parameter(bidKey, bid) }
}

internal fun ParametersBuilder.putVideoId(aid: Int?, bid: String?) {
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
    cid: Int? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse = withContext(context) {
    client.get<String>(VIDEO_INFO_GET_URL) {
        putVideoId(aid, bid)
        cid?.let { parameter("cid", cid) }
    }.deserializeJson()
}

/**
 *  ??????????????????, ?????? av ???, ?????? [VideoInfoGetResponse]
 *  @see getVideoInfo
 *  @see VideoInfoGetResponse
 */
public suspend fun BiliClient.getVideoInfo(
    aid: Int,
    cid: Int? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for av$aid..." }
    return this.getVideoInfo(aid, null, cid, context).also {
        logger.debug { "Got video info for av$aid: $it" }
    }
}

/**
 * ??????????????????, ?????? bv ???, ?????? [VideoInfoGetResponse]
 *  @see getVideoInfo
 *  @see VideoInfoGetResponse
 */
public suspend fun BiliClient.getVideoInfo(
    bid: String,
    cid: Int? = null,
    context: CoroutineContext = this.context,
): VideoInfoGetResponse {
    logger.debug { "Getting video info for $bid..." }
    return this.getVideoInfo(null, bid, cid, context).also {
        logger.debug { "Got video info for $bid: $it" }
    }
}

private suspend fun BiliClient.getVideoPlayerInfo(
    aid: Int?,
    bvid: String?,
    cid: Int,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse = withContext(context) {
    requireLeastAndOnlyOne(aid, bvid)
    client.get<String>(VIDEO_PLAYER_INFO_GET_URL) {
        putVideoId(aid, bvid)
        parameter("cid", cid)
    }.deserializeJson()
}

public suspend fun BiliClient.getVideoPlayerInfo(
    aid: Int,
    cid: Int,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse {
    logger.debug { "Getting VideoPlayerInfo for av$aid - $cid" }
    return getVideoPlayerInfo(aid, null, cid, context).also {
        logger.debug { "Got VideoPlayerInfo for av$aid - $cid: $it" }
    }
}

public suspend fun BiliClient.getVideoPlayerInfo(
    bvid: String,
    cid: Int,
    context: CoroutineContext = this.context,
): VideoPlayerInfoResponse {
    logger.debug { "Getting VideoPlayerInfo for $bvid - $cid" }
    return getVideoPlayerInfo(null, bvid, cid, context).also {
        logger.debug { "Got VideoPlayerInfo for $bvid - $cid: $it" }
    }
}

private suspend inline fun BiliClient.getVideoParts(
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): VideoPartsGetResponse = withContext(context) {
    client.get<String>(VIDEO_PARTS_GET_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ???????????????P, ?????? av ???, ?????? [VideoPartsGetResponse]
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
 * ???????????????P, ?????? bv ???, ?????? [VideoPartsGetResponse]
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
    client.get<String>(VIDEO_DESCRIPTION_GET_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ??????????????????, ?????? av ???, ?????? [VideoDescriptionGetResponse]
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
 * ??????????????????, ?????? bv ???, ?????? [VideoDescriptionGetResponse]
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
    client.get<String>(VIDEO_TAG_GET_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ???????????? Tags, ?????? av ???, ?????? [VideoTagsGetResponse]
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
 * ???????????? Tags, ?????? bv ???, ?????? [VideoTagsGetResponse]
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
    client.post<String>(VIDEO_LIKE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            append("like", action.code.toString())
            putCsrf()
        }
        body = FormDataContent(params)
    }.deserializeJson()
}

/**
 * ????????????
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
 * ????????????
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
    client.get<String>(VIDEO_HAS_LIKE_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ???????????????????????????, ?????? bv ???, ?????? [HasLikedResponse]
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
 * ???????????????????????????, ?????? bv ???, ?????? [HasLikedResponse]
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
    client.post<String>(VIDEO_COIN_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            val actualCount = if (count >= 2) "2" else "1"
            append("multiply", actualCount)
            if (withLike) append("select_like", "1")
            putCsrf()
        }
        body = FormDataContent(params)
    }.deserializeJson()
}

/**
 * ????????????
 * @param aid av id
 * @param count ???????????? in 1..2
 * @param withLike ??????????????????
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
 * ????????????
 * @param bid bv ???
 * @param count ???????????? in 1..2
 * @param withLike ??????????????????
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
    client.get<String>(VIDEO_COIN_CHECK_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ????????????????????????
 * @param aid av???
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
 * ????????????????????????
 * @param bid bv ???
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
    client.post<String>(VIDEO_COLLECT_ACTION_URL) {
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
    }.deserializeJson()
}

/**
 * ????????????
 * @param aid av ???
 * @param action ????????????, ADD / REMOVE
 * @param folderList ?????????id, ??????????????????????????????id
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
 * ????????????
 * @param bid bv ???
 * @param action ????????????, ADD / REMOVE
 * @param folderList ?????????id, ??????????????????????????????id
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
    client.get<String>(VIDEO_COLLECT_CHECK_URL) {
        parameter("aid", getActualId(aid, bid))
    }.deserializeJson()
}

/**
 * ???????????????????????? ?????? av ???, ?????? [VideoCollectCheck]
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
 * ???????????????????????? ?????? bv ???, ?????? [VideoCollectCheck]
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
    client.post<String>(VIDEO_COMBO_LIKE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        body = FormDataContent(params)
    }.deserializeJson()
}

/**
 * ????????????, ?????? aid, ?????? [VideoComboLikeResponse]
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
 * ????????????, ?????? bid, ?????? [VideoComboLikeResponse]
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
    client.post<String>(VIDEO_SHARE_URL) {
        val params = Parameters.build {
            putVideoId(aid, bid)
            putCsrf()
        }
        body = FormDataContent(params)
    }.deserializeJson()
}

/**
 * ????????????, ?????? av ???, ?????? [ShareVideoResponse]
 * ??????, ???????????????????????????, ????????????????????????
 */
public suspend fun BiliClient.shareVideo(
    aid: Int,
    context: CoroutineContext = this.context,
): ShareVideoResponse {
    logger.debug { "Sharing video av$aid..." }
    return shareVideo(aid, null, context).also {
        logger.debug { "Shared video av$aid: $it" }
    }
}

/**
 * ????????????, ?????? bv ???, ?????? [ShareVideoResponse]
 * ??????, ???????????????????????????, ????????????????????????
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
): VideoStreamResponse = withContext(context) {
    client.get<String>(VIDEO_STREAM_FETCH_URL) {
        putVideoId(aid, bid, "avid")
        parameter("cid", cid)
        request.putParameter(this)
    }.deserializeJson()
}

/**
 * ???????????????
 * @param aid av ???
 * @param cid ???p id
 * @param request ????????????
 * @return ?????? VideoStreamResponse
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
 * ???????????????
 * @param bid bv ???
 * @param cid ???p id
 * @param request ????????????
 * @return ?????? VideoStreamResponse
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

public suspend fun BiliClient.fetchPgcStream(
    ep: Int,
    request: StreamRequest = StreamRequest(),
    context: CoroutineContext = this.context,
): PgcStreamResponse = withContext(context) {
    logger.debug { "Fetching Pgc Stream for ep$ep..." }
    client.get<String>(PGC_STREAM_FETCH_URL) {
        parameter("ep_id", ep)
        request.putParameter(this)
    }.deserializeJson<PgcStreamResponse>().also {
        logger.debug { "Fetched Pgc Stream for ep$ep..." }
    }
}

// endregion

// region ==================== Hot ====================

/**
 * ???????????????????????????
 */
private suspend inline fun BiliClient.getTimelineHot(
    cid: Int,
    aid: Int? = null,
    bid: String? = null,
    context: CoroutineContext = this.context,
): TimelineHotResponse = withContext(context) {
    client.get<String>(VIDEO_TIMELINE_HOT_URL) {
        parameter("cid", cid)
        if (aid != null || bid != null) putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ???????????????p ???????????????
 * @param cid ??? p ??????
 * @return [TimelineHotResponse]
 */
public suspend fun BiliClient.getTimelineHot(
    cid: Int,
    context: CoroutineContext = this.context,
): TimelineHotResponse {
    logger.debug { "Getting Timeline Hot for cid $cid..." }
    return getTimelineHot(cid, null, null, context).also {
        logger.debug { "Got timeline hot for cid $cid: $it" }
    }
}

/**
 * ???????????????p ???????????????
 * @param cid ??? p ??????
 * @param aid av ???
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
 * ???????????????p ???????????????
 * @param cid ??? p ??????
 * @param bid bv ???
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
    client.get<String>(VIDEO_ONLINE_GET_URL) {
        putVideoId(aid, bid)
        parameter("cid", cid)
    }.deserializeJson()
}

/**
 * ????????????????????????
 * @param aid av ???
 * @param cid ??? p ???
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
 * ????????????????????????
 * @param bid bv ???
 * @param cid ??? p ???
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
    client.get<String>(VIDEO_RELATED_GET_URL) {
        putVideoId(aid, bid)
    }.deserializeJson()
}

/**
 * ???????????????????????????, ?????? av ???, ?????? [VideoRelatedGetResponse]
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
 * ???????????????????????????, ?????? bv ???, ?????? [VideoRelatedGetResponse]
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
 * ??????????????????
 * @param aid av???
 * @param cid ???p id
 * @param progress ??????, ?????????
 */
public suspend fun BiliClient.reportVideoProgress(
    aid: Int,
    cid: Int,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = withContext(context) {
    logger.debug { "Reporting Video Watch Progress for av$aid, cid$cid." }
    client.post<String>(VIDEO_REPORT_PROGRESS_URL) {
        body = FormDataContent(
            Parameters.build {
                append("aid", aid.toString())
                append("cid", cid.toString())
                append("progress", progress.toString())
                putCsrf()
            }
        )
    }.deserializeJson<ReportWatchResponse>().also {
        logger.debug { "Reported Video Watch Progress for av$aid, cid$cid: $it" }
    }
}

/**
 * ??????????????????
 * @param bid bv???
 * @param cid ???p id
 * @param progress ??????, ?????????
 */
public suspend inline fun BiliClient.reportVideoProgress(
    bid: String,
    cid: Int,
    progress: Int,
    context: CoroutineContext = this.context,
): ReportWatchResponse = reportVideoProgress(bid.avInt, cid, progress, context)

// endregion

// region ==================== Subtitle ====================

private val subtitleUrlRegex by lazy {
    Regex("""^\s*(https?)?:?//([\w\d]+\.hdslb\.com/bfs/subtitle/[\w\d]+\.json)\s*$""")
}

public suspend fun BiliClient.getSubtitleContent(
    url: String,
    context: CoroutineContext = this.context,
): SubtitleContent = withContext(context) {
    logger.debug { "Getting subtitle at $url" }
    val match = subtitleUrlRegex.matchEntire(url)
    require(match?.groupValues != null) { "Subtitle url must matches $subtitleUrlRegex..." }
    val normalizeUrl = "https://" + match!!.groupValues[2]
    client.get<String>(normalizeUrl).deserializeJson<SubtitleContent>().also {
        logger.debug { "Got subtitle content from $normalizeUrl: $it" }
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
    edgeId: Int? = null,
    platform: String = "pc",
    context: CoroutineContext = this.context,
): VideoInteractiveResponse = withContext(context) {
    logger.debug { "Try to get interactive video info for $bvid" }
    client.get<String>(VIDEO_INTERACTIVE_INFO_URL) {
        parameter("bvid", bvid)
        parameter("graph_version", graphVersion)
        edgeId?.let { parameter("edge_id", edgeId) }
        parameter("platform", platform)
    }.deserializeJson<VideoInteractiveResponse>().also {
        logger.debug { "Got interactive video info for $bvid graph $graphVersion edge $edgeId: $it" }
    }
}

// endregion
