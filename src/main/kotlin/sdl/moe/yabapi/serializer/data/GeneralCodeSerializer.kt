package sdl.moe.yabapi.serializer.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.deserializeEnumWithFallback

internal class GeneralCodeSerializer : KSerializer<GeneralCode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this.javaClass.simpleName, INT)

    override fun serialize(encoder: Encoder, value: GeneralCode) = encoder.encodeString(value.name)

    override fun deserialize(decoder: Decoder): GeneralCode =
        deserializeEnumWithFallback(decoder, UNKNOWN)
}
