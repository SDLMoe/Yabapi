package moe.sdl.yabapi.api

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
 * ???????????????????????????, ?????????
 * @see [BasicInfoGetResponse]
 */
public suspend fun BiliClient.getBasicInfo(
    context: CoroutineContext = this.context,
): BasicInfoGetResponse = withContext(context) {
    logger.debug { "Getting basic info..." }
    client.get<String>(BASIC_INFO_GET_URL).also {
        logger.debug { "Basic info response: $it" }
    }.deserializeJson()
}

/**
 * ????????????????????????, ?????????
 * @see StatGetResponse
 */
public suspend fun BiliClient.getStat(
    context: CoroutineContext = this.context,
): StatGetResponse = withContext(context) {
    logger.debug { "Getting stat info..." }
    client.get<String>(STAT_GET_URL)
        .deserializeJson<StatGetResponse>()
        .also { logger.debug { "Got stat info response: $it" } }
}

/**
 * ??????????????????????????????
 * @see CoinGetResponse
 */
public suspend fun BiliClient.getCoinInfo(
    context: CoroutineContext = this.context,
): CoinGetResponse = withContext(context) {
    logger.debug { "Getting coin number..." }
    client.get<String>(COIN_GET_URL)
        .deserializeJson<CoinGetResponse>()
        .also { logger.debug { "Got Coin info response: $it" } }
}

/**
 * ???????????????????????????, ??????????????????
 * @see AccountInfoGetResponse
 */
public suspend fun BiliClient.getAccountInfo(
    context: CoroutineContext = this.context,
): AccountInfoGetResponse = withContext(context) {
    logger.debug { "Getting Account Info..." }
    client.get<String>(ACCOUNT_INFO_GET_URL)
        .deserializeJson<AccountInfoGetResponse>()
        .also { logger.debug { "Got Account Info Response: $it" } }
}

/**
 * ????????????????????????????????????
 * @see ExpRewardGetResponse
 */
public suspend fun BiliClient.getExpReward(
    context: CoroutineContext = this.context,
): ExpRewardGetResponse = withContext(context) {
    logger.debug { "Getting Exp Reward..." }
    client.get<String>(EXP_REWARD_GET_URL)
        .deserializeJson<ExpRewardGetResponse>()
        .also { logger.debug { "Got Exp Reward Response: $it" } }
}

/**
 * ??????????????????, ??? [getExpReward] ????????????, ?????????????????????
 * @see CoinExpGetResponse
 */
public suspend fun BiliClient.getCoinExp(
    context: CoroutineContext = this.context,
): CoinExpGetResponse = withContext(context) {
    logger.debug { "Getting Coin Exp..." }
    client.get<String>(COIN_EXP_GET_URL)
        .deserializeJson<CoinExpGetResponse>()
        .also { logger.debug { "Got Coin Exp Response: $it" } }
}

/**
 * ???????????? Vip ?????????
 * @see VipStatGetResponse
 */
public suspend fun BiliClient.getVipStat(
    context: CoroutineContext = this.context,
): VipStatGetResponse = withContext(context) {
    logger.debug { "Getting Vip Stat..." }
    client.get<String>(VIP_STAT_GET_URL)
        .deserializeJson<VipStatGetResponse>()
        .also { logger.debug { "Got Vip Stat Response: $it" } }
}

/**
 * ??????????????????????????????
 * @see SecureInfoGetResponse
 */
public suspend fun BiliClient.getSecureInfo(
    context: CoroutineContext = this.context,
): SecureInfoGetResponse = with(context) {
    logger.debug { "Getting Secure Info..." }
    client.get<String>(SECURE_INFO_GET_URL)
        .deserializeJson<SecureInfoGetResponse>()
        .also { logger.debug { "Got Secure Info: $it" } }
}

/**
 * ????????????????????????
 * @see RealNameInfoGetResponse
 */
