package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class SpaceResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("53013")
    PRIVACY_LIMITED,
    ;
}
