package moe.sdl.yabapi.enums.search

public enum class ArticleOrder(public val code: String) {
    MIXED(""), // empty for default, aka mixed order
    TIME("pubdate"),
    CLICK("click"),
    SUBSCRIBE("attention"),
    COMMENT("scores")
}
