package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class UnreadMsgCountGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: MsgUnreadCount? = null,
)

@Serializable
public data class MsgUnreadCount(
    @SerialName("at") val at: Int? = null, // 未读 at 数
    @SerialName("chat") val chat: Int? = null,
    @SerialName("like") val like: Int? = null, // 未读点赞数
    @SerialName("reply") val reply: Int? = null, // 未读回复数
    @SerialName("sys_msg") val system: Int? = null, // 未读系统消息数
    @SerialName("up") val up: Int? = null, // 未读UP主助手消息数
)
