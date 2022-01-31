package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class MessageSendResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: MessageSendResponseData? = null,
)

@Serializable
public data class MessageSendResponseData(
    @SerialName("msg_key") val key: Long? = null,
    @SerialName("msg_content") val content: String? = null,
    @SerialName("key_hit_infos") val infos: JsonObject? = null,
)
