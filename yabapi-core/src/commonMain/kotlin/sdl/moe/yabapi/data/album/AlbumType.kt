package sdl.moe.yabapi.data.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class AlbumType {
    UNKNOWN,

    @SerialName("1")
    DRAW,

    @SerialName("2")
    CAMERA,

    @SerialName("3")
    DAILY,
    ;
}
