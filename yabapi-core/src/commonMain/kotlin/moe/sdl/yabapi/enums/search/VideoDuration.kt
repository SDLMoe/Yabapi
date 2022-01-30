package moe.sdl.yabapi.enums.search

/**
 * @property NO_LIMIT 无限制
 * @property D10 `<= 10`
 * @property D10_30 `10 - 30`
 * @property D30_60 `30 - 60`
 * @property D60 `>= 60`
 */
public enum class VideoDuration(public val code: Int) {
    NO_LIMIT(0),
    D10(1),
    D10_30(2),
    D30_60(3),
    D60(4),
    ;
}
