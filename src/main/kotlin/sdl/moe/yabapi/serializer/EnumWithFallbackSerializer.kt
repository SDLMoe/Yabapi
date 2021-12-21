// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer

import kotlinx.serialization.SerialName
import kotlinx.serialization.encoding.Decoder
import mu.KotlinLogging
import sdl.moe.yabapi.util.getEnumFieldAnnotation
import java.lang.IllegalArgumentException

private val logger = KotlinLogging.logger {}

internal inline fun <reified E : Enum<E>> deserializeEnumWithFallback(decoder: Decoder, fallback: E): E =
    enumFromStringWithFallback(decoder.decodeString(), fallback)

internal inline fun <reified E : Enum<E>> enumFromStringWithFallback(string: String, fallback: E): E =
    enumValues<E>().firstOrNull { it.getEnumFieldAnnotation<SerialName>()?.value == string || it.name == string }
        ?: run {
            logger.warn(IllegalArgumentException("Unexpected Enum Value $string")) {
                "Unknown enum value: $string, when deserialize ${E::class.qualifiedName}, fallback to $fallback"
            }
            fallback
        }
