package sdl.moe.yabapi.serializer.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.enums.video.VideoType

public object VideoTypeSerializer : KSerializer<VideoType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("VideoTypeSerializer", INT)
    override fun serialize(encoder: Encoder, value: VideoType): Unit = encoder.encodeInt(value.tid)
    override fun deserialize(decoder: Decoder): VideoType = VideoType.fromTid(decoder.decodeInt())
}
