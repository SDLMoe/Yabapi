package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.ACCOUNT_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.BASIC_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_EXP_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_LOG_GET_URL
import sdl.moe.yabapi.consts.internal.EXP_REWARD_GET_URL
import sdl.moe.yabapi.consts.internal.MASTERPIECE_VIDEO_GET_URL
import sdl.moe.yabapi.consts.internal.MY_SPACE_GET_URL
import sdl.moe.yabapi.consts.internal.NICK_CHECK_URL
import sdl.moe.yabapi.consts.internal.PINNED_VIDEO_GET_URL
import sdl.moe.yabapi.consts.internal.REAL_NAME_DETAILED_GET_URL
import sdl.moe.yabapi.consts.internal.REAL_NAME_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.SECURE_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.SPACE_VIDEO_GET_URL
import sdl.moe.yabapi.consts.internal.STAT_GET_URL
import sdl.moe.yabapi.consts.internal.USER_CARD_GET_URL
import sdl.moe.yabapi.consts.internal.USER_RECENT_COINED_VIDEO_GET_URL
import sdl.moe.yabapi.consts.internal.USER_RECENT_PLAYED_GAME_GET_URL
import sdl.moe.yabapi.consts.internal.USER_SPACE_ANNOUNCEMENT_GET_URL
import sdl.moe.yabapi.consts.internal.USER_SPACE_GET_URL
import sdl.moe.yabapi.consts.internal.USER_SPACE_SETTING_GET_URL
import sdl.moe.yabapi.consts.internal.USER_TAGS_GET_URL
import sdl.moe.yabapi.consts.internal.VIP_STAT_GET_URL
import sdl.moe.yabapi.data.info.AccountInfoGetResponse
import sdl.moe.yabapi.data.info.BasicInfoGetResponse
import sdl.moe.yabapi.data.info.CheckNickResponse
import sdl.moe.yabapi.data.info.CoinExpGetResponse
import sdl.moe.yabapi.data.info.CoinGetResponse
import sdl.moe.yabapi.data.info.CoinLogGetResponse
import sdl.moe.yabapi.data.info.ExpRewardGetResponse
import sdl.moe.yabapi.data.info.MySpaceGetResponse
import sdl.moe.yabapi.data.info.RealNameDetailedGetResponse
import sdl.moe.yabapi.data.info.RealNameInfoGetResponse
import sdl.moe.yabapi.data.info.SecureInfoGetResponse
import sdl.moe.yabapi.data.info.StatGetResponse
import sdl.moe.yabapi.data.info.UserCardGetResponse
import sdl.moe.yabapi.data.info.UserSpaceGetResponse
import sdl.moe.yabapi.data.info.VipStatGetResponse
import sdl.moe.yabapi.data.space.MasterpieceGetResponse
import sdl.moe.yabapi.data.space.PinnedVideoGetResponse
import sdl.moe.yabapi.data.space.PlayedGameGetResponse
import sdl.moe.yabapi.data.space.RecentCoinedVideoResponse
import sdl.moe.yabapi.data.space.SpaceAnnouncementGetResponse
import sdl.moe.yabapi.data.space.SpaceSettingResponse
import sdl.moe.yabapi.data.space.SpaceVideoResponse
import sdl.moe.yabapi.data.space.UserTagsGetResponse
import sdl.moe.yabapi.enums.video.All
import sdl.moe.yabapi.enums.video.VideoSort
import sdl.moe.yabapi.enums.video.VideoSort.TIME
import sdl.moe.yabapi.enums.video.VideoType
import sdl.moe.yabapi.util.Logger
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
    client.get<String>(BASIC_INFO_GET_URL).also {
        logger.debug { "Basic info response: $it" }
    }.deserializeJson()
}

/**
 * 获取自己的状态值, 需登录
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
 * 获取自己的硬币状态数
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
 * 获取自己的帐号信息, 个人简介相关
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
 * 获取自身经验计算相关属性
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
 * 获取硬币经验, 与 [getExpReward] 不同的是, 此接口实时更新
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
 * 获取自身 Vip 状态值
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
 * 获取自身安全状态信息
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
 * 获取自身实名信息
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
 * 获取详细实名信息
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
 * 获取硬币收支记录
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
 * 获取自身用户空间信息
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
 * 获取目标用户空间信息
 * @param mid 用户 mid
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
 * 获取用户卡片信息
 * @param mid 目标用户 mid
 * @param requestBanner 是否请求空间头图 banner
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
 * 获取目标用户置顶视频
 * @param mid 用戶 uid
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
 * 获取用户代表作
 * @param mid 用戶 uid
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
 * 获取用户 tags
 * @param mid 目标用户mid
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
 * 获取用户空间公告
 * @param mid 目标用户 mid
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
 * 获取目标用户空间设置
 * @param mid 目标用户mid
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
 * 查詢用戶最近玩過的遊戲
 * @param mid 目標用戶 mid
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
 * 獲取目標用戶最近投幣的視頻
 * @param mid 目標用戶 mid
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
 * 獲取目標用戶的空間視頻
 * @param mid 目標用戶 mid
 * @param page 頁碼
 * @param pageSize 每頁視頻數量
 * @param sort 排序方式, 默認按時間排序 [VideoSort]
 * @param type 視頻分區, 默認全部
 * @param keyword 搜索關鍵詞
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
    client.get<String>(NICK_CHECK_URL) {
        parameter("nickName", nick)
    }.deserializeJson<CheckNickResponse>().also {
        logger.debug { "Nick \"$nick\" status: $it" }
    }
}

// endregion
