package moe.sdl.yabapi.data.message

public enum class SessionType(public val code: Int?) {
    UNKNOWN(null),

    NORMAL(1),

    FANS(3),

    FOLDED(5),
    ;
}
