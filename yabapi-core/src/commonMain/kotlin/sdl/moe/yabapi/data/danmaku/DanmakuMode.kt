package sdl.moe.yabapi.data.danmaku

import kotlinx.serialization.Serializable

@Serializable
public enum class DanmakuMode(public val code: IntArray) {
    UNKNOWN(intArrayOf()),
    NORMAL(intArrayOf(1, 2, 3)),
    BOTTOM(intArrayOf(4)),
    TOP(intArrayOf(5)),
    REVERSED(intArrayOf(6)),
    COMPLEX(intArrayOf(7)),
    CODE(intArrayOf(8)),
    BAS(intArrayOf(9));

    public companion object {
        public fun fromCode(code: Int): DanmakuMode = values().firstOrNull { it.code.contains(code) } ?: UNKNOWN
    }

    public fun encodeToCode(): Int = this.code.firstOrNull() ?: -1
}
