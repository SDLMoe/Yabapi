package moe.sdl.yabapi.serializer.data

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.data.RgbaColor

public object RgbColorStringSerializer : KSerializer<RgbColor> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("RgbColorString", STRING)

    override fun serialize(encoder: Encoder, value: RgbColor): Unit = encoder.encodeString(value.hex)
    override fun deserialize(decoder: Decoder): RgbColor = RgbColor.fromHex(decoder.decodeString())
}

public object RgbColorStringSerializerNullable : KSerializer<RgbColor?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("RgbColorString", STRING)

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: RgbColor?): Unit = value?.hex?.let {
        encoder.encodeString(it)
    } ?: encoder.encodeNull()

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): RgbColor? {
        val decoded = decoder.decodeNullableSerializableValue(String.serializer().nullable)
        return if (decoded.isNullOrBlank()) {
            return null
        } else runCatching {
            RgbColor.fromHex(decoded)
        }.getOrNull()
    }
}

public object RgbColorIntSerializerNullable : KSerializer<RgbColor?> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("RgbColorInt", INT)

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: RgbColor?): Unit = value?.hex?.let {
        encoder.encodeString(it)
    } ?: encoder.encodeNull()

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): RgbColor? {
        val decoded = decoder.decodeNullableSerializableValue(Int.serializer().nullable)
        return if (decoded == null) {
            return null
        } else runCatching {
            RgbColor.fromHex(decoded)
        }.getOrNull()
    }
}

public object RgbColorIntSerializer : KSerializer<RgbColor> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RgbColorIntSerializer", INT)
    override fun serialize(encoder: Encoder, value: RgbColor): Unit = encoder.encodeInt(value.intHex)
    override fun deserialize(decoder: Decoder): RgbColor = RgbColor.fromHex(decoder.decodeInt())
}

public object RgbaColorStringSerializer : KSerializer<RgbaColor> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RgbaColorString", STRING)
    override fun serialize(encoder: Encoder, value: RgbaColor): Unit = encoder.encodeString(value.hex)
    override fun deserialize(decoder: Decoder): RgbaColor = RgbaColor.fromHex(decoder.decodeString())
}
