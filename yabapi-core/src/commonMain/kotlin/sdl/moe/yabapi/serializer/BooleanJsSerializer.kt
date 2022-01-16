package sdl.moe.yabapi.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import sdl.moe.yabapi.exception.UnsupportedDecoderException

/**
 * Boolean Serializer Wrapper for JS
 *
 * F**k JavaScript, with full of mess
 *
 * Example to specify Serializer
 * ```
 * @Serializable
 * data class Example(
 *     @Serializable(with = BooleanJsSerializer::class)
 *     val bool: Boolean
 * )
 * ```
 *
 * Or file-level annotation
 * ```
 * @file:UseSerializers(BooleanJsSerializer::class)
 * ```
 */
internal object BooleanJsSerializer : KSerializer<Boolean> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BooleanIntSerializer", PrimitiveKind.BOOLEAN)

    override fun serialize(encoder: Encoder, value: Boolean) = encoder.encodeBoolean(value)

    override fun deserialize(decoder: Decoder): Boolean =
        when (decoder) {
            is JsonDecoder -> {
                val element = decoder.decodeJsonElement()
                element.jsonPrimitive.booleanOrNull ?: run {
                    element.jsonPrimitive.intOrNull == 1
                }
            }
            else -> throw UnsupportedDecoderException(decoder)
        }
}
