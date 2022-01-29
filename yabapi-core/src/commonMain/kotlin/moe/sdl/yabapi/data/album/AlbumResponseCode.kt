package moe.sdl.yabapi.data.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class AlbumResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("110001")
    ALBUM_NOT_FIND,
}
