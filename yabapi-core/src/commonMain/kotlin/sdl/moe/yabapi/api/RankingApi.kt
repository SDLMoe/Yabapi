// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.LATEST_VIDEO_GET_URL
import sdl.moe.yabapi.consts.internal.RANKING_GET_URL
import sdl.moe.yabapi.data.GeneralCode.SUCCESS
import sdl.moe.yabapi.data.ranking.LatestVideoGetResponse
import sdl.moe.yabapi.data.ranking.RankingGetResponse
import sdl.moe.yabapi.enums.video.VideoType
import sdl.moe.yabapi.util.Logger

private val logger = Logger("RankingApi")

/**
 * 通過視頻分區獲得排行榜
 * @see VideoType
 */
public suspend fun BiliClient.getRanking(type: VideoType, day: Int = 3): RankingGetResponse =
    withContext(context) {
        logger.debug { "Getting Ranking for type ${type.name}, recent $day day(s)." }
        client.get<RankingGetResponse>(RANKING_GET_URL) {
            parameter("rid", type.tid)
            parameter("day", day)
        }.also {
            logger.debug { "Got Ranking for ${type.name}, recent $day day(s), count: ${it.data.count()}" }
            if (it.code != SUCCESS) logger.debug { "$it" }
            else logger.verbose { "$it" }
        }
    }

/**
 * 通過視頻分區獲得最新發佈的視頻
 */
public suspend fun BiliClient.getLatestVideo(
    type: VideoType,
    page: Int = 1,
    countPerPage: Int = 5,
): LatestVideoGetResponse = withContext(context) {
    logger.debug { "Getting latest video for type ${type.name}, page $page..." }
    client.get<LatestVideoGetResponse>(LATEST_VIDEO_GET_URL) {
        parameter("pn", page)
        parameter("ps", countPerPage)
        parameter("rid", type.tid)
    }.also {
        logger.debug { "Got latest video for type ${type.name}, page $page, count: ${it.data?.archives?.count()}" }
        if (it.code != SUCCESS) logger.debug { "$it" }
        else logger.verbose { "$it" }
    }
}
