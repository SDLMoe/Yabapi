package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class OnlineRankCountCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: OnlineRankCountData? = null,
) : LiveCommand {
    inline val count: Int? // shortcut for `data.count`
        get() = data?.count

    @Serializable
    public data class OnlineRankCountData(
        @SerialName("count") val count: Int? = null,
    )

    public companion object : LiveCommandFactory() {
        override val operation: String = "ONLINE_RANK_COUNT"
        override fun decode(json: Json, data: JsonElement): OnlineRankCountCmd = json.decodeFromJsonElement(data)
    }
}
