package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class ArticleResult(
    @SerialName("type") val type: String,
    @SerialName("title") val title: String,
    @SerialName("desc") val description: String,
    @SerialName("id") val id: Int,
    @SerialName("mid") val mid: Int,
    @SerialName("template_id") val templateId: Int,
    @SerialName("category_id") val categoryId: Int,
    @SerialName("category_name") val categoryName: String,
    @SerialName("like") val like: Int,
    @SerialName("view") val view: Int,
    @SerialName("reply") val reply: Int,
    @SerialName("pub_time") val releaseTime: Long,
    @SerialName("rank_index") val rankIndex: Int,
    @SerialName("rank_offset") val rankOffset: Int,
    @SerialName("rank_score") val rankScore: Int,
    @SerialName("image_urls") val imageUrls: List<String>? = null,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "article"
        override fun decode(json: Json, data: JsonObject): ArticleResult = json.decodeFromJsonElement(data)
    }
}
