package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VideoInfoGetCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("-404")
    NOT_FOUND,

    @SerialName("62002")
    VIDEO_INVISIBLE;
}
