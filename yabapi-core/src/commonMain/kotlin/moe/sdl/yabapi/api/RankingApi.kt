package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.LATEST_VIDEO_GET_URL
import moe.sdl.yabapi.consts.internal.RANKING_GET_URL
import moe.sdl.yabapi.data.GeneralCode.SUCCESS
import moe.sdl.yabapi.data.ranking.LatestVideoGetResponse
import moe.sdl.yabapi.data.ranking.RankingGetResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.video.VideoType
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("RankingApi") }

/**
 * 通過視頻分區獲得排行榜
 * @see VideoType
 */
public suspend fun BiliClient.getRanking(
    type: VideoType,
    day: Int = 3,
    context: CoroutineContext = this.context,
): RankingGetResponse = withContext(context) {
    logger.debug { "Getting Ranking for type ${type.name}, recent $day day(s)." }
    client.get(RANKING_GET_URL) {
        parameter("rid", type.tid)
        parameter("day", day)
    }.body<String>().deserializeJson<RankingGetResponse>().also {
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
    context: CoroutineContext = this.context,
): LatestVideoGetResponse = withContext(context) {
    logger.debug { "Getting latest video for type ${type.name}, page $page..." }
    client.get(LATEST_VIDEO_GET_URL) {
        parameter("pn", page)
        parameter("ps", countPerPage)
        parameter("rid", type.tid)
    }.body<String>().deserializeJson<LatestVideoGetResponse>().also {
        logger.debug { "Got latest video for type ${type.name}, page $page, count: ${it.data?.archives?.count()}" }
        if (it.code != SUCCESS) logger.debug { "$it" }
        else logger.verbose { "$it" }
    }
}
