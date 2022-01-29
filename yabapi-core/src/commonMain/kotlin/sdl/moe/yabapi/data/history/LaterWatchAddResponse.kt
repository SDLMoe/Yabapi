package sdl.moe.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.history.LaterWatchCode.UNKNOWN

@Serializable
public data class LaterWatchAddResponse(
    @SerialName("code") val code: LaterWatchCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
)
