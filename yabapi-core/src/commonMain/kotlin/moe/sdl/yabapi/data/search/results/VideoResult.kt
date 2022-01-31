@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoResult(
    @SerialName("type") val type: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("typeid") val typeId: String? = null,
    @SerialName("typename") val typename: String? = null,
    @SerialName("arcurl") val avUrl: String? = null,
    @SerialName("aid") val aid: Int? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("arcrank") val rank: String? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("play") val plays: Int? = null,
    @SerialName("video_review") val danmakus: Int? = null,
    @SerialName("favorites") val collects: Int? = null,
    @SerialName("tag") val tag: String? = null,
    @SerialName("review") val comments: Int? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("senddate") val sendDate: Long? = null,
    @SerialName("duration") val duration: String? = null,
    @SerialName("badgepay") val badgePay: Boolean? = null,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("view_type") val viewType: String? = null,
    @SerialName("is_pay") val isPay: Boolean? = null,
    @SerialName("is_union_video") val isUnionVideo: Boolean? = null,
    @SerialName("rec_tags") val recTags: List<JsonElement> = emptyList(),
    @SerialName("new_rec_tags") val newRecTags: List<JsonElement> = emptyList(),
    @SerialName("rank_score") val rankScore: Int? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("upic") val avatar: String? = null,
    @SerialName("corner") val corner: String? = null, // 常为 ""
    @SerialName("cover") val cover: String? = null, // 常为 ""
    @SerialName("desc") val desc: String? = null, // 常为 ""
    @SerialName("url") val url: String? = null, // 常为 ""
    @SerialName("rec_reason") val recommendReason: String? = null, // 常为 ""
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "video"
        override fun decode(json: Json, data: JsonObject): VideoResult = json.decodeFromJsonElement(data)
    }
}
