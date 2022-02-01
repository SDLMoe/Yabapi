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
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val userName: String? = null,
    @SerialName("roomid") val roomId: Int? = null,
    @SerialName("uface") val avatar: String? = null,
    @SerialName("cate_name") val categoryName: String? = null,
    @SerialName("area") val area: Int? = null,
    @SerialName("area_v2_id") val areaV2Id: Int? = null,
    @SerialName("attentions") val attentions: Int? = null,
    @SerialName("tags") val tags: String? = null,
    @SerialName("is_live") val isLive: Boolean? = null,
    @SerialName("live_status") val liveStatus: LiveRoomStatus? = null,
    @SerialName("live_time") val liveTime: String? = null,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("rank_offset") val rankOffset: Int? = null,
    @SerialName("rank_index") val rankIndex: Int? = null,
    @SerialName("rank_score") val rankScore: Int? = null,
): SearchResult {
    public companion object: ResultFactory() {
        override val code: String = "live_user"
        override fun decode(json: Json, data: JsonObject): LiveUserResult = json.decodeFromJsonElement(data)
    }
}
