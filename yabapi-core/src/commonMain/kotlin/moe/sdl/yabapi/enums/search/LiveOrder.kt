package moe.sdl.yabapi.enums.search

/**
 * @property ONLINE 按人氣排序
 * @property TIME 按時間排序
 */
public enum class LiveOrder(public val code: String) {
    ONLINE("online"),

    TIME("live_time"),
    ;
}
