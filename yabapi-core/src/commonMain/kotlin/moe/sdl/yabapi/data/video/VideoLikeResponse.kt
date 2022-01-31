package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.video.VideoLikeResponseCode.UNKNOWN

@Serializable
public data class VideoLikeResponse(
    @SerialName("code") val code: VideoLikeResponseCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: String? = null,
)

@Serializable
public enum class VideoLikeResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-111")
    CSRF_INVALID,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("10003")
    NOT_FOUND,

    @SerialName("65004")
    CANCEL_FAILED,

    @SerialName("65006")
    LIKED_BEFORE,
}
