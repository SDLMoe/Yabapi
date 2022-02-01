package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveRoomInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN, // 未找到该房间 -> 1
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveRoomData? = null,
)

@Serializable
public data class LiveRoomData(
    @SerialName("uid") val uid: Int,
    @SerialName("room_id") val roomId: Int,
    @SerialName("short_id") val shortId: Int? = null, // 官方定制房间号
    @SerialName("attention") val attention: Int? = null,
    @SerialName("online") val online: Int? = null,
    @SerialName("is_portrait") val portrait: Boolean? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("live_status") val liveStatus: Int? = null,
    @SerialName("area_id") val areaId: Int? = null, // 子区 ID?
    @SerialName("parent_area_id") val parentAreaId: Int? = null,
    @SerialName("parent_area_name") val parentAreaName: String? = null,
    @SerialName("old_area_id") val oldAreaId: Int? = null,
    @SerialName("background") val background: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("user_cover") val userCover: String? = null,
    @SerialName("keyframe") val keyFrame: String? = null,
    @SerialName("is_strict_room") val isStrictRoom: Boolean? = null,
    @SerialName("live_time") val liveTime: String? = null, // 格式为 yyyy-MM-DD HH:mm:ss
    @SerialName("tags") val tags: String? = null,
    @SerialName("is_anchor") val isAnchor: Int? = null,
    @SerialName("room_silent_type") val roomSilentType: String? = null,
    @SerialName("room_silent_level") val roomSilentLevel: Int? = null,
    @SerialName("room_silent_second") val roomSilentSecond: Int? = null,
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("pendants") val pendants: String? = null, // blank
    @SerialName("area_pendants") val areaPendants: String? = null, // blank
    @SerialName("new_pendants") val newPendants: JsonObject? = null,
    @SerialName("up_session") val upSession: String? = null,
    @SerialName("pk_status") val pkStatus: Int? = null,
    @SerialName("pk_id") val pkId: Int? = null,
    @SerialName("battle_id") val battleId: Int? = null,
    @SerialName("allow_change_area_time") val allowChangeAreaTime: Int? = null,
    @SerialName("allow_upload_cover_time") val allowUploadCoverTime: Int? = null,
    @SerialName("hot_words") val hotWords: List<String> = emptyList(),
    @SerialName("studio_info") val studioInfo: JsonObject? = null,
    @SerialName("hot_words_status") val hotWordsStatus: Int? = null,
    @SerialName("verify") val verify: JsonElement? = null,
)
