package sdl.moe.yabapi.serializer.data.video

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.video.VideoAttribute

public object VideoAttributeSerializer : KSerializer<VideoAttribute> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("VideoAttribute", PrimitiveKind.INT)
    override fun deserialize(decoder: Decoder): VideoAttribute = VideoAttribute.decode(decoder.decodeInt())
    override fun serialize(encoder: Encoder, value: VideoAttribute): Unit = encoder.encodeInt(value.encode())
}
