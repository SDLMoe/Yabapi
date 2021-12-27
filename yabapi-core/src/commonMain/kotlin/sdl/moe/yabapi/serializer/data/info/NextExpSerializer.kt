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
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive
import sdl.moe.yabapi.data.info.NextExp
import sdl.moe.yabapi.exception.UnsupportedDecoderException

public object NextExpSerializer : KSerializer<NextExp> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("NextExpSerializer", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: NextExp): Unit = encoder.encodeInt(value.value)

    override fun deserialize(decoder: Decoder): NextExp = when (decoder) {
        is JsonDecoder -> decoder.decodeJsonElement().jsonPrimitive.let { NextExp(it.intOrNull ?: -1) }
        else -> throw UnsupportedDecoderException(decoder)
    }
}
