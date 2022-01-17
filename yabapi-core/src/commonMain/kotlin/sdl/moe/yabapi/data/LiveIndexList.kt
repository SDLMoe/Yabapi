package sdl.moe.yabapi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.live.LiveRoomStatus

@Serializable
public data class LiveIndexList(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: List<IndexLiveRoomInfo>,
)

@Serializable
public data class IndexLiveRoomInfo(
    @SerialName("user_cover") val userCover: String,
    @SerialName("user_cover_flag") val userCoverFlag: Int,
    @SerialName("area_v2_id") val areaV2Id: Int,
    @SerialName("area") val area: Int,
    @SerialName("area_name") val areaName: String,
    @SerialName("area_v2_parent_id") val areaV2ParentId: Int,
    @SerialName("title") val title: String,
    @SerialName("system_cover") val systemCover: String,
    @SerialName("online") val online: Int,
    @SerialName("roomid") val roomId: Int,
    @SerialName("uname") val username: String,
    @SerialName("uid") val uid: String,
    @SerialName("link") val link: String,
    @SerialName("live_status") val liveStatus: LiveRoomStatus,
    @SerialName("hidden_status") val hiddenStatus: String, // 時間戳, 默認 0000-00-00 00:00:00
    @SerialName("face") val avatar: String,
    @SerialName("area_v2_name") val areaV2Name: String,
    @SerialName("area_v2_parent_name") val areaV2ParentName: String,
    @SerialName("show_cover") val showCover: String,
    @SerialName("pk_id") val pkId: Int,
    @SerialName("flag") val flag: Int,
)
