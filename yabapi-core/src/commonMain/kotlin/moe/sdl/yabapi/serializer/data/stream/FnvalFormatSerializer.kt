package moe.sdl.yabapi.serializer.data.stream

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import moe.sdl.yabapi.data.stream.VideoFnvalFormat

public object FnvalFormatSerializer : KSerializer<VideoFnvalFormat> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("VideoFnvalFormatSerializer", INT)
    override fun serialize(encoder: Encoder, value: VideoFnvalFormat): Unit = encoder.encodeInt(value.toBinary())
    override fun deserialize(decoder: Decoder): VideoFnvalFormat = VideoFnvalFormat.fromBinary(decoder.decodeInt())
}
