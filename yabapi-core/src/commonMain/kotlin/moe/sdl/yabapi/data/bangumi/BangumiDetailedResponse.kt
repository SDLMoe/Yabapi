@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.info.OfficialRole
import moe.sdl.yabapi.data.info.Pendant
import moe.sdl.yabapi.data.info.VipLabel
import moe.sdl.yabapi.data.info.VipStatus
import moe.sdl.yabapi.data.info.VipType
import moe.sdl.yabapi.data.video.VideoDimension
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 通過 epid ssid 獲取到的番劇信息
 * @property data 成功时返回的 data [BangumiDetailed]
 */
@Serializable
public data class BangumiDetailedResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("result") val data: BangumiDetailed? = null,
)

@Serializable
public data class BangumiDetailed(
    @SerialName("activity") val activity: BangumiActivity? = null, // 活動
    @SerialName("alias") val alias: String? = null,
    @SerialName("areas") val areas: List<BangumiArea> = emptyList(),
    @SerialName("bkg_cover") val backgroundCover: String? = null, // 背景圖
    @SerialName("cover") val cover: String? = null, // 封面
    @SerialName("episodes") val episodes: List<BangumiEpisode> = emptyList(), // 詳細劇集
    @SerialName("evaluate") val evaluate: String? = null, // 簡介
    @SerialName("jp_title") val jpTitle: String? = null,
    @SerialName("link") val link: String? = null, // 鏈接
    @SerialName("media_id") val mediaId: Long? = null, // media id
    @SerialName("mode") val mode: Int? = null,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode? = null,
    @SerialName("payment") val payment: BangumiPayment? = null,
    @SerialName("positive") val positive: BangumiPositive? = null,
    @SerialName("publish") val publish: BangumiPublish? = null,
    @SerialName("rating") val rating: BangumiRating? = null,
    @SerialName("record") val record: String? = null, // 備案號
    @SerialName("rights") val rights: BangumiRights? = null,
    @SerialName("season_id") val seasonId: Long? = null, // ssid
    @SerialName("season_title") val seasonTitle: String? = null,
    @SerialName("seasons") val seasons: List<BangumiSeason> = emptyList(), // 季度信息
    @SerialName("section") val section: List<BangumiSection> = emptyList(), // section 信息, 例如 正片, PV
    @SerialName("series") val series: BangumiSeries? = null, // 系列信息
    @SerialName("share_copy") val shareString: String? = null, // 分享消息
    @SerialName("share_sub_title") val shareSubtitle: String? = null,
    @SerialName("share_url") val shareUrl: String? = null,
    @SerialName("show") val show: BangumiShow? = null,
    @SerialName("square_cover") val squareCover: String? = null,
    @SerialName("stat") val stat: BangumiStat? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("total") val total: Int? = null, // if not finished, -1
    @SerialName("type") val type: BangumiType = BangumiType.UNKNOWN,
    @SerialName("up_info") val owner: BangumiOwner? = null,
    @SerialName("user_status") val userStatus: BangumiUserStatus? = null, // BangumiUserStatus
    @SerialName("freya") val freya: BangumiBubble? = null,
)

@Serializable
public data class BangumiActivity(
    @SerialName("cover") val cover: String? = null,
    @SerialName("head_bg_url") val bannerUrl: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("pendants") val pendants: List<Pendant> = emptyList(),
)

/**
 * 番剧的单集信息
 */
