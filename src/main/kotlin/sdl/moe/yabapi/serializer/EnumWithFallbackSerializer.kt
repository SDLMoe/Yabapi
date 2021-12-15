@file:Suppress("UNUSED")

package sdl.moe.yabapi.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.util.getEnumFieldAnnotation
import kotlin.reflect.KClass

/**
 * Unused, due to JVM limitations.
 * Serializer for enum classes with fallback values.
 * @param E the enum class
 * @param fallback the fallback value
 */
internal inline fun <reified E : Enum<E>> createEnumWithFallbackSerializer(
    type: KClass<E>,
    fallback: E,
): KSerializer<*> =
    object : KSerializer<E> {
        override val descriptor: SerialDescriptor =
            PrimitiveSerialDescriptor(type.simpleName ?: "EnumWithFallbackSerializer", PrimitiveKind.STRING)

        override fun serialize(encoder: Encoder, value: E) {
            encoder.encodeString(value.getEnumFieldAnnotation<SerialName>()?.value ?: value.name)
        }

        override fun deserialize(decoder: Decoder): E = decoder.decodeString().let { value ->
            enumValues<E>()
                .firstOrNull { it.getEnumFieldAnnotation<SerialName>()?.value == value || it.name == value }
                ?: fallback
        }
    }
