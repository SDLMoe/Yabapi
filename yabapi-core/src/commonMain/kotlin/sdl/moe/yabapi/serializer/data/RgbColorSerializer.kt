// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.RgbColor

public object RgbColorStringSerializer : KSerializer<RgbColor> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("RgbColorStringSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: RgbColor): Unit = encoder.encodeString(value.hex)
    override fun deserialize(decoder: Decoder): RgbColor = RgbColor.fromHex(decoder.decodeString())
}

public object RgbColorIntSerializer : KSerializer<RgbColor> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("RgbColorIntSerializer", PrimitiveKind.INT)
    override fun serialize(encoder: Encoder, value: RgbColor): Unit = encoder.encodeInt(value.intHex)
    override fun deserialize(decoder: Decoder): RgbColor = RgbColor.fromHex(decoder.decodeInt())
}