package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.live.LiveResponseCode.UNKNOWN

@Serializable
public data class LiveDanmakuInfoGetResponse(
    @SerialName("code") val code: LiveResponseCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveDanmakuInfo? = null,
)

@Serializable
public data class LiveDanmakuInfo(
    @SerialName("group") val group: String,
    @SerialName("business_id") val businessId: Int,
    @SerialName("refresh_row_factor") val refreshRowFactor: Double,
    @SerialName("refresh_rate") val refreshRate: Double,
    @SerialName("max_delay") val maxDelay: Double,
    @SerialName("token") val token: String,
    @SerialName("host_list") val hostList: List<LiveDanmakuHost>,
)

@Serializable
public data class LiveDanmakuHost(
    @SerialName("host") val host: String,
    @SerialName("port") val port: Int,
    @SerialName("wss_port") val wssPort: Int,
    @SerialName("ws_port") val wsPort: Int,
)
