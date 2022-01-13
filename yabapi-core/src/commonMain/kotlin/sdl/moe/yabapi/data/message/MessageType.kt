// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.message

public enum class MessageType(public val code: Int) {
    TEXT(1),

    IMAGE(2),

    RECALL(5),
    ;
}
