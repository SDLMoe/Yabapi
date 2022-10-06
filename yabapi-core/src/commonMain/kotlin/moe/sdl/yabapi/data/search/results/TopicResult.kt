package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class TopicResult(
    @SerialName("type") val type: String,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("pubdate") val pubdate: Long? = null,
    @SerialName("favourite") val favourite: Int? = null,
    @SerialName("review") val review: Int? = null,
    @SerialName("update") val update: Int? = null,
    @SerialName("click") val click: Int? = null,
    @SerialName("tp_type") val topicType: Int? = null,
    @SerialName("tp_id") val tpId: Long? = null,
    @SerialName("keyword") val keyword: String? = null,
    @SerialName("hit_columns") val hitColumns: List<String> = emptyList(),
    @SerialName("arcurl") val url: String? = null,
    @SerialName("rank_offset") val rankOffset: Int? = null,
    @SerialName("rank_index") val rankIndex: Int? = null,
    @SerialName("rank_score") val rankScore: Int? = null,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "topic"
        override fun decode(json: Json, data: JsonObject): TopicResult = json.decodeFromJsonElement(data)
    }
}
