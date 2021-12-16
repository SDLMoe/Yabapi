@file:UseSerializers(BooleanJsSerializer::class)
package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LoginWebQRCodeResponse(
    @SerialName("code")
    val code: GeneralCode,
    @SerialName("message")
    val message: String? = null,
    @SerialName("ts")
    val timestamp: Long? = null,
    @SerialName("status")
    val status: Boolean,
    @SerialName("data")
    val data: LoginWebQRCodeResponseData? = null
)

@Serializable
public data class LoginWebQRCodeResponseData(
    val url: String
)
