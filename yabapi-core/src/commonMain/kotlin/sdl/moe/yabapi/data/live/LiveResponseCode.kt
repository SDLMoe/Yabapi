// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LiveResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("1")
    ERROR,

    @SerialName("60004")
    NOT_FOUND,

    @SerialName("60009")
    INVALID_LIVE_TYPE,
}
