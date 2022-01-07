// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.BANGUMI_DETAILED_GET_URL
import sdl.moe.yabapi.consts.internal.BANGUMI_INFO_GET_URL
import sdl.moe.yabapi.data.bangumi.BangumiDetailedResponse
import sdl.moe.yabapi.data.bangumi.BangumiInfoGetResponse
import sdl.moe.yabapi.util.logger
import sdl.moe.yabapi.util.requireLeastAndOnlyOne

public object BangumiApi : BiliApi {
    override val apiName: String = "bangumi"

    init {
        BiliClient.registerApi(this)
    }

    public suspend fun BiliClient.getBangumiInfo(mediaId: Int): BangumiInfoGetResponse =
        withContext(dispatcher) {
            logger.debug { "Getting bangumi info for media id $mediaId" }
            client.get<BangumiInfoGetResponse>(BANGUMI_INFO_GET_URL) {
                parameter("media_id", mediaId)
            }.also {
                logger.debug { "Got bangumi info for media id $mediaId： $it" }
            }
        }

    private suspend inline fun BiliClient.getBangumiDetailed(
        seasonId: Int? = null,
        epId: Int? = null,
    ): BangumiDetailedResponse =
        withContext(dispatcher) {
            requireLeastAndOnlyOne(seasonId, epId)
            val showId = if (seasonId != null) "ss$seasonId" else "ep$epId"
            logger.debug { "Getting bangumi detailed info for $showId..." }
            client.get<BangumiDetailedResponse>(BANGUMI_DETAILED_GET_URL) {
                seasonId?.let { parameter("season_id", it) }
                seasonId?.let { parameter("ep_id", it) }
            }.also { logger.debug { "Got bangumi detailed info for $showId: $it" } }
        }

    public suspend fun BiliClient.getBangumiDetailedBySeason(seasonId: Int): BangumiDetailedResponse =
        getBangumiDetailed(seasonId, null)

    public suspend fun BiliClient.getBangumiDetailedByEp(epId: Int): BangumiDetailedResponse =
        getBangumiDetailed(null, epId)
}
