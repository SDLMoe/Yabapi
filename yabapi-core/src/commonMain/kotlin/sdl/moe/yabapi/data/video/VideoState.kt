// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class VideoState {
    UNKNOWN,

    @SerialName("1")
    SPECIAL_PASS,

    @SerialName("0")
    PASS,

    @SerialName("-1")
    NEED_REVIEW,

    @SerialName("-3")
    POLICE_LOCKED,

    @SerialName("-4")
    LOCKED,

    @SerialName("-5")
    ADMIN_LOCKED,

    @SerialName("-6")
    COMMITTED_NEED_REVIEW,

    @SerialName("-7")
    BIT_PENDING,

    @SerialName("-8")
    RE_UPLOAD_NEED_REVIEW,

    @SerialName("-9")
    WAIT_ENCODE,

    @SerialName("-10")
    PENDING,

    @SerialName("-11")
    SOURCE_ERROR,

    @SerialName("-12")
    DUMP_FAILED,

    @SerialName("-13")
    ALLOW_COMMENT_NEED_REVIEW,

    @SerialName("-14")
    TRASH,

    @SerialName("-15")
    DISTRIBUTING,

    @SerialName("-16")
    ENCODE_FAILED,

    @SerialName("-20")
    CREATE_NOT_PUSH,

    @SerialName("-30")
    CREATE_PUSHED,

    @SerialName("-40")
    TIMED_RELEASE,

    @SerialName("-100")
    DELETED;
}
