package moe.sdl.yabapi.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.consts.internal.ACCOUNT_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.BASIC_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.COIN_EXP_GET_URL
import moe.sdl.yabapi.consts.internal.COIN_GET_URL
import moe.sdl.yabapi.consts.internal.COIN_LOG_GET_URL
import moe.sdl.yabapi.consts.internal.EXP_REWARD_GET_URL
import moe.sdl.yabapi.consts.internal.FAVORITES_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.FAVORITES_MEDIA_GET_URL
import moe.sdl.yabapi.consts.internal.FAVORITES_TYPES_GET_URL
import moe.sdl.yabapi.consts.internal.MASTERPIECE_VIDEO_GET_URL
import moe.sdl.yabapi.consts.internal.MY_SPACE_GET_URL
import moe.sdl.yabapi.consts.internal.NICK_CHECK_URL
import moe.sdl.yabapi.consts.internal.PINNED_VIDEO_GET_URL
import moe.sdl.yabapi.consts.internal.REAL_NAME_DETAILED_GET_URL
import moe.sdl.yabapi.consts.internal.REAL_NAME_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.SECURE_INFO_GET_URL
import moe.sdl.yabapi.consts.internal.SPACE_ALBUM_INDEX_URL
import moe.sdl.yabapi.consts.internal.SPACE_ALBUM_LIST_URL
import moe.sdl.yabapi.consts.internal.SPACE_CHANNEL_ARCHIVES_URL
import moe.sdl.yabapi.consts.internal.SPACE_CHANNEL_LIST_URL
import moe.sdl.yabapi.consts.internal.SPACE_COLLECTION_LIST_GET_URL
import moe.sdl.yabapi.consts.internal.SPACE_FAV_COLLECTION_LIST_GET_URL
import moe.sdl.yabapi.consts.internal.SPACE_SUB_GET_URL
import moe.sdl.yabapi.consts.internal.SPACE_SUB_TAGS_GET_URL
import moe.sdl.yabapi.consts.internal.SPACE_VIDEO_GET_URL
import moe.sdl.yabapi.consts.internal.STAT_GET_URL
import moe.sdl.yabapi.consts.internal.USER_CARD_GET_URL
import moe.sdl.yabapi.consts.internal.USER_RECENT_COINED_VIDEO_GET_URL
import moe.sdl.yabapi.consts.internal.USER_RECENT_PLAYED_GAME_GET_URL
import moe.sdl.yabapi.consts.internal.USER_SPACE_ANNOUNCEMENT_GET_URL
import moe.sdl.yabapi.consts.internal.USER_SPACE_GET_URL
import moe.sdl.yabapi.consts.internal.USER_SPACE_SETTING_GET_URL
import moe.sdl.yabapi.consts.internal.USER_TAGS_GET_URL
import moe.sdl.yabapi.consts.internal.VIP_STAT_GET_URL
import moe.sdl.yabapi.data.info.AccountInfoGetResponse
import moe.sdl.yabapi.data.info.BasicInfoGetResponse
import moe.sdl.yabapi.data.info.CheckNickResponse
import moe.sdl.yabapi.data.info.CoinExpGetResponse
import moe.sdl.yabapi.data.info.CoinGetResponse
import moe.sdl.yabapi.data.info.CoinLogGetResponse
import moe.sdl.yabapi.data.info.ExpRewardGetResponse
import moe.sdl.yabapi.data.info.FavoritesInfoResponse
import moe.sdl.yabapi.data.info.FavoritesMediaResponse
import moe.sdl.yabapi.data.info.FavoritesOrder
import moe.sdl.yabapi.data.info.FavoritesOrder.FAVORITE_TIME
import moe.sdl.yabapi.data.info.FavoritesTypeResponse
import moe.sdl.yabapi.data.info.MySpaceGetResponse
import moe.sdl.yabapi.data.info.RealNameDetailedGetResponse
import moe.sdl.yabapi.data.info.RealNameInfoGetResponse
import moe.sdl.yabapi.data.info.SecureInfoGetResponse
import moe.sdl.yabapi.data.info.StatGetResponse
import moe.sdl.yabapi.data.info.UserCardGetResponse
import moe.sdl.yabapi.data.info.UserSpaceGetResponse
import moe.sdl.yabapi.data.info.VipStatGetResponse
import moe.sdl.yabapi.data.space.CollectedFavoritesGetResponse
import moe.sdl.yabapi.data.space.FavoritesGetResponse
import moe.sdl.yabapi.data.space.MasterpieceGetResponse
import moe.sdl.yabapi.data.space.PinnedVideoGetResponse
import moe.sdl.yabapi.data.space.PlayedGameGetResponse
import moe.sdl.yabapi.data.space.RecentCoinedVideoResponse
import moe.sdl.yabapi.data.space.SpaceAlbumListResponse
import moe.sdl.yabapi.data.space.SpaceAlbumResponse
import moe.sdl.yabapi.data.space.SpaceAnnouncementGetResponse
import moe.sdl.yabapi.data.space.SpaceChannelArchivesResponse
import moe.sdl.yabapi.data.space.SpaceChannelResponse
import moe.sdl.yabapi.data.space.SpaceSettingResponse
import moe.sdl.yabapi.data.space.SpaceVideoResponse
import moe.sdl.yabapi.data.space.SubscribedBangumiGetResponse
import moe.sdl.yabapi.data.space.SubscribedTagsResponse
import moe.sdl.yabapi.data.space.UserTagsGetResponse
import moe.sdl.yabapi.deserializeJson
import moe.sdl.yabapi.enums.space.SubscribedBangumiType
import moe.sdl.yabapi.enums.space.SubscribedBangumiType.ANIME
import moe.sdl.yabapi.enums.video.All
import moe.sdl.yabapi.enums.video.VideoSort
import moe.sdl.yabapi.enums.video.VideoSort.TIME
import moe.sdl.yabapi.enums.video.VideoType
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("InfoApi") }

