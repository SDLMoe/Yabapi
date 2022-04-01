package moe.sdl.yabapi.data.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class CommentResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    ERROR_REQUEST,

    @SerialName("-404")
    NOT_FOUND,

    @SerialName("12002")
    CLOSED,

    @SerialName("12009")
    TYPE_INVALID,
}
