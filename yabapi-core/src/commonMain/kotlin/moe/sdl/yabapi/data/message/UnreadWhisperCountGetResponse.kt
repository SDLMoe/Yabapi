package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.message.UnreadWhisperCode.UNKNOWN

@Serializable
public data class UnreadWhisperCountGetResponse(
    @SerialName("code") val code: UnreadWhisperCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: UnreadWhisperData? = null,
)

@Serializable
public enum class UnreadWhisperCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-6")
    NOT_LOGIN,
}

@Serializable
public data class UnreadWhisperData(
    @SerialName("unfollow_unread") val unfollowUnread: Int? = null,
    @SerialName("follow_unread") val followUnread: Int? = null,
    @SerialName("unfollow_push_msg") val unfollowPushMsg: String? = null,
    @SerialName("dustbin_push_msg") val dustbinPushMsg: Int? = null,
    @SerialName("dustbin_unread") val dustbinUnread: Int? = null,
)
