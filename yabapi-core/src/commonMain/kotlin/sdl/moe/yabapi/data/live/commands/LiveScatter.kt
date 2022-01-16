package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LiveScatter(
    @SerialName("max") val max: Int,
    @SerialName("min") val min: Int,
)
