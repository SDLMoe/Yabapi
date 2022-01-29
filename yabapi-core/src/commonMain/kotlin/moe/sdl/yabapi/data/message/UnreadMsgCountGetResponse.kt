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
    @SerialName("at") val at: Int, // 未读 at 数
    @SerialName("chat") val chat: Int,
    @SerialName("like") val like: Int, // 未读点赞数
    @SerialName("reply") val reply: Int, // 未读回复数
    @SerialName("sys_msg") val system: Int, // 未读系统消息数
    @SerialName("up") val up: Int, // 未读UP主助手消息数
)
