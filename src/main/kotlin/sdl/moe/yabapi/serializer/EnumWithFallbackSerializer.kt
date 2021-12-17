// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:Suppress("UNUSED")

package sdl.moe.yabapi.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import mu.KotlinLogging
import sdl.moe.yabapi.util.getEnumFieldAnnotation
import kotlin.reflect.KClass

private val logger = KotlinLogging.logger {}

/**
 * Unused, due to JVM limitations.
 * Serializer for enum classes with fallback values.
 * @param E the enum class
 * @param fallback the fallback value
 */
@Deprecated("Unused, due to JVM limitations.")
internal inline fun <reified E : Enum<E>> createEnumWithFallbackSerializer(
    type: KClass<E>,
    fallback: E,
): KSerializer<*> = object : KSerializer<E> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(type.simpleName ?: "EnumWithFallbackSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: E) {
        encoder.encodeString(value.getEnumFieldAnnotation<SerialName>()?.value ?: value.name)
    }

    override fun deserialize(decoder: Decoder): E = decoder.decodeString().let { value ->
        enumValues<E>().firstOrNull { it.getEnumFieldAnnotation<SerialName>()?.value == value || it.name == value }
            ?: fallback
    }
}

internal inline fun <reified E : Enum<E>> deserializeEnumWithFallback(decoder: Decoder, fallback: E): E =
    enumFromStringWithFallback(decoder.decodeString(), fallback)

internal inline fun <reified E : Enum<E>> enumFromStringWithFallback(string: String, fallback: E): E =
    enumValues<E>().firstOrNull { it.getEnumFieldAnnotation<SerialName>()?.value == string || it.name == string }
        ?: run {
            logger.warn {
                "Unknown enum value: $string, when deserialize ${E::class.qualifiedName}, fallback to $fallback"
            }
            fallback
        }
