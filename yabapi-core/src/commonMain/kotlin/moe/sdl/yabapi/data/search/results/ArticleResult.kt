package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class ArticleResult(
    @SerialName("type") val type: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("template_id") val templateId: Int? = null,
    @SerialName("category_id") val categoryId: Int? = null,
    @SerialName("category_name") val categoryName: String? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("view") val view: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("pub_time") val releaseTime: Long? = null,
    @SerialName("rank_index") val rankIndex: Int? = null,
    @SerialName("rank_offset") val rankOffset: Int? = null,
    @SerialName("rank_score") val rankScore: Int? = null,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "article"
        override fun decode(json: Json, data: JsonObject): ArticleResult = json.decodeFromJsonElement(data)
    }
}
