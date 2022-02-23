package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class ActivityResult(
    @SerialName("status") val status: Int? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("pos") private val _pos: Int? = null,
    @SerialName("position") private val _position: Int? = null,
    @SerialName("card_type") val cardType: Int? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("corner") val corner: String? = null,
    @SerialName("card_value") val cardValue: String? = null,
    @SerialName("type") val type: String? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("desc") val desc: String? = null,
) : SearchResult {
    val pos: Int?
        get() = _pos ?: _position

    public companion object : ResultFactory() {
        override val code: String = "activity"
        override fun decode(json: Json, data: JsonObject): ActivityResult = json.decodeFromJsonElement(data)
    }
}
