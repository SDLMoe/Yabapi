package sdl.moe.yabapi.serializer.data.login

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.login.LoginWebResponseCode
import sdl.moe.yabapi.serializer.deserializeEnumWithFallback

internal object LoginWebResponseCodeSerializer : KSerializer<LoginWebResponseCode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this.javaClass.simpleName, PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: LoginWebResponseCode) = encoder.encodeString(value.name)

    override fun deserialize(decoder: Decoder): LoginWebResponseCode =
        deserializeEnumWithFallback(decoder, LoginWebResponseCode.UNKNOWN)
}
