@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.info.OfficialRole
import sdl.moe.yabapi.data.info.Pendant
import sdl.moe.yabapi.data.info.VipLabel
import sdl.moe.yabapi.data.info.VipStatus
import sdl.moe.yabapi.data.info.VipType
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
    @SerialName("activity") val activity: BangumiActivity, // 活動
    @SerialName("alias") val alias: String? = null,
    @SerialName("areas") val areas: List<BangumiArea> = emptyList(),
    @SerialName("bkg_cover") val backgroundCover: String, // 背景圖
    @SerialName("cover") val cover: String, // 封面
    @SerialName("episodes") val episodes: List<BangumiEpisode>, // 詳細劇集
    @SerialName("evaluate") val evaluate: String, // 簡介
    @SerialName("jp_title") val jpTitle: String,
    @SerialName("link") val link: String, // 鏈接
    @SerialName("media_id") val mediaId: Int, // media id
    @SerialName("mode") val mode: Int,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode,
    @SerialName("payment") val payment: BangumiPayment,
    @SerialName("positive") val positive: BangumiPositive,
    @SerialName("publish") val publish: BangumiPublish,
    @SerialName("rating") val rating: BangumiRating? = null,
    @SerialName("record") val record: String? = null, // 備案號
    @SerialName("rights") val rights: BangumiRights,
    @SerialName("season_id") val seasonId: Int, // ssid
    @SerialName("season_title") val seasonTitle: String,
    @SerialName("seasons") val seasons: List<BangumiSeason> = emptyList(), // 季度信息
    @SerialName("section") val section: List<BangumiSection> = emptyList(), // section 信息, 例如 正片, PV
    @SerialName("series") val series: BangumiSeries, // 系列信息
    @SerialName("share_copy") val shareString: String, // 分享消息
    @SerialName("share_sub_title") val shareSubtitle: String,
    @SerialName("share_url") val shareUrl: String,
    @SerialName("show") val show: BangumiShow,
    @SerialName("square_cover") val squareCover: String,
    @SerialName("stat") val stat: BanugmiStat,
    @SerialName("status") val status: Int,
    @SerialName("title") val title: String,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("total") val total: Int, // if not finished, -1
    @SerialName("type") val type: Int,
    @SerialName("up_info") val owner: BangumiOwner,
    @SerialName("user_status") val userStatus: BangumiUserStatus, // BangumiUserStatus
    @SerialName("freya") val freya: BangumiBubble,
)

@Serializable
public data class BangumiActivity(
    @SerialName("head_bg_url") val bannerUrl: String,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
)

/**
 * 番剧的单集信息
 */
@Serializable
public data class BangumiEpisode(
    @SerialName("aid") val aid: Int, // av 号
    @SerialName("badge") val badge: String, // badge 提示
    @SerialName("badge_info") val badgeInfo: BangumiBadgeInfo, // badge 信息
    @SerialName("badge_type") val badgeType: Int, // badge 类型, 未知
    @SerialName("bvid") val bvId: String, // bv 号
    @SerialName("cid") val cid: Int, // 分 p id
    @SerialName("cover") val cover: String, // 封面链接
    @SerialName("dimension") val dimension: VideoDimension, // 视频分辨率
    @SerialName("duration") val duration: Long, // 时长
    @SerialName("from") val from: String,
    @SerialName("id") val id: Int,
    @SerialName("is_view_hide") val isViewHide: Boolean,
    @SerialName("link") val link: String,
    @SerialName("long_title") val longTitle: String,
    @SerialName("pub_time") val releaseTime: Long,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("pv") val pv: Int,
    @SerialName("rights") val rights: BangumiRights,
    @SerialName("share_copy") val shareCopy: String,
    @SerialName("share_url") val shareUrl: String,
    @SerialName("short_link") val shortLink: String,
    @SerialName("stat") val stat: BanugmiStat? = null,
    @SerialName("status") val status: Int,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("title") val title: String, // 标题
    @SerialName("vid") val vid: String,
)

@Serializable
public data class BangumiBadgeInfo(
    @SerialName("bg_color") val backgroundColor: String,
    @SerialName("bg_color_night") val backgroundColorNight: String,
    @SerialName("text") val text: String,
)

@Serializable
public data class BangumiPayment(
    @SerialName("discount") val discount: Double,
    @SerialName("pay_type") val payType: BangumiPayType,
    @SerialName("price") val price: String,
    @SerialName("promotion") val promotion: String,
    @SerialName("tip") val tip: String? = null,
    @SerialName("view_start_time") val viewStartTime: Long,
    @SerialName("vip_discount") val vipDiscount: Double,
    @SerialName("vip_first_promotion") val vipFirstPromotion: String,
    @SerialName("vip_promotion") val vipPromotion: String,
)

