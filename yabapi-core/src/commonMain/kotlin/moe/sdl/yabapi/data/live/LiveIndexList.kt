package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.intOrNull
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class LiveIndexList(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: List<IndexLiveRoomInfo> = emptyList(),
)

@Serializable
public data class IndexLiveRoomInfo(
    @SerialName("user_cover") val userCover: String? = null,
    @SerialName("user_cover_flag") val userCoverFlag: Int? = null,
    @SerialName("area_v2_id") val areaV2Id: Int? = null,
    @SerialName("area") val area: Int? = null,
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("area_v2_parent_id") val areaV2ParentId: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("system_cover") val systemCover: String? = null,
    @SerialName("online") val online: Int? = null,
    @SerialName("roomid") val roomId: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("uid") val uid: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("live_status") val liveStatus: LiveRoomStatus? = null,
    @SerialName("hidden_status") val hiddenStatus: String? = null, // 時間戳, 默認 0000-00-00 00:00:00
    @SerialName("face") val avatar: String? = null,
    @SerialName("area_v2_name") val areaV2Name: String? = null,
    @SerialName("area_v2_parent_name") val areaV2ParentName: String? = null,
    @SerialName("show_cover") val showCover: String? = null,
    @SerialName("pk_id") val pkId: Int? = null,
    @SerialName("flag") val flag: Int? = null,
    @SerialName("watched_show") val watchedShow: WatchedShow? = null,
)

@Serializable
public data class WatchedShow(
    @SerialName("switch") val switch: Boolean? = null,
    @SerialName("num") val num: Int? = null,
    @SerialName("text_small") val textSmall: String? = null,
    @SerialName("text_large") val textLarge: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("icon_location") private val _iconLocation: JsonElement? = null,
    @SerialName("icon_web") val iconWeb: String? = null,
) {
    val iconLocation: Int? by lazy {
        if (_iconLocation is JsonPrimitive) {
            _iconLocation.intOrNull
        } else null
    }
}
