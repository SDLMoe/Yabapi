package moe.sdl.yabapi.serializer.data.stream

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import moe.sdl.yabapi.data.stream.QnQuality

public object QnQualitySerializer : KSerializer<QnQuality> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("VideoQnQualitySerializer", INT)
    override fun serialize(encoder: Encoder, value: QnQuality): Unit = encoder.encodeInt(value.code)
    override fun deserialize(decoder: Decoder): QnQuality = QnQuality.fromCode(decoder.decodeInt())
}
