package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class CertificatePacketResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
)