@Serializable
public data class BangumiPayType(
    @SerialName("allow_discount") val allowDiscount: Boolean,
    @SerialName("allow_pack") val allowPack: Boolean,
    @SerialName("allow_ticket") val allowTicket: Boolean,
    @SerialName("allow_time_limit") val hasTimeLimit: Boolean,
    @SerialName("allow_vip_discount") val allowVipDiscount: Boolean,
    @SerialName("forbid_bb") val forbidBCoin: Boolean, // 這個應該多半是 B 幣
)

@Serializable
public data class BangumiPositive(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
)

@Serializable
public data class BangumiPublish(
    @SerialName("is_finish") val isFinished: Boolean,
    @SerialName("is_started") val isReleased: Boolean,
    @SerialName("pub_time") val releaseTime: String,
    @SerialName("pub_time_show") val releaseTimeShow: String,
    @SerialName("unknow_pub_date") val unknownReleaseDate: Int, // lol bilibili dev makes a typo
    @SerialName("weekday") val weekday: Int,
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
    @SerialName("is_cover_show") val isShowCover: Boolean? = null,
    @SerialName("is_preview") val isPreview: Boolean? = null, // PV
    @SerialName("only_vip_download") val isOnlyVipDownload: Boolean? = null,
    @SerialName("resource") val resource: String? = null,
    @SerialName("watch_platform") val watchPlatform: Int? = null,
)

@Serializable
public data class BangumiSeason(
    @SerialName("badge") val badge: String,
    @SerialName("badge_info") val badgeInfo: BangumiBadgeInfo,
    @SerialName("badge_type") val badgeType: Int,
    @SerialName("cover") val cover: String? = null,
    @SerialName("horizontal_cover_1610") val largeCover: String? = null,
    @SerialName("horizontal_cover_169") val smallCover: String? = null,
    @SerialName("media_id") val mediaId: String,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode,
    @SerialName("season_id") val seasonId: Int,
    @SerialName("season_title") val seasonTitle: String,
    @SerialName("season_type") val seasonType: String,
    @SerialName("stat") val stat: BanugmiStat,
)

@Serializable
public data class BangumiSection(
    @SerialName("episode_id") val epId: Int,
    @SerialName("episodes") val episodes: List<BangumiEpisode> = emptyList(),
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("type") val type: Int,
)

@Serializable
public data class BangumiSeries(
    @SerialName("series_id") val seriesId: Int,
    @SerialName("series_title") val seriesTitle: String,
)

@Serializable
public data class BangumiShow(
    @SerialName("wide_screen") val isWideScreen: Boolean,
)

@Serializable
public data class BanugmiStat(
    @SerialName("coin") private val _coin: Int? = null,
    @SerialName("coins") private val _coins: Int? = null,
    @SerialName("danmakus") val danmakus: Int? = null,
    @SerialName("favorite") private val _favorite: Int? = null,
    @SerialName("favorites") private val _favorites: Int? = null,
    @SerialName("series_follow") val seriesFollow: Int? = null,
    @SerialName("likes") val likes: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("share") val share: Int? = null,
    @SerialName("play") val play: Int? = null,
    @SerialName("views") val views: Int? = null,
) {
    @Transient
    public val coins: Int? = _coins ?: _coin

    @Transient
    public val collects: Int? = _favorites ?: _favorite
}

@Serializable
public data class BangumiOwner(
    @SerialName("avatar") val avatar: String,
    @SerialName("follower") val follower: Int,
    @SerialName("is_follow") val isFollowed: Int,
    @SerialName("mid") val mid: Int,
    @SerialName("pendant") val pendant: Pendant,
    @SerialName("theme_type") val themeType: Int,
    @SerialName("uname") val username: String,
    @SerialName("verify_type") val verifyType: OfficialRole,
    @SerialName("vip_status") val vipStatus: VipStatus,
    @SerialName("vip_type") val vipType: VipType,
    @SerialName("vip_label") val vipLabel: VipLabel,
)

@Serializable
public data class BangumiUserStatus(
    @SerialName("area_limit") val areaLimit: Boolean,
    @SerialName("ban_area_show") val banAreaShow: Boolean,
    @SerialName("follow") val isFollow: Boolean,
    @SerialName("follow_status") val followStatus: Int,
    @SerialName("login") val isLogin: Boolean,
    @SerialName("pay") val hasPaid: Boolean,
    @SerialName("pay_pack_paid") val payPackPaid: Boolean,
    @SerialName("sponsor") val isSponsor: Boolean,
)

@Serializable
public data class BangumiBubble(
    @SerialName("bubble_desc") val description: String? = null,
    @SerialName("bubble_show_cnt") val showBubbleCount: Boolean,
    @SerialName("icon_show") val iconShow: Boolean,
)
