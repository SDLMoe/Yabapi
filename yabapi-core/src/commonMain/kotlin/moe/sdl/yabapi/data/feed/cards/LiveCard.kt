package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.data.live.LiveRoomStatus
import moe.sdl.yabapi.data.live.LiveRoomStatus.UNKNOWN
import moe.sdl.yabapi.enums.feed.FeedType.LIVE

@Serializable
public data class LiveCard(
    @SerialName("live_play_info") val livePlayInfo: LiveCardInfo? = null,
    @SerialName("live_record_info") val liveRecordInfo: JsonElement? = null,
    @SerialName("style") val style: Int? = null,
    @SerialName("type") val type: Int? = null,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = LIVE.code
        override fun decode(json: Json, data: String): LiveCard = json.decodeFromString(data)
    }
}

@Serializable
public data class LiveCardInfo(
    @SerialName("area_id") val areaId: Int? = null,
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("live_id") val liveId : ULong,
    @SerialName("live_screen_type") val liveScreenType: Int? = null,
    @SerialName("live_start_time") val liveStartTime: Long? = null,
    @SerialName("live_status") val liveStatus: LiveRoomStatus = UNKNOWN,
    @SerialName("online") val online: Int? = null,
    @SerialName("parent_area_id") val parentAreaId: Int? = null,
    @SerialName("parent_area_name") val parentAreaName: String? = null,
    @SerialName("play_type") val playType: Int? = null,
    @SerialName("room_id") val roomId: Int? = null,
    @SerialName("room_type") val roomType: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("uid") val uid: Int? = null,
)
