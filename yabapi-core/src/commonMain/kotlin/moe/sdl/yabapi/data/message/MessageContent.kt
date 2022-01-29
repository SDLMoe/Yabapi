package moe.sdl.yabapi.data.message

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
public sealed class MessageContent {

    public abstract val code: Int

    @Serializable
    public data class Text(
        @SerialName("content") val content: String,
    ) : MessageContent() {
        @Transient override val code: Int = 1
    }

    @Serializable
    public data class Image(
        @SerialName("url") val url: String,
    ) : MessageContent() {
        @Transient override val code: Int = 2
    }

    @Serializable
    public data class Recall(
        public val key: String,
    ) : MessageContent() {
        @Transient override val code: Int = 5

        public companion object : KSerializer<Recall> {
            override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("MessageRecall", PrimitiveKind.STRING)
            override fun serialize(encoder: Encoder, value: Recall): Unit = encoder.encodeString(value.key)
            override fun deserialize(decoder: Decoder): Recall = Recall(decoder.decodeString())
        }
    }
}