@Serializable
public data class BangumiEpisode(
    @SerialName("aid") val aid: Long? = null, // av 号
    @SerialName("badge") val badge: String? = null, // badge 提示
    @SerialName("badge_info") val badgeInfo: BangumiBadgeInfo? = null, // badge 信息
    @SerialName("badge_type") val badgeType: Int? = null, // badge 类型, 未知
    @SerialName("bvid") val bvId: String? = null, // bv 号
    @SerialName("cid") val cid: Long? = null, // 分 p id
    @SerialName("cover") val cover: String? = null, // 封面链接
    @SerialName("dimension") val dimension: VideoDimension? = null, // 视频分辨率
    @SerialName("duration") val duration: Long? = null, // 时长, ms
    @SerialName("from") val from: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("is_view_hide") val isViewHide: Boolean? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("long_title") val longTitle: String? = null,
    @SerialName("pub_time") val releaseTime: Long? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("pv") val pv: Int? = null,
    @SerialName("rights") val rights: BangumiRights? = null,
    @SerialName("share_copy") val shareCopy: String? = null,
    @SerialName("share_url") val shareUrl: String? = null,
    @SerialName("short_link") val shortLink: String? = null,
    @SerialName("stat") val stat: BangumiStat? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("title") val title: String? = null, // 标题
    @SerialName("vid") val vid: String? = null,
) {
    val durationInSecond: Long? by lazy { duration?.div(1000L) }
}

@Serializable
public data class BangumiBadgeInfo(
    @SerialName("bg_color") val backgroundColor: String? = null,
    @SerialName("bg_color_night") val backgroundColorNight: String? = null,
    @SerialName("text") val text: String? = null,
)

@Serializable
public data class BangumiPayment(
    @SerialName("discount") val discount: Double? = null,
    @SerialName("pay_type") val payType: BangumiPayType? = null,
    @SerialName("price") val price: String? = null,
    @SerialName("promotion") val promotion: String? = null,
    @SerialName("tip") val tip: String? = null,
    @SerialName("view_start_time") val viewStartTime: Long? = null,
    @SerialName("vip_discount") val vipDiscount: Double? = null,
    @SerialName("vip_first_promotion") val vipFirstPromotion: String? = null,
    @SerialName("vip_promotion") val vipPromotion: String? = null,
)

@Serializable
public data class BangumiPayType(
    @SerialName("allow_discount") val allowDiscount: Boolean? = null,
    @SerialName("allow_pack") val allowPack: Boolean? = null,
    @SerialName("allow_ticket") val allowTicket: Boolean? = null,
    @SerialName("allow_time_limit") val hasTimeLimit: Boolean? = null,
    @SerialName("allow_vip_discount") val allowVipDiscount: Boolean? = null,
    @SerialName("forbid_bb") val forbidBCoin: Boolean? = null, // 這個應該多半是 B 幣
)

@Serializable
public data class BangumiPositive(
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
)

@Serializable
public data class BangumiPublish(
    @SerialName("is_finish") val isFinished: Boolean? = null,
    @SerialName("is_started") val isReleased: Boolean? = null,
    @SerialName("pub_time") val releaseTime: String? = null,
    @SerialName("pub_time_show") val releaseTimeShow: String? = null,
    @SerialName("release_date") val releaseDate: String? = null,
    @SerialName("release_date_show") val releaseDateShow: String? = null,
    @SerialName("unknow_pub_date") val unknownReleaseDate: Int? = null, // lol bilibili dev makes a typo
    @SerialName("weekday") val weekday: Int? = null,
)

@Serializable
public data class BangumiRights(
    @SerialName("allow_demand") val allowDemand: Boolean? = null,
    @SerialName("allow_bp") val allowBP: Boolean? = null, // 似乎是 B 幣的意思? idk
    @SerialName("allow_bp_rank") val allowBPRank: Boolean? = null,
    @SerialName("allow_download") val canDownload: Boolean? = null,
    @SerialName("allow_dm") val allowDanmaku: Boolean? = null,
    @SerialName("allow_review") val allowReview: Boolean? = null,
    @SerialName("area_limit") val hasAreaLimit: Boolean? = null,
    @SerialName("ban_area_show") val showBanArea: Boolean? = null,
    @SerialName("can_watch") val canWatch: Boolean? = null,
    @SerialName("copyright") val copyright: String? = null,
    @SerialName("forbid_pre") val forbidPre: Int? = null,
    @SerialName("freya_white") val freyaWhite: Boolean? = null,
    @SerialName("is_cover_show") val isShowCover: Boolean? = null,
    @SerialName("is_preview") val isPreview: Boolean? = null, // PV
    @SerialName("only_vip_download") val isOnlyVipDownload: Boolean? = null,
    @SerialName("resource") val resource: String? = null,
    @SerialName("watch_platform") val watchPlatform: Int? = null,
)

