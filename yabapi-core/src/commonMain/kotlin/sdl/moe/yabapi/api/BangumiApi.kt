package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.BANGUMI_DETAILED_GET_URL
import sdl.moe.yabapi.consts.internal.BANGUMI_INFO_GET_URL
import sdl.moe.yabapi.data.bangumi.BangumiDetailedResponse
import sdl.moe.yabapi.data.bangumi.BangumiInfoGetResponse
import sdl.moe.yabapi.deserializeJson
import sdl.moe.yabapi.util.Logger
import sdl.moe.yabapi.util.requireLeastAndOnlyOne
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
    client.get<String>(BANGUMI_INFO_GET_URL) {
        parameter("media_id", mediaId)
    }.deserializeJson<BangumiInfoGetResponse>().also {
        logger.debug { "Got bangumi info for media id $mediaId： $it" }
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
    client.get<String>(BANGUMI_DETAILED_GET_URL) {
        seasonId?.let { parameter("season_id", it) }
        seasonId?.let { parameter("ep_id", it) }
    }.deserializeJson<BangumiDetailedResponse>().also {
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
