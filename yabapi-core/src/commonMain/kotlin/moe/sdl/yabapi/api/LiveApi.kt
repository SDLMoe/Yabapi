package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.connect.LiveDanmakuConnectConfig
import moe.sdl.yabapi.connect.LiveMessageConnection
import moe.sdl.yabapi.consts.internal.LIVER_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_AREA_URL
import moe.sdl.yabapi.consts.internal.LIVE_CHECK_PWD_URL
import moe.sdl.yabapi.consts.internal.LIVE_DANMAKU_INFO_URL
import moe.sdl.yabapi.consts.internal.LIVE_GUARD_LIST_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_HOVER_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_INIT_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_MEDAL_RANK_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_RANKING_GET_URL
import moe.sdl.yabapi.consts.internal.LIVE_ROOM_INFO_URL
import moe.sdl.yabapi.consts.internal.LIVE_SHOW_LIST_GET
import moe.sdl.yabapi.consts.internal.LIVE_SIGN_INFO_URL
import moe.sdl.yabapi.consts.internal.LIVE_SIGN_LAST_MONTH_URL
import moe.sdl.yabapi.consts.internal.LIVE_SIGN_URL
import moe.sdl.yabapi.consts.internal.LIVE_STREAM_FETCH_URL
import moe.sdl.yabapi.consts.internal.LIVE_UID_TO_ROOM_ID
import moe.sdl.yabapi.data.live.LiveAreasGetResponse
import moe.sdl.yabapi.data.live.LiveDanmakuHost
import moe.sdl.yabapi.data.live.LiveDanmakuInfoGetResponse
import moe.sdl.yabapi.data.live.LiveGuardListGetResponse
import moe.sdl.yabapi.data.live.LiveHoverGetResponse
import moe.sdl.yabapi.data.live.LiveIndexList
import moe.sdl.yabapi.data.live.LiveInitGetResponse
import moe.sdl.yabapi.data.live.LiveRankMedalResponse
import moe.sdl.yabapi.data.live.LiveRankResponse
import moe.sdl.yabapi.data.live.LiveRoomInfoResponse
import moe.sdl.yabapi.data.live.LiveRoomPwdResponse
import moe.sdl.yabapi.data.live.LiveSignInfoGetResponse
import moe.sdl.yabapi.data.live.LiveSignLastMonthResponse
import moe.sdl.yabapi.data.live.LiveSignResponse
import moe.sdl.yabapi.data.live.LiverInfoGetResponse
import moe.sdl.yabapi.data.live.RoomIdByUserResponse
import moe.sdl.yabapi.data.stream.LiveStreamRequest
import moe.sdl.yabapi.data.stream.LiveStreamResponse
import moe.sdl.yabapi.data.stream.putLiveStreamRequest
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.live.LiveArea
import moe.sdl.yabapi.enums.live.LiveRankType
import moe.sdl.yabapi.enums.live.LiveRankType.LIVER_VITALITY
import moe.sdl.yabapi.enums.live.LiveRankType.USER_ENERGY
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("LiveApi") }

// region =========================== Info ===========================

/**
 * 獲取直播分區
 */
public suspend fun BiliClient.getLiveAreas(
    context: CoroutineContext = this.context,
): LiveAreasGetResponse = withContext(context) {
    logger.debug { "Getting live areas..." }
    client.get(LIVE_AREA_URL)
        .body<String>()
        .deserializeJson<LiveAreasGetResponse>()
        .also { logger.debug { "Got live areas, response: $it" } }
}

public suspend fun BiliClient.getRoomIdByUid(
    uid: Long,
    context: CoroutineContext = this.context,
): RoomIdByUserResponse = withContext(context) {
    logger.debug { "Getting room id of uid $uid" }
    client.get(LIVE_UID_TO_ROOM_ID) {
        parameter("uid", uid.toString())
    }.body<String>().deserializeJson<RoomIdByUserResponse>().also {
        logger.debug { "Got room id of uid $uid: $it" }
    }
}

