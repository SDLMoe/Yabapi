// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LogOutResponse(
    @SerialName("code") public val code: GeneralCode = UNKNOWN,
    @SerialName("status") public val status: Boolean? = null,
    @SerialName("ts") public val timestamp: Long,
    @SerialName("message") public val message: String? = null,
    @SerialName("data") public val data: LogOutResponseData? = null,
)

@Serializable
public data class LogOutResponseData(
    @SerialName("redirectUrl") val redirectUrl: String,
)
