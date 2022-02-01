package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TimelineHotResponse(
    @SerialName("step_sec") val step: Int? = null,
    @SerialName("tagstr") val tag: String? = null,
    @SerialName("events") val events: TimelineEvents? = null,
    @SerialName("debug") val debug: String? = null,
)

@Serializable
public data class TimelineEvents(
    @SerialName("default") val list: List<Double> = emptyList(),
)
