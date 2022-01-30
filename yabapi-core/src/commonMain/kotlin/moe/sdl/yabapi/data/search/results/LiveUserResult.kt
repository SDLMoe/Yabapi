package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.live.LiveRoomStatus

@Serializable
public data class LiveUserResult(
    @SerialName("type") val type: String,
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val userName: String,
    @SerialName("roomid") val roomId: Int,
    @SerialName("uface") val avatar: String,
    @SerialName("cate_name") val categoryName: String,
    @SerialName("area") val area: Int,
    @SerialName("area_v2_id") val areaV2Id: Int,
    @SerialName("attentions") val attentions: Int,
    @SerialName("tags") val tags: String,
    @SerialName("is_live") val isLive: Boolean,
    @SerialName("live_status") val liveStatus: LiveRoomStatus,
    @SerialName("live_time") val liveTime: String,
    @SerialName("hit_columns") val hitColumns: List<String>,
    @SerialName("rank_offset") val rankOffset: Int,
    @SerialName("rank_index") val rankIndex: Int,
    @SerialName("rank_score") val rankScore: Int,
): SearchResult {
    public companion object: ResultFactory() {
        override val code: String = "live_user"
        override fun decode(json: Json, data: JsonObject): LiveUserResult = json.decodeFromJsonElement(data)
    }
}
