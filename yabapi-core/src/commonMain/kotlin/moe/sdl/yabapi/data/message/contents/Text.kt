package moe.sdl.yabapi.data.message.contents

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.data.message.MessageType

@Serializable
public data class Text(
    @SerialName("content") val content: String? = null,
) : RecvContent {
    public companion object : ContentFactory<Text>() {
        override val code: Int = MessageType.TEXT.code
        override fun decode(json: Json, data: String): Text = json.decodeFromString(data)
    }
}
