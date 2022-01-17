package sdl.moe.yabapi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Gender {
    UNKNOWN,

    @SerialName("-1")
    PRIVATE,

    @SerialName("0")
    FEMALE,

    @SerialName("1")
    MALE,
    ;
}