/**
 * 獲得直播間初始化信息
 * @see [LiveInitGetResponse]
 */
public suspend fun BiliClient.getRoomInitInfo(
    roomId: Long,
    context: CoroutineContext = this.context,
): LiveInitGetResponse = withContext(context) {
    logger.debug { "Getting Room Init Info for room $roomId" }
    client.get(LIVE_INIT_INFO_GET_URL) {
        parameter("id", roomId)
    }.body<String>().deserializeJson<LiveInitGetResponse>().also {
        logger.debug { "Got Room Init Info for room $roomId: $it" }
    }
}

public suspend fun BiliClient.getRoomInfoByRoomId(
    roomId: Long,
    context: CoroutineContext = this.context,
): LiveRoomInfoResponse = withContext(context) {
    logger.debug { "Getting Room Info for room $roomId" }
    client.get(LIVE_ROOM_INFO_URL) {
        parameter("id", roomId)
    }.body<String>().deserializeJson<LiveRoomInfoResponse>().also {
        logger.debug { "Got Room Info for room $roomId: $it" }
    }
}

/**
 * 獲取用戶直播信息
 */
public suspend fun BiliClient.getLiverInfo(
    mid: Long,
    context: CoroutineContext = this.context,
): LiverInfoGetResponse = withContext(context) {
    logger.debug { "Getting liver info for mid $mid..." }
    client.get(LIVER_INFO_GET_URL) {
        parameter("uid", mid)
    }.body<String>().deserializeJson<LiverInfoGetResponse>().also {
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
    client.get(LIVE_SHOW_LIST_GET)
        .body<String>()
        .deserializeJson<LiveIndexList>()
        .also { logger.debug { "Got live index list: $it" } }
}

/**
 * 驗證房間密碼
 */
public suspend fun BiliClient.checkLivePwd(
    id: Long,
    pwd: String,
    context: CoroutineContext = this.context,
): LiveRoomPwdResponse = withContext(context) {
    logger.debug { "Checking Live Pwd for id$id" }
    client.get(LIVE_CHECK_PWD_URL) {
        parameter("room_id", id)
        parameter("pwd", pwd)
    }.body<String>().deserializeJson<LiveRoomPwdResponse>().also {
        logger.debug { "Checked Live Pwd for id$id: $it" }
    }
}

/**
 * 獲取直播浮動推薦欄
 */
public suspend fun BiliClient.getLiveHover(
    areaId: Long,
    context: CoroutineContext = this.context,
): LiveHoverGetResponse = withContext(context) {
    logger.debug { "Getting live hover of area $areaId..." }
    client.get(LIVE_HOVER_GET_URL) {
        parameter("area_id", areaId)
    }.body<String>().deserializeJson<LiveHoverGetResponse>().also {
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
    realRoomId: Long,
    context: CoroutineContext = this.context,
): LiveDanmakuInfoGetResponse = withContext(context) {
    logger.debug { "Getting live danmaku info for room $realRoomId" }
    client.get(LIVE_DANMAKU_INFO_URL) {
        parameter("id", realRoomId)
    }.body<String>().deserializeJson<LiveDanmakuInfoGetResponse>().also {
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
    loginUserMid: Long,
    realRoomId: Long,
    token: String,
    host: LiveDanmakuHost,
    context: CoroutineContext = this.context,
    config: LiveDanmakuConnectConfig.() -> Unit = {},
): Job = LiveMessageConnection(
    loginUserMid = loginUserMid,
    realRoomId = realRoomId,
    token = token,
    host = host,
    client = client,
    jsonParser = Yabapi.defaultJson.value,
    context = context,
    config = config
).start()

/**
 * 獲取直播視頻流
 * @param roomId 真實房間號
 * @param request 請求內容
 * @see LiveStreamRequest
 * @see LiveStreamResponse
 */
public suspend fun BiliClient.fetchLiveStream(
    roomId: Long,
    request: LiveStreamRequest = LiveStreamRequest(),
    context: CoroutineContext = this.context,
): LiveStreamResponse = withContext(context) {
    logger.debug { "Fetching Live Stream..." }
    client.get(LIVE_STREAM_FETCH_URL) {
        parameter("room_id", roomId)
        putLiveStreamRequest(request)
    }.body<String>().deserializeJson<LiveStreamResponse>().also {
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
    client.get(LIVE_SIGN_URL)
        .body<String>()
        .deserializeJson<LiveSignResponse>()
        .also { logger.debug { "Signed for live, response: $it" } }
}

/**
 * 獲取簽到信息
 */
public suspend fun BiliClient.getLiveSignInfo(
    context: CoroutineContext = this.context,
): LiveSignInfoGetResponse = withContext(context) {
    logger.debug { "Getting live sign info..." }
    client.get(LIVE_SIGN_INFO_URL)
        .body<String>()
        .deserializeJson<LiveSignInfoGetResponse>()
        .also { logger.debug { "Got Live Sign Info: $it" } }
}

/**
 * 獲取上月簽到信息
 */
public suspend fun BiliClient.getLiveSignLastMonthInfo(
    context: CoroutineContext = this.context,
): LiveSignLastMonthResponse = withContext(context) {
    logger.debug { "Getting last-month live sign info..." }
    client.get(LIVE_SIGN_LAST_MONTH_URL)
        .body<String>()
        .deserializeJson<LiveSignLastMonthResponse>()
        .also { logger.debug { "Got last-month Live Sign Info: $it" } }
}

// endregion

// region =========================== Rank ===========================

public suspend fun BiliClient.getLiveRank(
    type: LiveRankType,
    areaId: Long? = null,
    page: Int = 1,
    pageSize: Int = 20,
    date: String = "week",
    isTrend: Boolean = true,
    context: CoroutineContext = this.context,
): LiveRankResponse = withContext(context) {
    logger.debug { "Getting live rank, type $type, page $page..." }
    client.get(LIVE_RANKING_GET_URL) {
        if (type == USER_ENERGY || type == LIVER_VITALITY) parameter("date", date)
        parameter("type", type.code)
        parameter("area_id", if (type == LIVER_VITALITY) areaId ?: 0 else "")
        parameter("page", page)
        parameter("pageSize", pageSize)
        parameter("isTrend", if (isTrend) "1" else "0")
    }.body<String>().deserializeJson<LiveRankResponse>().also {
        logger.debug { "Got live rank, type $type, page $page: $it" }
    }
}

public suspend fun BiliClient.getLiveMedalRank(
    page: Int = 1,
    pageSize: Int = 20,
    context: CoroutineContext = this.context,
): LiveRankMedalResponse = withContext(context) {
    logger.debug { "Getting Live Medal Rank, page $page..." }
    client.get(LIVE_MEDAL_RANK_GET_URL) {
        parameter("page", page)
        parameter("page_size", pageSize)
    }.body<String>().deserializeJson<LiveRankMedalResponse>().also {
        logger.debug { "Got Live Medal Rank, page $page: $it" }
    }
}

// endregion

// region =========================== Guard ===========================

public suspend fun BiliClient.getGuardList(
    roomId: Long,
    targetUid: Long,
    page: Int = 1,
    pageSize: Int = 29,
    context: CoroutineContext = this.context,
): LiveGuardListGetResponse = withContext(context) {
    logger.debug { "Getting live guard list for room $roomId, page $page, size $pageSize..." }
    client.get(LIVE_GUARD_LIST_GET_URL) {
        parameter("roomid", roomId)
        parameter("page", page)
        parameter("ruid", targetUid)
        parameter("page_size", pageSize)
    }.body<String>().deserializeJson<LiveGuardListGetResponse>().also {
        logger.debug { "Got live guard list response: $it" }
    }
}

// endregion
