package sdl.moe.yabapi.enums.feed

public enum class FeedType(public val code: Int) {
    UNKNOWN(-1),

    REPOST(1),

    IMAGE(2),

    TEXT(4),

    VIDEO(8)
}
