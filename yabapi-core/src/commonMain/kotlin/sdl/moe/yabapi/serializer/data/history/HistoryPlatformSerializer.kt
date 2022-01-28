package sdl.moe.yabapi.serializer.data.history

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.history.HistoryPlatform
import sdl.moe.yabapi.data.history.HistoryPlatform.UNKNOWN

public object HistoryPlatformSerializer : KSerializer<HistoryPlatform> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("HistoryPlatform", INT)
    override fun serialize(encoder: Encoder, value: HistoryPlatform): Unit = encoder.encodeInt(value.codes.first())
    override fun deserialize(decoder: Decoder): HistoryPlatform = decoder.decodeInt().let { decoded ->
        HistoryPlatform.values().firstOrNull {
            it.codes.contains(decoded)
        } ?: UNKNOWN
    }
}
