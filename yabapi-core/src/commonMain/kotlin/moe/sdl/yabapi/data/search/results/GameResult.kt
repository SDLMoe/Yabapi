package moe.sdl.yabapi.data.search.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class GameResult(
    @SerialName("game_name") val gameName: String,
    @SerialName("game_icon") val gameIcon: String,
    @SerialName("summary") val summary: String,
    @SerialName("game_status") val gameStatus: Int,
    @SerialName("game_link") val gameLink: String,
    @SerialName("grade") val grade: Double,
    @SerialName("book_num") val bookNum: Int,
    @SerialName("download_num") val downloadNum: Int,
    @SerialName("comment_num") val commentNum: Int,
    @SerialName("platform") val platform: String,
) : SearchResult {
    public companion object: ResultFactory() {
        override val code: String = "web_game"
        override fun decode(json: Json, data: JsonObject): GameResult = json.decodeFromJsonElement(data)
    }
}
