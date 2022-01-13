// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.message

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public sealed class MessageContent {
    @Serializable
    public data class Text(
        @SerialName("content") val content: String,
    ) : MessageContent()

    @Serializable
    public data class Image(
        @SerialName("url") val url: String,
    ) : MessageContent()

    @Serializable
    public data class Recall(
        public val key: String,
    ) : MessageContent() {
        public companion object : KSerializer<Recall> {
            override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MessageRecall", PrimitiveKind.STRING)
            override fun serialize(encoder: Encoder, value: Recall): Unit = encoder.encodeString(value.key)
            override fun deserialize(decoder: Decoder): Recall = Recall(decoder.decodeString())
        }
    }
}
