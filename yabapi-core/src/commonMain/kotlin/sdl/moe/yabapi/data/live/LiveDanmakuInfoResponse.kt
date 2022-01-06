// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.live.LiveResponseCode.UNKNOWN

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
    @SerialName("refresh_now_factor") val refreshNowFactor: Int,
    @SerialName("refresh_rate") val refreshRate: Int,
    @SerialName("max_delay") val maxDelay: Int,
    @SerialName("token") val token: String,
    @SerialName("host_list") val hostList: List<DanmakuHost>,
)

@Serializable
public data class DanmakuHost(
    @SerialName("host") val host: String,
    @SerialName("port") val port: Int,
    @SerialName("wss_port") val wssPort: Int,
    @SerialName("ws_port") val wsPort: Int,
)
