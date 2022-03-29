package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.data.message.contents.ContentFactory
import moe.sdl.yabapi.data.message.contents.RecvContent
import moe.sdl.yabapi.util.Logger
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("RecvMessage") }

@SharedImmutable
private val json by lazy {
    Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}

@Serializable
public data class RecvMessage(
    @SerialName("sender_uid") val senderUid: Int? = null,
    @SerialName("receiver_type") val receiverType: Int? = null,
    @SerialName("receiver_id") val receiverId: Int? = null,
    @SerialName("msg_type") val msgType: Int? = null,
    @SerialName("content") private val _content: String? = null,
    @SerialName("msg_seqno") val messageSeq: ULong? = null,
    @SerialName("timestamp") val timestamp: ULong? = null,
    @SerialName("at_uids") val atUids: List<Int> = emptyList(),
    @SerialName("msg_key") val key: ULong? = null,
    @SerialName("msg_status") val status: Int? = null,
    @SerialName("notify_code") val notifyCode: String? = null,
    @SerialName("new_face_version") val newFaceVersion: Int? = null,
) {
    public val content: RecvContent? by lazy {
        if (_content == null) return@lazy null
        if (msgType == null) return@lazy null
        ContentFactory.map[msgType]?.decode(json, _content) ?: run {
            logger.warn { "Unknown message content type $msgType, raw string: $_content" }
            null
        }
    }
}
