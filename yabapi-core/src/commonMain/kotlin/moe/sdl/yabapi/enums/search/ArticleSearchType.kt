package moe.sdl.yabapi.enums.search

public enum class ArticleSearchType(public val code: Int?) {
    NO_LIMIT(null),
    GAME(0),
    ANIME(2),
    LIFE(3),
    LIGHT_NOVEL(16),
    TECH(17),
    FILM(28),
    HOBBY(29),
    NOTE(41),
}
