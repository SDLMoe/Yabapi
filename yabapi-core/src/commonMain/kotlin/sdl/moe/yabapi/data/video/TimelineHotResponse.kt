// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TimelineHotResponse(
    @SerialName("step_sec") val step: Int,
    @SerialName("tagstr") val tag: String,
    @SerialName("events") val events: TimelineEvents,
    @SerialName("debug") val debug: String,
)

@Serializable
public data class TimelineEvents(
    @SerialName("default") val list: List<Double>,
)
