package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class WatchedChangeCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: WatchedChange,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "WATCHED_CHANGE"

        override fun decode(json: Json, data: JsonElement): WatchedChangeCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class WatchedChange(
    @SerialName("num") val num: Int? = null,
    @SerialName("text_small") val textSmall: String? = null,
    @SerialName("text_large") val textLarge: String? = null,
)
