// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.message

import io.ktor.http.ParametersBuilder
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.data.message.MessageType.IMAGE
import sdl.moe.yabapi.data.message.MessageType.RECALL
import sdl.moe.yabapi.data.message.MessageType.TEXT

@Serializable
public data class MessageData(
    val senderUid: Int,
    val receiverId: Int,
    val content: MessageContent,
    val receiverType: Int = 1,
    val devId: String = generateFakeUUID(),
    val timestamp: Long = Clock.System.now().epochSeconds,
) {
    val msgType: MessageType = when(content) {
        is MessageContent.Text -> TEXT
        is MessageContent.Image -> IMAGE
        is MessageContent.Recall -> RECALL
    }
}

public fun MessageData.put(
    builder: ParametersBuilder,
    json: Json = Json,
): ParametersBuilder = builder.apply {
    append("msg[sender_uid]", senderUid.toString())
    append("msg[receiver_id]", receiverId.toString())
    append("msg[receiver_type]", receiverType.toString())
    append("msg[msg_type]", msgType.code.toString())
    append("msg[dev_id]", devId)
    append("msg[timestamp]", timestamp.toString())
    append("msg[content]", json.encodeToString(content))
}

private const val uuidTemplate = "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx"

private val randomRange = 0..15

private fun generateFakeUUID(): String = uuidTemplate.fold("") { acc, char ->
    acc + when (char) {
        'x' -> randomRange.random().toString(16)
        'y' -> (3 and randomRange.random() or 8).toString(16)
        else -> char
    }
}.uppercase()
