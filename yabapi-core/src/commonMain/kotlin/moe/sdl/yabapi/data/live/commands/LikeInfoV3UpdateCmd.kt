package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class LikeInfoV3UpdateCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: LikeInfoV3? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "LIKE_INFO_V3_UPDATE"
        override fun decode(json: Json, data: JsonElement): InteractWordCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class LikeInfoV3(
    @SerialName("click_count") val clickCount: Long? = null,
)
