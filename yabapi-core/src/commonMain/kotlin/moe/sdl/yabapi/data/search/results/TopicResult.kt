package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class TopicResult(
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("cover") val cover: String,
    @SerialName("author") val author: String,
    @SerialName("mid") val mid: Int,
    @SerialName("pubdate") val pubdate: Long,
    @SerialName("favourite") val favourite: Int,
    @SerialName("review") val review: Int,
    @SerialName("update") val update: Int,
    @SerialName("click") val click: Int,
    @SerialName("tp_type") val topicType: Int,
    @SerialName("tp_id") val tpId: Int,
    @SerialName("keyword") val keyword: String,
    @SerialName("hit_columns") val hitColumns: List<String>,
    @SerialName("arcurl") val url: String,
    @SerialName("rank_offset") val rankOffset: Int,
    @SerialName("rank_index") val rankIndex: Int,
    @SerialName("rank_score") val rankScore: Int,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "topic"
        override fun decode(json: Json, data: JsonObject): TopicResult = json.decodeFromJsonElement(data)
    }
}
