package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import sdl.moe.yabapi.data.live.LiveRoomStatus
import sdl.moe.yabapi.data.live.LiveRoomStatus.UNKNOWN
import sdl.moe.yabapi.enums.feed.FeedType.LIVE

@Serializable
public data class LiveCard(
    @SerialName("live_play_info") val livePlayInfo: LiveCardInfo,
    @SerialName("live_record_info") val liveRecordInfo: JsonElement? = null,
    @SerialName("style") val style: Int,
    @SerialName("type") val type: Int,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = LIVE.code
        override fun decode(json: Json, data: String): LiveCard = json.decodeFromString(data)
    }
}

@Serializable
public data class LiveCardInfo(
    @SerialName("area_id") val areaId: Int,
    @SerialName("area_name") val areaName: String,
    @SerialName("cover") val cover: String,
    @SerialName("link") val link: String,
    @SerialName("live_id") val liveId : ULong,
    @SerialName("live_screen_type") val liveScreenType: Int,
    @SerialName("live_start_time") val liveStartTime: Long,
    @SerialName("live_status") val liveStatus: LiveRoomStatus = UNKNOWN,
    @SerialName("online") val online: Int,
    @SerialName("parent_area_id") val parentAreaId: Int,
    @SerialName("parent_area_name") val parentAreaName: String,
    @SerialName("play_type") val playType: Int,
    @SerialName("room_id") val roomId: Int,
    @SerialName("room_type") val roomType: Int,
    @SerialName("title") val title: String,
    @SerialName("uid") val uid: Int,
)