@Serializable
public data class BangumiSeason(
    @SerialName("badge") val badge: String? = null,
    @SerialName("badge_info") val badgeInfo: BangumiBadgeInfo? = null,
    @SerialName("badge_type") val badgeType: Int? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("horizontal_cover_1610") val largeCover: String? = null,
    @SerialName("horizontal_cover_169") val smallCover: String? = null,
    @SerialName("media_id") val mediaId: String? = null,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode? = null,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("season_title") val seasonTitle: String? = null,
    @SerialName("season_type") val seasonType: String? = null,
    @SerialName("stat") val stat: BangumiStat? = null,
)

@Serializable
public data class BangumiSection(
    @SerialName("episode_id") val epId: Long? = null,
    @SerialName("episode_ids") val episodeIds: List<Long> = emptyList(),
    @SerialName("episodes") val episodes: List<BangumiEpisode> = emptyList(),
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("type") val type: BangumiType = BangumiType.UNKNOWN,
)

@Serializable
public data class BangumiSeries(
    @SerialName("series_id") val seriesId: Long? = null,
    @SerialName("series_title") val seriesTitle: String? = null,
    @SerialName("season_count") val seasonCount: Int? = null,
    @SerialName("new_season_id") val newSeasonId: Long? = null,
    @SerialName("series_ord") val seriesOrd: Int? = null,
)

@Serializable
public data class BangumiShow(
    @SerialName("wide_screen") val isWideScreen: Boolean? = null,
)

@Serializable
public data class BangumiStat(
    @SerialName("coin") private val _coin: Double? = null,
    @SerialName("coins") private val _coins: Double? = null,
    @SerialName("danmakus") val danmakus: Int? = null,
    @SerialName("favorite") private val _favorite: Int? = null,
    @SerialName("favorites") private val _favorites: Int? = null,
    @SerialName("series_follow") val seriesFollow: Int? = null,
    @SerialName("series_view") val seriesView: Int? = null,
    @SerialName("likes") val likes: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("share") val share: Int? = null,
    @SerialName("play") val play: Int? = null,
    @SerialName("view") private val _view: Int? = null,
    @SerialName("views") private val _views: Int? = null,
) {
    @Transient
    public val coins: Double? = _coins ?: _coin

    @Transient
    public val collects: Int? = _favorites ?: _favorite

    @Transient
    public val views: Int? = _views ?: _view
}

@Serializable
public data class BangumiOwner(
    @SerialName("avatar") val avatar: String? = null,
    @SerialName("follower") val follower: Int? = null,
    @SerialName("is_follow") val isFollowed: Int? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("theme_type") val themeType: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("verify_type") val verifyType: OfficialRole? = null,
    @SerialName("vip_status") val vipStatus: VipStatus? = null,
    @SerialName("vip_type") val vipType: VipType? = null,
    @SerialName("vip_label") val vipLabel: VipLabel? = null,
)

@Serializable
public data class BangumiUserStatus(
    @SerialName("area_limit") val areaLimit: Int? = null,
    @SerialName("ban_area_show") val banAreaShow: Boolean? = null,
    @SerialName("follow") val isFollow: Boolean? = null,
    @SerialName("follow_status") val followStatus: Int? = null,
    @SerialName("login") val isLogin: Boolean? = null,
    @SerialName("pay") val hasPaid: Boolean? = null,
    @SerialName("pay_pack_paid") val payPackPaid: Boolean? = null,
    @SerialName("sponsor") val isSponsor: Boolean? = null,
)

@Serializable
public data class BangumiBubble(
    @SerialName("bubble_desc") val description: String? = null,
    @SerialName("bubble_show_cnt") val showBubbleCount: Boolean? = null,
    @SerialName("icon_show") val iconShow: Boolean? = null,
)
