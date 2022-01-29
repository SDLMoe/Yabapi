package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GuardLevel {
    UNKNOWN,

    @SerialName("0")
    NONE, // 无

    @SerialName("3")
    CAPTAIN, // 舰长

    @SerialName("2")
    ADMIRAL, // 提督

    @SerialName("1")
    GOVERNOR, // 总督
}
