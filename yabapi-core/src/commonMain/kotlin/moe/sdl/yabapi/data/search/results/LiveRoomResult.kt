@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.live.LiveRoomStatus
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveRoomResult(
    @SerialName("title") val title: String,
    @SerialName("uid") val uid: Int,
    @SerialName("roomid") val roomId: Int,
    @SerialName("short_id") val shortId: Int,
    @SerialName("uface") val avatar: String,
    @SerialName("uname") val userName: String,
    @SerialName("user_cover") val userCover: String,
    @SerialName("cover") val cover: String,
    @SerialName("live_time") val liveTime: String,
    @SerialName("type") val type: String,
    @SerialName("area") val area: Int,
    @SerialName("cate_name") val categoryName: String,
    @SerialName("live_status") val roomStatus: LiveRoomStatus,
    @SerialName("online") val online: Int,
    @SerialName("attentions") val attentions: Int,
    @SerialName("tags") val tags: String,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("is_live_room_inline") val isLiveRoomInline: Boolean,
    @SerialName("rank_offset") val rankOffset: Int,
    @SerialName("rank_index") val rankIndex: Int,
    @SerialName("rank_score") val rankScore: Int,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "live_room"
        override fun decode(json: Json, data: JsonObject): LiveRoomResult = json.decodeFromJsonElement(data)
    }
}