// region ============= Self =================

/**
 * 获取自己的基本信息, 需登录
 * @see [BasicInfoGetResponse]
 */
public suspend fun BiliClient.getBasicInfo(
    context: CoroutineContext = this.context,
): BasicInfoGetResponse = withContext(context) {
    logger.debug { "Getting basic info..." }
    client.get(BASIC_INFO_GET_URL).also {
        logger.debug { "Basic info response: $it" }
    }.body<String>().deserializeJson()
}

/**
 * 获取自己的状态值, 需登录
 * @see StatGetResponse
 */
public suspend fun BiliClient.getStat(
    context: CoroutineContext = this.context,
): StatGetResponse = withContext(context) {
    logger.debug { "Getting stat info..." }
    client.get(STAT_GET_URL)
        .body<String>()
        .deserializeJson<StatGetResponse>()
        .also { logger.debug { "Got stat info response: $it" } }
}

/**
 * 获取自己的硬币状态数
 * @see CoinGetResponse
 */
public suspend fun BiliClient.getCoinInfo(
    context: CoroutineContext = this.context,
): CoinGetResponse = withContext(context) {
    logger.debug { "Getting coin number..." }
    client.get(COIN_GET_URL)
        .body<String>()
        .deserializeJson<CoinGetResponse>()
        .also { logger.debug { "Got Coin info response: $it" } }
}

/**
 * 获取自己的帐号信息, 个人简介相关
 * @see AccountInfoGetResponse
 */
public suspend fun BiliClient.getAccountInfo(
    context: CoroutineContext = this.context,
): AccountInfoGetResponse = withContext(context) {
    logger.debug { "Getting Account Info..." }
    client.get(ACCOUNT_INFO_GET_URL)
        .body<String>()
        .deserializeJson<AccountInfoGetResponse>()
        .also { logger.debug { "Got Account Info Response: $it" } }
}

/**
 * 获取自身经验计算相关属性
 * @see ExpRewardGetResponse
 */
public suspend fun BiliClient.getExpReward(
    context: CoroutineContext = this.context,
): ExpRewardGetResponse = withContext(context) {
    logger.debug { "Getting Exp Reward..." }
    client.get(EXP_REWARD_GET_URL)
        .body<String>()
        .deserializeJson<ExpRewardGetResponse>()
        .also { logger.debug { "Got Exp Reward Response: $it" } }
}

/**
 * 获取硬币经验, 与 [getExpReward] 不同的是, 此接口实时更新
 * @see CoinExpGetResponse
 */
