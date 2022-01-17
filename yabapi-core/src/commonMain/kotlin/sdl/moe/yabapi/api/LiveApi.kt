package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.connect.LiveDanmakuConnectConfig
import sdl.moe.yabapi.connect.LiveMessageConnection
import sdl.moe.yabapi.consts.internal.LIVER_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.LIVE_AREA_URL
import sdl.moe.yabapi.consts.internal.LIVE_DANMAKU_INFO_URL
import sdl.moe.yabapi.consts.internal.LIVE_HOVER_GET_URL
import sdl.moe.yabapi.consts.internal.LIVE_INIT_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.LIVE_MEDAL_RANK_GET_URL
import sdl.moe.yabapi.consts.internal.LIVE_RANKING_GET_URL
import sdl.moe.yabapi.consts.internal.LIVE_SHOW_LIST_GET
import sdl.moe.yabapi.consts.internal.LIVE_SIGN_INFO_URL
import sdl.moe.yabapi.consts.internal.LIVE_SIGN_LAST_MONTH_URL
import sdl.moe.yabapi.consts.internal.LIVE_SIGN_URL
import sdl.moe.yabapi.consts.internal.LIVE_STREAM_FETCH_URL
import sdl.moe.yabapi.consts.internal.LIVE_UID_TO_ROOM_ID
import sdl.moe.yabapi.data.LiveIndexList
import sdl.moe.yabapi.data.live.LiveAreasGetResponse
import sdl.moe.yabapi.data.live.LiveDanmakuHost
import sdl.moe.yabapi.data.live.LiveDanmakuInfoGetResponse
import sdl.moe.yabapi.data.live.LiveHoverGetResponse
import sdl.moe.yabapi.data.live.LiveInitGetResponse
import sdl.moe.yabapi.data.live.LiveRankMedalResponse
import sdl.moe.yabapi.data.live.LiveRankResponse
import sdl.moe.yabapi.data.live.LiveSignInfoGetResponse
import sdl.moe.yabapi.data.live.LiveSignLastMonthResponse
import sdl.moe.yabapi.data.live.LiveSignResponse
import sdl.moe.yabapi.data.live.LiverInfoGetResponse
import sdl.moe.yabapi.data.live.RoomIdByUserResponse
import sdl.moe.yabapi.data.stream.LiveStreamRequest
import sdl.moe.yabapi.data.stream.LiveStreamResponse
import sdl.moe.yabapi.data.stream.putLiveStreamRequest
import sdl.moe.yabapi.enums.live.LiveArea
import sdl.moe.yabapi.enums.live.LiveRankType
import sdl.moe.yabapi.enums.live.LiveRankType.LIVER_VITALITY
import sdl.moe.yabapi.enums.live.LiveRankType.USER_ENERGY
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("LiveApi")

// region =========================== Info ===========================

/**
 * 獲取直播分區
 */
public suspend fun BiliClient.getLiveAreas(
    context: CoroutineContext = this.context,
): LiveAreasGetResponse = withContext(context) {
    logger.debug { "Getting live areas..." }
    client.get<LiveAreasGetResponse>(LIVE_AREA_URL).also {
        logger.debug { "Got live areas, response: $it" }
    }
}

public suspend fun BiliClient.getRoomIdByUid(
    uid: Int,
    context: CoroutineContext = this.context,
): RoomIdByUserResponse = withContext(context) {
    logger.debug { "Getting room id of uid $uid" }
    client.get<RoomIdByUserResponse>(LIVE_UID_TO_ROOM_ID) {
        parameter("uid", uid.toString())
    }.also {
        logger.debug { "Got room id of uid $uid: $it" }
    }
}

/**
 * 獲得直播間初始化信息
 * @see [LiveInitGetResponse]
 */
public suspend fun BiliClient.getRoomInitInfo(
    roomId: Int,
    context: CoroutineContext = this.context,
): LiveInitGetResponse = withContext(context) {
    logger.debug { "Getting Room Init Info for room $roomId" }
    client.get<LiveInitGetResponse>(LIVE_INIT_INFO_GET_URL) {
        parameter("id", roomId)
    }.also {
        logger.debug { "Got Room Init Info for room $roomId: $it" }
    }
}

/**
 * 獲取用戶直播信息
 */
public suspend fun BiliClient.getLiverInfo(
    mid: Int,
    context: CoroutineContext = this.context,
): LiverInfoGetResponse = withContext(context) {
    logger.debug { "Getting liver info for mid $mid..." }
    client.get<LiverInfoGetResponse>(LIVER_INFO_GET_URL) {
        parameter("uid", mid)
    }.also {
        logger.debug { "Got liver info for mid $mid, response: $it" }
    }
}

/**
 * 獲取直播首頁推薦項
 */
public suspend fun BiliClient.getLiveIndexList(
    context: CoroutineContext = this.context,
): LiveIndexList = withContext(context) {
    logger.debug { "Getting live index list: " }
    client.get<LiveIndexList>(LIVE_SHOW_LIST_GET).also {
        logger.debug { "Got live index list: $it" }
    }
}

/**
 * 獲取直播浮動推薦欄
 */
public suspend fun BiliClient.getLiveHover(
    areaId: Int,
    context: CoroutineContext = this.context,
): LiveHoverGetResponse = withContext(context) {
    logger.debug { "Getting live hover of area $areaId..." }
    client.get<LiveHoverGetResponse>(LIVE_HOVER_GET_URL) {
        parameter("area_id", areaId)
    }.also {
        logger.debug { "Got live hover of area $areaId" }
    }
}

