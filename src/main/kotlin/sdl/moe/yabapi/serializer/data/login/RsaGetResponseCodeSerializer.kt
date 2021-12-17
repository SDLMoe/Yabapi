// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.serializer.data.login

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import sdl.moe.yabapi.data.login.RsaGetResponseCode
import sdl.moe.yabapi.serializer.deserializeEnumWithFallback

internal object RsaGetResponseCodeSerializer : KSerializer<RsaGetResponseCode> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(this.javaClass.simpleName, INT)

    override fun serialize(encoder: Encoder, value: RsaGetResponseCode) = encoder.encodeString(value.name)

    override fun deserialize(decoder: Decoder): RsaGetResponseCode =
        deserializeEnumWithFallback(decoder, RsaGetResponseCode.UNKNOWN)
}
