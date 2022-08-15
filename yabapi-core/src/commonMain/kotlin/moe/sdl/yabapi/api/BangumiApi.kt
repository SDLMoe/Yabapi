package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.BANGUMI_DETAILED_GET_URL
import moe.sdl.yabapi.consts.internal.BANGUMI_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.BANGUMI_REVIEW_INFO_GET_URL
import moe.sdl.yabapi.data.bangumi.BangumiDetailedResponse
import moe.sdl.yabapi.data.bangumi.BangumiInfoGetResponse
import moe.sdl.yabapi.data.bangumi.BangumiReviewInfoResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.requireLeastAndOnlyOne
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("BangumiApi") }

/**
 * 獲取番劇系列基本信息
 * @param mediaId 傳入 media id 形如 md114514
 * @return [BangumiInfoGetResponse]
 */
public suspend fun BiliClient.getBangumiInfo(
    mediaId: Int,
    context: CoroutineContext = this.context,
): BangumiInfoGetResponse = withContext(context) {
    logger.debug { "Getting bangumi info for media id $mediaId" }
    client.get(BANGUMI_INFO_GET_URL) {
        parameter("media_id", mediaId)
    }.body<String>().deserializeJson<BangumiInfoGetResponse>().also {
        logger.debug { "Got bangumi info for media id $mediaId： $it" }
    }
}

/**
 * 获取评价相关信息
 *
 * 可以用于获取 md 号对应的 ss 号
 */
public suspend fun BiliClient.getBangumiReviewInfo(
    mediaId: Int,
    context: CoroutineContext = this.context,
): BangumiReviewInfoResponse = withContext(context) {
    logger.debug { "Getting Bangumi Review Info for media id $mediaId" }
    client.get(BANGUMI_REVIEW_INFO_GET_URL) {
        parameter("media_id", mediaId)
    }.body<String>().deserializeJson<BangumiReviewInfoResponse>().also {
        logger.debug { "Got Bangumi Review Info for media id $mediaId: $it" }
    }
}

private suspend inline fun BiliClient.getBangumiDetailed(
    seasonId: Int? = null,
    epId: Int? = null,
    context: CoroutineContext = this.context,
): BangumiDetailedResponse = withContext(context) {
    requireLeastAndOnlyOne(seasonId, epId)
    val showId = if (seasonId != null) "ss$seasonId" else "ep$epId"
    logger.debug { "Getting bangumi detailed info for $showId..." }
    client.get(BANGUMI_DETAILED_GET_URL) {
        seasonId?.let { parameter("season_id", it) }
        epId?.let { parameter("ep_id", it) }
    }.body<String>().deserializeJson<BangumiDetailedResponse>().also {
        logger.debug { "Got bangumi detailed info for $showId: $it" }
    }
}

/**
 * 獲取特定某一季的番劇信息
 * @param seasonId 輸入 ssid, 形如 ss30132
 */
public suspend fun BiliClient.getBangumiDetailedBySeason(
    seasonId: Int,
    context: CoroutineContext = this.context,
): BangumiDetailedResponse = getBangumiDetailed(seasonId, null, context)

/**
 * 通過劇集 ep 號獲取番劇信息
 * @param epId 輸入 ep 號 形如 ep457555
 */
public suspend fun BiliClient.getBangumiDetailedByEp(
    epId: Int,
    context: CoroutineContext = this.context,
): BangumiDetailedResponse = getBangumiDetailed(null, epId, context)
