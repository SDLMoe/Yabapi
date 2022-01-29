package moe.sdl.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class LaterWatchAddChannelResponse(
    @SerialName("status") val status: Boolean,
    @SerialName("data") val data: String,
)
