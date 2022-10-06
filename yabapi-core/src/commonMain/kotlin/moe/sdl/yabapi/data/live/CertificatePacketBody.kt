package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CertificatePacketBody(
    @SerialName("uid") val mid: Long,
    @SerialName("roomid") val roomId: Long,
    @SerialName("key") val key: String,
    @SerialName("protover") val version: Int = 3,
    @SerialName("platform") val platform: String = "web",
    @SerialName("type") val type: Int = 2,
)
