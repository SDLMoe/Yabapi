// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class RelationModifyCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-102")
    BANNED,

    @SerialName("-111")
    CSRF_ERROR,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("22001")
    CANNOT_MODIFY_SELF,

    @SerialName("22003")
    BLACKLIST,
}
