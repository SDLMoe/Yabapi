package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class ActivityResult(
    @SerialName("status") val status: Int,
    @SerialName("author") val author: String,
    @SerialName("url") val url: String,
    @SerialName("title") val title: String,
    @SerialName("cover") val cover: String,
    @SerialName("pos") val pos: Int,
    @SerialName("card_type") val cardType: Int,
    @SerialName("state") val state: Int,
    @SerialName("corner") val corner: String,
    @SerialName("card_value") val cardValue: String,
    @SerialName("type") val type: String,
    @SerialName("id") val id: Int,
    @SerialName("desc") val desc: String,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "activity"
        override fun decode(json: Json, data: JsonObject): ActivityResult = json.decodeFromJsonElement(data)
    }
}