public suspend fun BiliClient.getRealNameInfo(
    context: CoroutineContext = this.context,
): RealNameInfoGetResponse = withContext(context) {
    logger.debug { "Getting Real Name Info..." }
    client.get<String>(REAL_NAME_INFO_GET_URL)
        .deserializeJson<RealNameInfoGetResponse>()
        .also { logger.debug { "Got Real Name Info: $it" } }
}

/**
 * ????????????????????????
 * @see RealNameDetailedGetResponse
 */
public suspend fun BiliClient.getRealNameDetailed(
    context: CoroutineContext = this.context,
): RealNameDetailedGetResponse =
    withContext(context) {
        logger.debug { "Getting Real Name Detailed..." }
        client.get<String>(REAL_NAME_DETAILED_GET_URL)
            .deserializeJson<RealNameDetailedGetResponse>()
            .also { logger.debug { "Got Real Name Detailed: $it" } }
    }

/**
 * ????????????????????????
 * @see CoinLogGetResponse
 */
public suspend fun BiliClient.getCoinLog(
    context: CoroutineContext = this.context,
): CoinLogGetResponse = withContext(context) {
    logger.debug { "Getting Coin Log..." }
    client.get<String>(COIN_LOG_GET_URL)
        .deserializeJson<CoinLogGetResponse>()
        .also { logger.debug { "Got Coin Log: $it" } }
}

/**
 * ??????????????????????????????
 */
public suspend fun BiliClient.getMySpace(
    context: CoroutineContext = this.context,
): MySpaceGetResponse = withContext(context) {
    logger.debug { "Getting Current User Space Info:" }
    client.get<String>(MY_SPACE_GET_URL)
        .deserializeJson<MySpaceGetResponse>()
        .also { logger.debug { "Got Current User Space Info: $it" } }
}

// endregion

// region ============= Target =================

/**
 * ??????????????????????????????
 * @param mid ?????? mid
 * @see UserSpaceGetResponse
 * @see getMySpace
 */
public suspend fun BiliClient.getUserSpace(
    mid: Int,
    context: CoroutineContext = this.context,
): UserSpaceGetResponse = withContext(context) {
    logger.debug { "Getting User Space Info..." }
    client.get<String>(USER_SPACE_GET_URL) {
        parameter("mid", mid.toString())
    }.deserializeJson<UserSpaceGetResponse>().also {
        logger.debug { "Got User $mid Space Info: $it" }
    }
}

/**
 * ????????????????????????
 * @param mid ???????????? mid
 * @param requestBanner ???????????????????????? banner
 * @see UserCardGetResponse
 */
public suspend fun BiliClient.getUserCard(
    mid: Int,
    requestBanner: Boolean,
    context: CoroutineContext = this.context,
): UserCardGetResponse = withContext(context) {
    logger.debug { "Getting User Card Info..." }
    client.get<String>(USER_CARD_GET_URL) {
        parameter("mid", mid.toString())
        parameter("photo", requestBanner.toString())
    }.deserializeJson<UserCardGetResponse>().also {
        logger.debug { "Got User $mid Card Info: $it" }
    }
}

/**
 * ??????????????????????????????
 * @param mid ?????? uid
 * @return [PinnedVideoGetResponse]
 */
