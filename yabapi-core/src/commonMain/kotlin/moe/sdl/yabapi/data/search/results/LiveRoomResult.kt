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
    @SerialName("title") val title: String? = null,
    @SerialName("uid") val uid: Long? = null,
    @SerialName("roomid") val roomId: Long? = null,
    @SerialName("short_id") val shortId: Long? = null,
    @SerialName("uface") val avatar: String? = null,
    @SerialName("uname") val userName: String? = null,
    @SerialName("user_cover") val userCover: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("live_time") val liveTime: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("area") val area: Int? = null,
    @SerialName("cate_name") val categoryName: String? = null,
    @SerialName("live_status") val roomStatus: LiveRoomStatus? = null,
    @SerialName("online") val online: Int? = null,
    @SerialName("attentions") val attentions: Int? = null,
    @SerialName("tags") val tags: String? = null,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("is_live_room_inline") val isLiveRoomInline: Boolean? = null,
    @SerialName("rank_offset") val rankOffset: Int? = null,
    @SerialName("rank_index") val rankIndex: Int? = null,
    @SerialName("rank_score") val rankScore: Int? = null,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "live_room"
        override fun decode(json: Json, data: JsonObject): LiveRoomResult = json.decodeFromJsonElement(data)
    }
}
