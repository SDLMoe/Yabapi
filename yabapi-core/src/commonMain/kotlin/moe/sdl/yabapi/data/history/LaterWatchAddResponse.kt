package moe.sdl.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.history.LaterWatchCode.UNKNOWN

@Serializable
public data class LaterWatchAddResponse(
    @SerialName("code") val code: LaterWatchCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
)
