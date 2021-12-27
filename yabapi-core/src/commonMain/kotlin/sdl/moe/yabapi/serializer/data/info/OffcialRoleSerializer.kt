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
import sdl.moe.yabapi.data.info.OffcialRole
import sdl.moe.yabapi.data.info.OffcialRole.UNKNOWN
import sdl.moe.yabapi.exception.UnsupportedDecoderException

/**
 * 根据 [OffcialRole.valueList] 序列化
 */
public object OffcialRoleSerializer : KSerializer<OffcialRole> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffcialRoleSerializer", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: OffcialRole): Unit =
        encoder.encodeInt(value.valueList.firstOrNull() ?: 0)

    override fun deserialize(decoder: Decoder): OffcialRole = when (decoder) {
        is JsonDecoder -> run {
            val decoded = decoder.decodeInt()
            enumValues<OffcialRole>().firstOrNull { it.valueList.contains(decoded) } ?: UNKNOWN
        }
        else -> throw UnsupportedDecoderException(decoder)
    }
}