public suspend inline fun BiliClient.getLiveHover(
    area: LiveArea,
    context: CoroutineContext = this.context,
): LiveHoverGetResponse = getLiveHover(area.id, context)

/**
 * @param realRoomId 需要輸入真實直播間 ID
 * @see getRoomInitInfo
 */
public suspend fun BiliClient.getLiveDanmakuInfo(
    realRoomId: Int,
    context: CoroutineContext = this.context,
): LiveDanmakuInfoGetResponse = withContext(context) {
    logger.debug { "Getting live danmaku info for room $realRoomId" }
    client.get<LiveDanmakuInfoGetResponse>(LIVE_DANMAKU_INFO_URL) {
        parameter("id", realRoomId)
    }.also {
        logger.debug { "Got live danmaku info for room $realRoomId: $it" }
    }
}

// endregion

// region =========================== Stream ===========================

/**
 * @param loginUserMid 當前用戶的 mid
 * @param realRoomId 真實直播間 ID
 * @param token 密鑰, 獲取: [LiveDanmakuInfoGetResponse]
 * @param host [LiveDanmakuHost]
 * @see LiveMessageConnection
 * @see LiveDanmakuConnectConfig
 */
public suspend fun BiliClient.createLiveDanmakuConnection(
    loginUserMid: Int,
    realRoomId: Int,
    token: String,
    host: LiveDanmakuHost,
    context: CoroutineContext = this.context,
    config: LiveDanmakuConnectConfig.() -> Unit = {},
): Job = withContext(context) {
    val bClient = this@createLiveDanmakuConnection
    LiveMessageConnection(
        loginUserMid,
        realRoomId,
        token,
        host,
        bClient.client,
        bClient.json,
        bClient.context,
        config
    ).start()
}

/**
 * 獲取直播視頻流
 * @param roomId 真實房間號
 * @param request 請求內容
 * @see LiveStreamRequest
 * @see LiveStreamResponse
 */
public suspend fun BiliClient.fetchLiveStream(
    roomId: Int,
    request: LiveStreamRequest = LiveStreamRequest(),
    context: CoroutineContext = this.context,
): LiveStreamResponse = withContext(context) {
    logger.debug { "Fetching Live Stream..." }
    client.get<LiveStreamResponse>(LIVE_STREAM_FETCH_URL) {
        parameter("room_id", roomId)
        putLiveStreamRequest(request)
    }.also {
        logger.debug { "Fetched Live Stream: $it" }
    }
}

// endregion

// region =========================== Sign ===========================

/**
 * 直播簽到
 * @see LiveSignResponse
 */
public suspend fun BiliClient.signLive(
    context: CoroutineContext = this.context,
): LiveSignResponse = withContext(context) {
    logger.debug { "Do signing for live..." }
    client.get<LiveSignResponse>(LIVE_SIGN_URL).also {
        logger.debug { "Signed for live, response: $it" }
    }
}

/**
 * 獲取簽到信息
 */
public suspend fun BiliClient.getLiveSignInfo(
    context: CoroutineContext = this.context,
): LiveSignInfoGetResponse = withContext(context) {
    logger.debug { "Getting live sign info..." }
    client.get<LiveSignInfoGetResponse>(LIVE_SIGN_INFO_URL).also {
        logger.debug { "Got Live Sign Info: $it" }
    }
}

/**
 * 獲取上月簽到信息
 */
public suspend fun BiliClient.getLiveSignLastMonthInfo(
    context: CoroutineContext = this.context,
): LiveSignLastMonthResponse = withContext(context) {
    logger.debug { "Getting last-month live sign info..." }
    client.get<LiveSignLastMonthResponse>(LIVE_SIGN_LAST_MONTH_URL).also {
        logger.debug { "Got last-month Live Sign Info: $it" }
    }
}

// endregion

// region =========================== Rank ===========================

public suspend fun BiliClient.getLiveRank(
    type: LiveRankType,
    areaId: Int? = null,
    page: Int = 1,
    pageSize: Int = 20,
    date: String = "week",
    isTrend: Boolean = true,
    context: CoroutineContext = this.context,
): LiveRankResponse = withContext(context) {
    logger.debug { "Getting live rank, type $type, page $page..." }
    client.get<LiveRankResponse>(LIVE_RANKING_GET_URL) {
        if (type == USER_ENERGY || type == LIVER_VITALITY) parameter("date", date)
        parameter("type", type.code)
        parameter("area_id", if (type == LIVER_VITALITY) areaId ?: 0 else "")
        parameter("page", page)
        parameter("pageSize", pageSize)
        parameter("isTrend", if (isTrend) "1" else "0")
    }.also {
        logger.debug { "Got live rank, type $type, page $page: $it" }
    }
}

public suspend fun BiliClient.getLiveMedalRank(
    page: Int = 1,
    pageSize: Int = 20,
    context: CoroutineContext = this.context,
): LiveRankMedalResponse = withContext(context) {
    logger.debug { "Getting Live Medal Rank, page $page..." }
    client.get<LiveRankMedalResponse>(LIVE_MEDAL_RANK_GET_URL) {
        parameter("page", page)
        parameter("page_size", pageSize)
    }.also {
        logger.debug { "Got Live Medal Rank, page $page: $it" }

    }
}

// endregion
