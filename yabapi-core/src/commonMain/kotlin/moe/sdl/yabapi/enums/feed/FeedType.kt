package moe.sdl.yabapi.enums.feed

public enum class FeedType(public val code: Int) {
    UNKNOWN(-1),

    REPOST(1),

    IMAGE(2),

    TEXT(4),

    VIDEO(8),

    ARTICLE(64),

    BANGUMI(512),

    LIVE(4308),

    COLLECTION(4310),

    ALL(268435455),
    ;
}
