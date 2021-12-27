// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer.data.info

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import sdl.moe.yabapi.data.info.IsOffcialCertified
import sdl.moe.yabapi.exception.UnsupportedDecoderException

/**
 * 为 0 时返回 true, 其余为 false
 */
public object IsOffcialCertifiedSerializer : KSerializer<IsOffcialCertified> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("IsOffcialCertifedSerializer", PrimitiveKind.BOOLEAN)

    override fun serialize(encoder: Encoder, value: IsOffcialCertified): Unit = when (value.value) {
        true -> encoder.encodeInt(0)
        false -> encoder.encodeInt(-1)
    }

    override fun deserialize(decoder: Decoder): IsOffcialCertified = when (decoder) {
        is JsonDecoder -> IsOffcialCertified(decoder.decodeInt() == 0)
        else -> throw UnsupportedDecoderException(decoder)
    }
}
