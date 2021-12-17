// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

/**
 * Boolean Serializer Wrapper for JS
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
 * @file:UseSerializers(BooleanJstSerializer::class)
 * ```
 */
internal object BooleanJsSerializer : KSerializer<Boolean> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("BooleanIntSerializer", PrimitiveKind.BOOLEAN)

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeInt(if (value) 1 else 0)
    }

    override fun deserialize(decoder: Decoder): Boolean {
        return try {
            decoder.decodeBoolean()
        } catch (e: SerializationException) {
            logger.warn(e) { "Failed to deserialize with default Boolean, try with Js method" }
            try {
                decoder.decodeInt() == 1
            } catch (e1: SerializationException) {
                logger.warn(e1) { "Failed to deserialize, return false default." }
                false
            }
        }
    }
}
