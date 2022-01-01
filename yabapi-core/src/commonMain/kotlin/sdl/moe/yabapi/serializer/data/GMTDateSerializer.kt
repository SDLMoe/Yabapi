// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer.data

import io.ktor.util.date.GMTDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object GMTDateSerializer : KSerializer<GMTDate> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("GMTDateSerializer", PrimitiveKind.LONG)

    override fun serialize(encoder: Encoder, value: GMTDate): Unit = encoder.encodeLong(value.timestamp)

    override fun deserialize(decoder: Decoder): GMTDate = GMTDate(decoder.decodeLong())
}
