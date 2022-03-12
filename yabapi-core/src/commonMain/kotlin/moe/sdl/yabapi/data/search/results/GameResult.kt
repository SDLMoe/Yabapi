package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class GameResult(
    @SerialName("game_name") val gameName: String? = null,
    @SerialName("game_icon") val gameIcon: String? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("game_status") val gameStatus: Int? = null,
    @SerialName("game_link") val gameLink: String? = null,
    @SerialName("grade") val grade: Double? = null,
    @SerialName("book_num") val bookNum: Int? = null,
    @SerialName("download_num") val downloadNum: Int? = null,
    @SerialName("comment_num") val commentNum: Int? = null,
    @SerialName("platform") val platform: String? = null,
) : SearchResult {
    public companion object : ResultFactory() {
        override val code: String = "web_game"
        override fun decode(json: Json, data: JsonObject): GameResult = json.decodeFromJsonElement(data)
    }
}
