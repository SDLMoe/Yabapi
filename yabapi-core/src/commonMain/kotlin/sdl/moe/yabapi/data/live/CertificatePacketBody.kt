package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CertificatePacketBody(
    @SerialName("uid") val mid: Int,
    @SerialName("roomid") val roomId: Int,
    @SerialName("key") val key: String,
    @SerialName("protover") val version: Int = 3,
    @SerialName("platform") val platform: String = "web",
    @SerialName("type") val type: Int = 2,
)
