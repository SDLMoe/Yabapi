// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LiveRoomStatus {
    @SerialName("0")
    NOT_LIVE,

    @SerialName("1")
    LIVING,

    @SerialName("2")
    ROUNDING,
}