public suspend fun BiliClient.getPinnedVideo(
    mid: Int,
    context: CoroutineContext = this.context,
): PinnedVideoGetResponse = withContext(context) {
    logger.debug { "Getting pinned video for mid$mid..." }
    client.get<String>(PINNED_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.deserializeJson<PinnedVideoGetResponse>().also {
        logger.debug { "Got pinned video for mid$mid: $it" }
    }
}

/**
 * ?????????????????????
 * @param mid ?????? uid
 * @return [MasterpieceGetResponse]
 */
public suspend fun BiliClient.getMasterpieceVideo(
    mid: Int,
    context: CoroutineContext = this.context,
): MasterpieceGetResponse = withContext(context) {
    logger.debug { "Getting masterpiece video for mid$mid..." }
    client.get<String>(MASTERPIECE_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.deserializeJson<MasterpieceGetResponse>().also {
        logger.debug { "Got masterpiece video for mid$mid: $it" }
    }
}

/**
 * ???????????? tags
 * @param mid ????????????mid
 * @return [UserTagsGetResponse]
 */
public suspend fun BiliClient.getUserTags(
    mid: Int,
    context: CoroutineContext = this.context,
): UserTagsGetResponse = withContext(context) {
    logger.debug { "Getting user tags for mid$mid..." }
    client.get<String>(USER_TAGS_GET_URL) {
        parameter("mid", mid)
    }.deserializeJson<UserTagsGetResponse>().also {
        logger.debug { "Got user tags for mid $mid: $it" }
    }
}

/**
 * ????????????????????????
 * @param mid ???????????? mid
 */
public suspend fun BiliClient.getSpaceAnnouncement(
    mid: Int,
    context: CoroutineContext = this.context,
): SpaceAnnouncementGetResponse = withContext(context) {
    logger.debug { "Getting Space Announcement for mid$mid..." }
    client.get<String>(USER_SPACE_ANNOUNCEMENT_GET_URL) {
        parameter("mid", mid)
    }.deserializeJson<SpaceAnnouncementGetResponse>().also {
        logger.debug { "Got Space Announcent for mid$mid: $it" }
    }
}

/**
 * ??????????????????????????????
 * @param mid ????????????mid
 */
public suspend fun BiliClient.getSpaceSetting(
    mid: Int,
    context: CoroutineContext = this.context,
): SpaceSettingResponse = withContext(context) {
    logger.debug { "Getting Space Setting for mid$mid..." }
    client.get<String>(USER_SPACE_SETTING_GET_URL) {
        parameter("mid", mid)
    }.deserializeJson<SpaceSettingResponse>().also {
        logger.debug { "Got Space Setting for mid$mid: $it" }
    }
}

/**
 * ?????????????????????????????????
 * @param mid ???????????? mid
 */
public suspend fun BiliClient.getRecentPlayedGame(
    mid: Int,
    context: CoroutineContext = this.context,
): PlayedGameGetResponse = withContext(context) {
    logger.debug { "Getting recent played game for mid $mid..." }
    client.get<String>(USER_RECENT_PLAYED_GAME_GET_URL) {
        parameter("mid", mid)
    }.deserializeJson<PlayedGameGetResponse>().also {
        logger.debug { "Got recent played game for mid $mid: $it" }
    }
}

/**
 * ???????????????????????????????????????
 * @param mid ???????????? mid
 * @return [RecentCoinedVideoResponse]
 */
public suspend fun BiliClient.getRecentCoinedVideo(
    mid: Int,
    context: CoroutineContext = this.context,
): RecentCoinedVideoResponse = withContext(context) {
    logger.debug { "Getting Recent Coined Video for mid $mid" }
    client.get<String>(USER_RECENT_COINED_VIDEO_GET_URL) {
        parameter("vmid", mid)
    }.deserializeJson<RecentCoinedVideoResponse>().also {
        logger.debug { "Got Recent Coined Video for mid $mid: $it" }
    }
}

/**
 * ?????????????????????????????????
 * @param mid ???????????? mid
 * @param page ??????
 * @param pageSize ??????????????????
 * @param sort ????????????, ????????????????????? [VideoSort]
 * @param type ????????????, ????????????
 * @param keyword ???????????????
 */
public suspend fun BiliClient.getSpaceVideo(
    mid: Int,
    page: Int = 1,
    pageSize: Int = 20,
    sort: VideoSort = TIME,
    type: VideoType = All,
    keyword: String? = null,
    context: CoroutineContext = this.context,
): SpaceVideoResponse = withContext(context) {
    logger.debug { "Getting Space Video - mid$mid|$sort|page$page|tid$type..." }
    client.get<String>(SPACE_VIDEO_GET_URL) {
        parameter("mid", mid)
        parameter("order", sort.code)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("tid", type.tid)
        keyword?.let { parameter("keyword", it) }
    }.deserializeJson<SpaceVideoResponse>().also {
        logger.debug { "Got Space Video - mid$mid|$sort|page$page|tid$type: $it" }
    }
}

public suspend fun BiliClient.getSpaceAlbumIndex(
    targetMid: Int,
    count: Int = 8,
    context: CoroutineContext = this.context,
): SpaceAlbumResponse = withContext(context) {
    logger.debug { "Getting Space Album Index..." }
    client.get<String>(SPACE_ALBUM_INDEX_URL) {
        parameter("mid", targetMid)
        parameter("ps", count)
    }.deserializeJson<SpaceAlbumResponse>().also {
        logger.debug { "Got Space Album Index: $it" }
    }
}

public suspend fun BiliClient.getSpaceAlbumList(
    targetMid: Int,
    page: Int = 1,
    pageSize: Int = 20,
) {
    logger.debug { "Got Space Album for mid $targetMid $page[$pageSize]..." }
    client.get<String>(SPACE_ALBUM_LIST_URL) {
        parameter("uid", targetMid)
        parameter("page_num", page)
        parameter("page_size", pageSize)
    }.deserializeJson<SpaceAlbumListResponse>().also {
        logger.debug { "Got Space Album: $it" }
    }
}

public suspend fun BiliClient.getSpaceChannel(
    targetMid: Int,
    context: CoroutineContext = this.context,
): SpaceChannelResponse = withContext(context) {
    logger.debug { "Getting Space Channel for mid $targetMid..." }
    client.get<String>(SPACE_CHANNEL_LIST_URL) {
        parameter("mid", targetMid)
    }.deserializeJson<SpaceChannelResponse>().also {
        logger.debug { "Got Space Channel for mid $targetMid: $it" }
    }
}

public suspend fun BiliClient.getChannelArchives(
    targetMid: Int,
    targetCid: Int,
    page: Int = 1,
    pageSize: Int = 20,
    context: CoroutineContext = this.context,
): SpaceChannelArchivesResponse = withContext(context) {
    val logParameter = "cid $targetCid(mid $targetMid) - page $page[$pageSize]"
    logger.debug { "Getting Space Channel Archives for $logParameter" }
    client.get<String>(SPACE_CHANNEL_ARCHIVES_URL) {
        parameter("mid", targetMid)
        parameter("cid", targetCid)
        parameter("pn", page)
        parameter("ps", pageSize)
    }.deserializeJson<SpaceChannelArchivesResponse>().also {
        logger.debug { "Got Space Channel Archives for $logParameter: $it" }
    }
}

/**
 * ????????????????????????????????????
 */
public suspend fun BiliClient.getFavorites(
    targetMid: Int,
    context: CoroutineContext = this.context,
): FavoritesGetResponse = withContext(context) {
    logger.debug { "Getting Favorites List for mid $targetMid..." }
    client.get<String>(SPACE_COLLECTION_LIST_GET_URL) {
        parameter("up_mid", targetMid)
    }.deserializeJson<FavoritesGetResponse>().also {
        logger.debug { "Got Favorites List for mid $targetMid" }
    }
}

/**
 * ????????????????????????????????????
 * Fav means Favorite
 * @param targetMid ?????? mid
 * @param page ??????
 * @param pageSize ????????????
 */
public suspend fun BiliClient.getCollectedFavorites(
    targetMid: Int,
    page: Int = 1,
    pageSize: Int = 20,
    platform: String = "web",
    context: CoroutineContext = this.context,
): CollectedFavoritesGetResponse = withContext(context) {
    logger.debug { "Getting Collected Favorites List for mid $targetMid..." }
    client.get<String>(SPACE_FAV_COLLECTION_LIST_GET_URL) {
        parameter("up_mid", targetMid)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("platform", platform)
    }.deserializeJson<CollectedFavoritesGetResponse>().also {
        logger.debug { "Got Collected Favorites List for mid $targetMid" }
    }
}

public suspend fun BiliClient.getSubscribedBangumi(
    targetMid: Int,
    page: Int = 1,
    pageSize: Int = 15,
    type: SubscribedBangumiType = ANIME,
    context: CoroutineContext = this.context,
): SubscribedBangumiGetResponse = withContext(context) {
    logger.debug { "Getting subscribed bangumi for $targetMid" }
    client.get<String>(SPACE_SUB_GET_URL) {
        parameter("vmid", targetMid)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("type", type.code)
    }.deserializeJson<SubscribedBangumiGetResponse>().also {
        logger.debug { "Got SubscribedBangumi: $it" }
    }
}

public suspend fun BiliClient.getSubscribedTags(
    targetMid: Int,
    context: CoroutineContext = this.context,
): SubscribedTagsResponse = withContext(context) {
    logger.debug { "Getting Subscribed Tags for $targetMid" }
    client.get<String>(SPACE_SUB_TAGS_GET_URL) {
        parameter("mid", targetMid)
    }.deserializeJson<SubscribedTagsResponse>().also {
        logger.debug { "Got Subscribed Tags for $targetMid" }
    }
}

// endregion

// region ============= Nick =================

/**
 * ????????????????????????
 * @param nick ?????????????????????
 * @see CheckNickResponse
 */
public suspend fun BiliClient.checkNick(
    nick: String,
    context: CoroutineContext = this.context,
): CheckNickResponse = withContext(context) {
    logger.debug { "Checking Nick Status..." }
    client.get<String>(NICK_CHECK_URL) {
        parameter("nickName", nick)
    }.deserializeJson<CheckNickResponse>().also {
        logger.debug { "Nick \"$nick\" status: $it" }
    }
}

// endregion

// region ============= Favorites =================

/**
 * ?????? id ??????????????????????????????
 */
public suspend fun BiliClient.getFavoritesInfo(
    id: Int,
    context: CoroutineContext = this.context,
): FavoritesInfoResponse = withContext(context) {
    logger.debug { "Getting favorites info " }
    client.get<String>(FAVORITES_INFO_GET_URL) {
        parameter("media_id", id)
    }.deserializeJson<FavoritesInfoResponse>().also {
        logger.debug { "Got favorites info: $it" }
    }
}

/**
 * ?????????????????????
 *
 * @param id ?????????id
 * @param page ??????
 * @param pageSize ????????????
 * @param tid ???????????? ID, ????????? [getFavoritesTypes] ??????
 */
public suspend fun BiliClient.getFavoritesMedia(
    id: Int,
    page: Int = 1,
    pageSize: Int = 20,
    tid: Int? = null,
    order: FavoritesOrder = FAVORITE_TIME,
    keyword: String = "",
    context: CoroutineContext = this.context,
): FavoritesMediaResponse = withContext(context) {
    client.get<String>(FAVORITES_MEDIA_GET_URL) {
        parameter("media_id", id)
        parameter("pn", page)
        parameter("ps", pageSize)
        parameter("keyword", keyword)
        parameter("order", order.code)
        tid?.let { parameter("tid", tid) }
    }.deserializeJson()
}

public suspend fun BiliClient.getFavoritesTypes(
    mid: Int,
    favoritesId: Int,
    context: CoroutineContext = this.context,
): FavoritesTypeResponse = withContext(context) {
    logger.debug { "Getting Favorites Type for $favoritesId($mid)" }
    client.get<String>(FAVORITES_TYPES_GET_URL) {
        parameter("up_mid", mid)
        parameter("media_id", favoritesId)
    }.deserializeJson<FavoritesTypeResponse>().also {
        logger.debug { "Got Favorites Type for $favoritesId($mid): $it" }
    }
}

// endregion
