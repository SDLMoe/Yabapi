package sdl.moe.yabapi.serializer.data.login

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.login.LoginWebQRCodeResponseFailedCode
import sdl.moe.yabapi.data.login.LoginWebQRCodeResponseFailedCode.UNKNOWN
import sdl.moe.yabapi.serializer.deserializeEnumWithFallback

internal object LoginWebQRCodeResponseFailedCodeSerializer : KSerializer<LoginWebQRCodeResponseFailedCode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this.javaClass.simpleName, INT)

    override fun serialize(encoder: Encoder, value: LoginWebQRCodeResponseFailedCode) = encoder.encodeString(value.name)

    override fun deserialize(decoder: Decoder): LoginWebQRCodeResponseFailedCode =
        deserializeEnumWithFallback(decoder, UNKNOWN)
}