public suspend fun BiliClient.getCoinExp(
    context: CoroutineContext = this.context,
): CoinExpGetResponse = withContext(context) {
    logger.debug { "Getting Coin Exp..." }
    client.get(COIN_EXP_GET_URL)
        .body<String>()
        .deserializeJson<CoinExpGetResponse>()
        .also { logger.debug { "Got Coin Exp Response: $it" } }
}

/**
 * 获取自身 Vip 状态值
 * @see VipStatGetResponse
 */
public suspend fun BiliClient.getVipStat(
    context: CoroutineContext = this.context,
): VipStatGetResponse = withContext(context) {
    logger.debug { "Getting Vip Stat..." }
    client.get(VIP_STAT_GET_URL)
        .body<String>()
        .deserializeJson<VipStatGetResponse>()
        .also { logger.debug { "Got Vip Stat Response: $it" } }
}

/**
 * 获取自身安全状态信息
 * @see SecureInfoGetResponse
 */
public suspend fun BiliClient.getSecureInfo(
    context: CoroutineContext = this.context,
): SecureInfoGetResponse = with(context) {
    logger.debug { "Getting Secure Info..." }
    client.get(SECURE_INFO_GET_URL)
        .body<String>()
        .deserializeJson<SecureInfoGetResponse>()
        .also { logger.debug { "Got Secure Info: $it" } }
}

/**
 * 获取自身实名信息
 * @see RealNameInfoGetResponse
 */
public suspend fun BiliClient.getRealNameInfo(
    context: CoroutineContext = this.context,
): RealNameInfoGetResponse = withContext(context) {
    logger.debug { "Getting Real Name Info..." }
    client.get(REAL_NAME_INFO_GET_URL)
        .body<String>()
        .deserializeJson<RealNameInfoGetResponse>()
        .also { logger.debug { "Got Real Name Info: $it" } }
}

/**
 * 获取详细实名信息
 * @see RealNameDetailedGetResponse
 */
public suspend fun BiliClient.getRealNameDetailed(
    context: CoroutineContext = this.context,
): RealNameDetailedGetResponse =
    withContext(context) {
        logger.debug { "Getting Real Name Detailed..." }
        client.get(REAL_NAME_DETAILED_GET_URL)
            .body<String>()
            .deserializeJson<RealNameDetailedGetResponse>()
            .also { logger.debug { "Got Real Name Detailed: $it" } }
    }

/**
 * 获取硬币收支记录
 * @see CoinLogGetResponse
 */
public suspend fun BiliClient.getCoinLog(
    context: CoroutineContext = this.context,
): CoinLogGetResponse = withContext(context) {
    logger.debug { "Getting Coin Log..." }
    client.get(COIN_LOG_GET_URL)
        .body<String>()
        .deserializeJson<CoinLogGetResponse>()
        .also { logger.debug { "Got Coin Log: $it" } }
}

/**
 * 获取自身用户空间信息
 */
public suspend fun BiliClient.getMySpace(
    context: CoroutineContext = this.context,
): MySpaceGetResponse = withContext(context) {
    logger.debug { "Getting Current User Space Info:" }
    client.get(MY_SPACE_GET_URL)
        .body<String>()
        .deserializeJson<MySpaceGetResponse>()
        .also { logger.debug { "Got Current User Space Info: $it" } }
}

// endregion

// region ============= Target =================

/**
 * 获取目标用户空间信息
 * @param mid 用户 mid
 * @see UserSpaceGetResponse
 * @see getMySpace
 */
public suspend fun BiliClient.getUserSpace(
    mid: Long,
    context: CoroutineContext = this.context,
): UserSpaceGetResponse = withContext(context) {
    logger.debug { "Getting User Space Info..." }
    client.get(USER_SPACE_GET_URL) {
        parameter("mid", mid.toString())
    }.body<String>().deserializeJson<UserSpaceGetResponse>().also {
        logger.debug { "Got User $mid Space Info: $it" }
    }
}

/**
 * 获取用户卡片信息
 * @param mid 目标用户 mid
 * @param requestBanner 是否请求空间头图 banner
 * @see UserCardGetResponse
 */
public suspend fun BiliClient.getUserCard(
    mid: Long,
    requestBanner: Boolean,
    context: CoroutineContext = this.context,
): UserCardGetResponse = withContext(context) {
    logger.debug { "Getting User Card Info..." }
    client.get(USER_CARD_GET_URL) {
        parameter("mid", mid.toString())
        parameter("photo", requestBanner.toString())
    }.body<String>().deserializeJson<UserCardGetResponse>().also {
        logger.debug { "Got User $mid Card Info: $it" }
    }
}

