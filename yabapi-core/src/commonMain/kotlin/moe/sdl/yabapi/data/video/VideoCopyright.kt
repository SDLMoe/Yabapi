package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VideoCopyright {
    UNKNOWN,

    @SerialName("1")
    ORIGINAL,

    @SerialName("2")
    REPRINT;
}
