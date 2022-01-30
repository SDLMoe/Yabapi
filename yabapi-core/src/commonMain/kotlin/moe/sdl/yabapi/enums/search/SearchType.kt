package moe.sdl.yabapi.enums.search

public enum class SearchType(public val code: String) {
    VIDEO("video"),
    ANIME("media_bangumi"),
    SERIES("media_ft"),
    LIVE("live"),
    LIVE_USER("live_user"),
    ARTICLE("article"),
    TOPIC("topic"),
    USER("bili_user")
}
