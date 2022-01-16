package sdl.moe.yabapi.serializer.data.info

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import sdl.moe.yabapi.data.info.OfficialRole
import sdl.moe.yabapi.data.info.OfficialRole.UNKNOWN
import sdl.moe.yabapi.exception.UnsupportedDecoderException

/**
 * 根据 [OfficialRole.valueList] 序列化
 */
public object OfficialRoleSerializer : KSerializer<OfficialRole> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("OffcialRoleSerializer", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: OfficialRole): Unit =
        encoder.encodeInt(value.valueList.firstOrNull() ?: 0)

    override fun deserialize(decoder: Decoder): OfficialRole = when (decoder) {
        is JsonDecoder -> run {
            val decoded = decoder.decodeInt()
            enumValues<OfficialRole>().firstOrNull { it.valueList.contains(decoded) } ?: UNKNOWN
        }
        else -> throw UnsupportedDecoderException(decoder)
    }
}
