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
    @SerialName("type") val type: String,
    @SerialName("id") val id: Int,
    @SerialName("author") val author: String,
    @SerialName("mid") val mid: Int,
    @SerialName("typeid") val typeId: String,
    @SerialName("typename") val typename: String,
    @SerialName("arcurl") val avUrl: String,
    @SerialName("aid") val aid: Int,
    @SerialName("bvid") val bvid: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("arcrank") val rank: String,
    @SerialName("pic") val pic: String,
    @SerialName("play") val plays: Int,
    @SerialName("video_review") val danmakus: Int,
    @SerialName("favorites") val collects: Int,
    @SerialName("tag") val tag: String,
    @SerialName("review") val comments: Int,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("senddate") val sendDate: Long,
    @SerialName("duration") val duration: String,
    @SerialName("badgepay") val badgePay: Boolean,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("view_type") val viewType: String,
    @SerialName("is_pay") val isPay: Boolean,
    @SerialName("is_union_video") val isUnionVideo: Boolean,
    @SerialName("rec_tags") val recTags: List<JsonElement>?,
    @SerialName("new_rec_tags") val newRecTags: List<JsonElement> = emptyList(),
    @SerialName("rank_score") val rankScore: Int,
    @SerialName("like") val like: Int,
    @SerialName("upic") val avatar: String,
    @SerialName("corner") val corner: String, // 常为 ""
    @SerialName("cover") val cover: String, // 常为 ""
    @SerialName("desc") val desc: String, // 常为 ""
    @SerialName("url") val url: String, // 常为 ""
    @SerialName("rec_reason") val recommendReason: String, // 常为 ""
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "video"
        override fun decode(json: Json, data: JsonObject): VideoResult = json.decodeFromJsonElement(data)
    }
}
