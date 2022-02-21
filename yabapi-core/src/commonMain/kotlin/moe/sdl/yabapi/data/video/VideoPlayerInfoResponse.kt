package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.info.LevelInfo
import moe.sdl.yabapi.data.info.UserVip

@Serializable
public data class VideoPlayerInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoPlayerInfo? = null,
)

@Serializable
public data class VideoPlayerInfo(
    @SerialName("aid") val aid: Int? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("allow_bp") val allowBp: Boolean? = null,
    @SerialName("no_share") val noShare: Boolean? = null,
    @SerialName("cid") val cid: Int? = null,
    @SerialName("max_limit") val maxLimit: Int? = null,
    @SerialName("page_no") val pageNo: Int? = null,
    @SerialName("has_next") val hasNext: Boolean? = null,
    @SerialName("ip_info") val ipInfo: IpInfo? = null,
    @SerialName("login_mid") val loginMid: Int? = null,
    @SerialName("login_mid_hash") val loginMidHash: String? = null,
    @SerialName("is_owner") val isOwner: Boolean? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("permission") val permission: String? = null,
    @SerialName("level_info") val levelInfo: LevelInfo? = null,
    @SerialName("vip") val vip: UserVip? = null,
    @SerialName("answer_status") val answerStatus: Int? = null,
    @SerialName("block_time") val blockTime: Long? = null,
    @SerialName("role") val role: String? = null,
    @SerialName("last_play_time") val lastPlayTime: Long? = null,
    @SerialName("last_play_cid") val lastPlayCid: Int? = null,
    @SerialName("now_time") val nowTime: Long? = null,
    @SerialName("online_count") val onlineCount: Int? = null,
    @SerialName("subtitle") val subtitle: VideoSubtitle? = null,
    @SerialName("view_points") val viewPoints: List<ViewPoint> = emptyList(),
    @SerialName("is_ugc_pay_preview") val isUgcPayPreview: Boolean? = null,
    @SerialName("preview_toast") val previewToast: String? = null,
    @SerialName("pcdn_loader") val pcdnLoader: Map<String, PcdnData> = emptyMap(),
    @SerialName("options") val options: PlayerOptions? = null,
    @SerialName("guide_attention") val guideSubscribe: List<GuideSubscribe>? = null,
    @SerialName("jump_card") val jumpCard: JsonArray? = null,
    @SerialName("operation_card") val operationCard: JsonArray? = null,
    @SerialName("online_switch") val onlineSwitch: OnlineSwitch? = null,
    @SerialName("fawkes") val fawkes: Fawkes? = null,
    @SerialName("show_switch") val showSwitch: ShowSwitch? = null,
)

@Serializable
public data class IpInfo(
    @SerialName("ip") val ip: String? = null,
    @SerialName("zone_ip") val zoneIp: String? = null,
    @SerialName("zone_id") val zoneId: Int? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("province") val province: String? = null,
    @SerialName("city") val city: String? = null,
)

@Serializable
public data class ViewPoint(
    @SerialName("type") val type: Int? = null,
    @SerialName("from") val from: Int? = null,
    @SerialName("to") val to: Int? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("imgUrl") val imgUrl: String? = null,
    @SerialName("logoUrl") val logoUrl: String? = null,
)

@Serializable
public data class PcdnData(
    @SerialName("labels") val labels: PcdnLabels? = null,
)

@Serializable
public data class PcdnLabels(
    @SerialName("pcdn_video_type") val pcdnVideoType: String? = null,
    @SerialName("pcdn_stage") val pcdnStage: String? = null,
    @SerialName("pcdn_group") val pcdnGroup: String? = null,
    @SerialName("pcdn_version") val pcdnVersion: String? = null,
    @SerialName("pcdn_vendor") val pcdnVendor: String? = null,
)

@Serializable
public data class PlayerOptions(
    @SerialName("is_360") val is360: Boolean? = null,
    @SerialName("without_vip") val withoutVip: Boolean? = null,
)

@Serializable
public data class GuideSubscribe(
    @SerialName("type") val type: Int? = null,
    @SerialName("from") val from: Int? = null,
    @SerialName("to") val to: Int? = null,
    @SerialName("pos_x") val posX: Double? = null,
    @SerialName("pos_y") val posY: Double? = null,
)

@Serializable
public data class OnlineSwitch(
    @SerialName("enable_gray_dash_playback") val enableGrayDashPlayback: String? = null,
    @SerialName("new_broadcast") val newBroadcast: String? = null,
    @SerialName("realtime_dm") val realtimeDm: String? = null,
    @SerialName("subtitle_submit_switch") val subtitleSubmitSwitch: String? = null,
)

@Serializable
public data class Fawkes(
    @SerialName("config_version") val configVersion: Int? = null,
    @SerialName("ff_version") val ffVersion: Int? = null,
)

@Serializable
public data class ShowSwitch(
    @SerialName("long_progress") val longProgress: Boolean? = null,
)
