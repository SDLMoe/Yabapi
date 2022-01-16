// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LotStatus {
    UNKNOWN,

    @SerialName("0")
    START,

    @SerialName("2")
    AWARDED,

    @SerialName("4")
    REVIEW_PASS,

    @SerialName("5")
    REVIEW_FAILED,
}
