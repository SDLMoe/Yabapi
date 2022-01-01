// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VideoCopyright {
    UNKNOWN,

    @SerialName("1")
    ORIGINAL,

    @SerialName("2")
    REPRINT;
}
