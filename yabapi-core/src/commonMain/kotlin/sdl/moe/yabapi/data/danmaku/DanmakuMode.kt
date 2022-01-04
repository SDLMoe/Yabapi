// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.danmaku

import kotlinx.serialization.Serializable

@Serializable
public enum class DanmakuMode(public val code: List<Int>) {
    UNKNOWN(listOf()),
    NORMAL(listOf(1, 2, 3)),
    BOTTOM(listOf(4)),
    TOP(listOf(5)),
    REVERSED(listOf(6)),
    COMPLEX(listOf(7)),
    CODE(listOf(8)),
    BAS(listOf(9));

    public companion object {
        public fun fromCode(code: Int): DanmakuMode = values().firstOrNull { it.code.contains(code) } ?: UNKNOWN
    }

    public fun encodeToCode(): Int = this.code.firstOrNull() ?: -1
}
