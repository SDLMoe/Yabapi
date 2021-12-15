package sdl.moe.yabapi.serializer.data.login

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.login.QueryCaptchaResponseCode
import sdl.moe.yabapi.util.getEnumFieldAnnotation

private typealias T = QueryCaptchaResponseCode

object QueryCaptchaResponseCodeSerializer : KSerializer<T> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this.javaClass.simpleName, PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: T) = encoder.encodeString(value.name)

    override fun deserialize(decoder: Decoder): T {
        return decoder.decodeString().let { value ->
            T.values()
                .firstOrNull { it.getEnumFieldAnnotation<SerialName>()?.value == value || it.name == value }
                ?: run { T.UNKNOWN }
        }
    }
}
