package moe.sdl.yabapi.enums.search

public enum class SearchRequestType(public val code: String) {
    ALL("all"),
    VIDEO("video"),
    ANIME("bangumi"),
    SERIES("pgc"),
    LIVE("live"),
    ARTICLE("article"),
    TOPIC("topic"),
}
