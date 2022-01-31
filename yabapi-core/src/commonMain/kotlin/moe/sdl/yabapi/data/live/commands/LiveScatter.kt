package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LiveScatter(
    @SerialName("max") val max: Int? = null,
    @SerialName("min") val min: Int? = null,
)