/**
 * 获取目标用户置顶视频
 * @param mid 用戶 uid
 * @return [PinnedVideoGetResponse]
 */
public suspend fun BiliClient.getPinnedVideo(
    mid: Long,
    context: CoroutineContext = this.context,
): PinnedVideoGetResponse = withContext(context) {
    logger.debug { "Getting pinned video for mid$mid..." }
    client.get(PINNED_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.body<String>().deserializeJson<PinnedVideoGetResponse>().also {
        logger.debug { "Got pinned video for mid$mid: $it" }
    }
}

/**
 * 获取用户代表作
 * @param mid 用戶 uid
 * @return [MasterpieceGetResponse]
 */
public suspend fun BiliClient.getMasterpieceVideo(
    mid: Long,
    context: CoroutineContext = this.context,
): MasterpieceGetResponse = withContext(context) {
    logger.debug { "Getting masterpiece video for mid$mid..." }
    client.get(MASTERPIECE_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.body<String>().deserializeJson<MasterpieceGetResponse>().also {
        logger.debug { "Got masterpiece video for mid$mid: $it" }
    }
}

/**
 * 获取用户 tags
 * @param mid 目标用户mid
 * @return [UserTagsGetResponse]
 */
public suspend fun BiliClient.getUserTags(
    mid: Long,
    context: CoroutineContext = this.context,
): UserTagsGetResponse = withContext(context) {
    logger.debug { "Getting user tags for mid$mid..." }
    client.get(USER_TAGS_GET_URL) {
        parameter("mid", mid)
    }.body<String>().deserializeJson<UserTagsGetResponse>().also {
        logger.debug { "Got user tags for mid $mid: $it" }
    }
}

/**
 * 获取用户空间公告
 * @param mid 目标用户 mid
 */
public suspend fun BiliClient.getSpaceAnnouncement(
    mid: Long,
    context: CoroutineContext = this.context,
): SpaceAnnouncementGetResponse = withContext(context) {
    logger.debug { "Getting Space Announcement for mid$mid..." }
    client.get(USER_SPACE_ANNOUNCEMENT_GET_URL) {
        parameter("mid", mid)
    }.body<String>().deserializeJson<SpaceAnnouncementGetResponse>().also {
        logger.debug { "Got Space Announcent for mid$mid: $it" }
    }
}

/**
 * 获取目标用户空间设置
 * @param mid 目标用户mid
 */
public suspend fun BiliClient.getSpaceSetting(
    mid: Long,
    context: CoroutineContext = this.context,
): SpaceSettingResponse = withContext(context) {
    logger.debug { "Getting Space Setting for mid$mid..." }
    client.get(USER_SPACE_SETTING_GET_URL) {
        parameter("mid", mid)
    }.body<String>().deserializeJson<SpaceSettingResponse>().also {
        logger.debug { "Got Space Setting for mid$mid: $it" }
    }
}

/**
 * 查詢用戶最近玩過的遊戲
 * @param mid 目標用戶 mid
 */
public suspend fun BiliClient.getRecentPlayedGame(
    mid: Long,
    context: CoroutineContext = this.context,
): PlayedGameGetResponse = withContext(context) {
    logger.debug { "Getting recent played game for mid $mid..." }
    client.get(USER_RECENT_PLAYED_GAME_GET_URL) {
        parameter("mid", mid)
    }.body<String>().deserializeJson<PlayedGameGetResponse>().also {
        logger.debug { "Got recent played game for mid $mid: $it" }
    }
}

/**
 * 獲取目標用戶最近投幣的視頻
 * @param mid 目標用戶 mid
 * @return [RecentCoinedVideoResponse]
 */
public suspend fun BiliClient.getRecentCoinedVideo(
    mid: Long,
    context: CoroutineContext = this.context,
): RecentCoinedVideoResponse = withContext(context) {
    logger.debug { "Getting Recent Coined Video for mid $mid" }
    client.get(USER_RECENT_COINED_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.body<String>().deserializeJson<RecentCoinedVideoResponse>().also {
        logger.debug { "Got Recent Coined Video for mid $mid: $it" }
    }
}

/**
 * 獲取目標用戶的空間視頻
 * @param mid 目標用戶 mid
 * @param page 頁碼
 * @param pageSize 每頁視頻數量
 * @param sort 排序方式, 默認按時間排序 [VideoSort]
 * @param type 視頻分區, 默認全部
 * @param keyword 搜索關鍵詞
 */
public suspend fun BiliClient.getSpaceVideo(
    mid: Long,
    page: Int = 1,
    pageSize: Int = 20,
    sort: VideoSort = TIME,
    type: VideoType = All,
    keyword: String? = null,
    context: CoroutineContext = this.context,
): SpaceVideoResponse = withContext(context) {
    logger.debug { "Getting Space Video - mid$mid|$sort|page$page|tid$type..." }
    client.get(SPACE_VIDEO_GET_URL) {
        parameter("mid", mid)
        parameter("order", sort.code)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("tid", type.tid)
        keyword?.let { parameter("keyword", it) }
    }.body<String>().deserializeJson<SpaceVideoResponse>().also {
        logger.debug { "Got Space Video - mid$mid|$sort|page$page|tid$type: $it" }
    }
}

public suspend fun BiliClient.getSpaceAlbumIndex(
    targetMid: Long,
    count: Int = 8,
    context: CoroutineContext = this.context,
): SpaceAlbumResponse = withContext(context) {
    logger.debug { "Getting Space Album Index..." }
    client.get(SPACE_ALBUM_INDEX_URL) {
        parameter("mid", targetMid)
        parameter("ps", count)
    }.body<String>().deserializeJson<SpaceAlbumResponse>().also {
        logger.debug { "Got Space Album Index: $it" }
    }
}

public suspend fun BiliClient.getSpaceAlbumList(
    targetMid: Long,
    page: Int = 1,
    pageSize: Int = 20,
) {
    logger.debug { "Got Space Album for mid $targetMid $page[$pageSize]..." }
    client.get(SPACE_ALBUM_LIST_URL) {
        parameter("uid", targetMid)
        parameter("page_num", page)
        parameter("page_size", pageSize)
    }.body<String>().deserializeJson<SpaceAlbumListResponse>().also {
        logger.debug { "Got Space Album: $it" }
    }
}

public suspend fun BiliClient.getSpaceChannel(
    targetMid: Long,
    context: CoroutineContext = this.context,
): SpaceChannelResponse = withContext(context) {
    logger.debug { "Getting Space Channel for mid $targetMid..." }
    client.get(SPACE_CHANNEL_LIST_URL) {
        parameter("mid", targetMid)
    }.body<String>().deserializeJson<SpaceChannelResponse>().also {
        logger.debug { "Got Space Channel for mid $targetMid: $it" }
    }
}

public suspend fun BiliClient.getChannelArchives(
    targetMid: Long,
    targetCid: Long,
    page: Int = 1,
    pageSize: Int = 20,
    context: CoroutineContext = this.context,
): SpaceChannelArchivesResponse = withContext(context) {
    val logParameter = "cid $targetCid(mid $targetMid) - page $page[$pageSize]"
    logger.debug { "Getting Space Channel Archives for $logParameter" }
    client.get(SPACE_CHANNEL_ARCHIVES_URL) {
        parameter("mid", targetMid)
        parameter("cid", targetCid)
        parameter("pn", page)
        parameter("ps", pageSize)
    }.body<String>().deserializeJson<SpaceChannelArchivesResponse>().also {
        logger.debug { "Got Space Channel Archives for $logParameter: $it" }
    }
}

/**
 * 獲取目標自己創建的收藏夾
 */
public suspend fun BiliClient.getFavorites(
    targetMid: Long,
    context: CoroutineContext = this.context,
): FavoritesGetResponse = withContext(context) {
    logger.debug { "Getting Favorites List for mid $targetMid..." }
    client.get(SPACE_COLLECTION_LIST_GET_URL) {
        parameter("up_mid", targetMid)
    }.body<String>().deserializeJson<FavoritesGetResponse>().also {
        logger.debug { "Got Favorites List for mid $targetMid" }
    }
}

/**
 * 目標獲取收藏他人的收藏夾
 * Fav means Favorite
 * @param targetMid 目標 mid
 * @param page 頁碼
 * @param pageSize 單頁大小
 */
public suspend fun BiliClient.getCollectedFavorites(
    targetMid: Long,
    page: Int = 1,
    pageSize: Int = 20,
    platform: String = "web",
    context: CoroutineContext = this.context,
): CollectedFavoritesGetResponse = withContext(context) {
    logger.debug { "Getting Collected Favorites List for mid $targetMid..." }
    client.get(SPACE_FAV_COLLECTION_LIST_GET_URL) {
        parameter("up_mid", targetMid)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("platform", platform)
    }.body<String>().deserializeJson<CollectedFavoritesGetResponse>().also {
        logger.debug { "Got Collected Favorites List for mid $targetMid" }
    }
}

public suspend fun BiliClient.getSubscribedBangumi(
    targetMid: Long,
    page: Int = 1,
    pageSize: Int = 15,
    type: SubscribedBangumiType = ANIME,
    context: CoroutineContext = this.context,
): SubscribedBangumiGetResponse = withContext(context) {
    logger.debug { "Getting subscribed bangumi for $targetMid" }
    client.get(SPACE_SUB_GET_URL) {
        parameter("vmid", targetMid)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("type", type.code)
    }.body<String>().deserializeJson<SubscribedBangumiGetResponse>().also {
        logger.debug { "Got SubscribedBangumi: $it" }
    }
}

public suspend fun BiliClient.getSubscribedTags(
    targetMid: Long,
    context: CoroutineContext = this.context,
): SubscribedTagsResponse = withContext(context) {
    logger.debug { "Getting Subscribed Tags for $targetMid" }
    client.get(SPACE_SUB_TAGS_GET_URL) {
        parameter("mid", targetMid)
    }.body<String>().deserializeJson<SubscribedTagsResponse>().also {
        logger.debug { "Got Subscribed Tags for $targetMid" }
    }
}

// endregion

// region ============= Nick =================

/**
 * 检查名称是否可用
 * @param nick 需要检查的昵称
 * @see CheckNickResponse
 */
public suspend fun BiliClient.checkNick(
    nick: String,
    context: CoroutineContext = this.context,
): CheckNickResponse = withContext(context) {
    logger.debug { "Checking Nick Status..." }
    client.get(NICK_CHECK_URL) {
        parameter("nickName", nick)
    }.body<String>().deserializeJson<CheckNickResponse>().also {
        logger.debug { "Nick \"$nick\" status: $it" }
    }
}

// endregion

// region ============= Favorites =================

/**
 * 根据 id 获得特定收藏夹的信息
 */
public suspend fun BiliClient.getFavoritesInfo(
    id: Long,
    context: CoroutineContext = this.context,
): FavoritesInfoResponse = withContext(context) {
    logger.debug { "Getting favorites info " }
    client.get(FAVORITES_INFO_GET_URL) {
        parameter("media_id", id)
    }.body<String>().deserializeJson<FavoritesInfoResponse>().also {
        logger.debug { "Got favorites info: $it" }
    }
}

/**
 * 获取收藏夹内容
 *
 * @param id 收藏夹id
 * @param page 页码
 * @param pageSize 每页数量
 * @param tid 分区过滤 ID, 可通过 [getFavoritesTypes] 获取
 */
public suspend fun BiliClient.getFavoritesMedia(
    id: Long,
    page: Int = 1,
    pageSize: Int = 20,
    tid: Long? = null,
    order: FavoritesOrder = FAVORITE_TIME,
    keyword: String = "",
    context: CoroutineContext = this.context,
): FavoritesMediaResponse = withContext(context) {
    client.get(FAVORITES_MEDIA_GET_URL) {
        parameter("media_id", id)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("keyword", keyword)
        parameter("order", order.code)
        tid?.let { parameter("tid", tid) }
    }.body<String>().deserializeJson()
}

public suspend fun BiliClient.getFavoritesTypes(
    mid: Long,
    favoritesId: Long,
    context: CoroutineContext = this.context,
): FavoritesTypeResponse = withContext(context) {
    logger.debug { "Getting Favorites Type for $favoritesId($mid)" }
    client.get(FAVORITES_TYPES_GET_URL) {
        parameter("up_mid", mid)
        parameter("media_id", favoritesId)
    }.body<String>().deserializeJson<FavoritesTypeResponse>().also {
        logger.debug { "Got Favorites Type for $favoritesId($mid): $it" }
    }
}

// endregion
