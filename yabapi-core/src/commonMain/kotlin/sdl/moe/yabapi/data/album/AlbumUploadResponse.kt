package sdl.moe.yabapi.data.album

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.album.AlbumUploadResponseCode.UNKNOWN

@Serializable
public data class AlbumUploadResponse(
    @SerialName("code") val code: AlbumUploadResponseCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: AlbumUploadData? = null,
)

@Serializable
public enum class AlbumUploadResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-1")
    NO_IMAGE,

    @SerialName("-2")
    INVALID_PARAMS,

    @SerialName("-3")
    TOO_SMALL,

    @SerialName("-4")
    NOT_LOGIN,

    @SerialName("-7")
    ERROR_INFO,
    ;
}

@Serializable
public data class AlbumUploadData(
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("image_width") val imageWidth: Int? = null,
    @SerialName("image_height") val imageHeight: Int? = null,
)
