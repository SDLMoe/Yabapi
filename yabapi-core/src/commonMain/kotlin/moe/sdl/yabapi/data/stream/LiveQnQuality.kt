package moe.sdl.yabapi.data.stream

import kotlinx.serialization.Serializable

@Serializable
public enum class LiveQnQuality(public val code: Int) {
    FAST(80), // 流畅
    STANDARD(150), // 高清
    HIGH(250), // 超清
    BLU_RAY(400), // 蓝光
    BLU_RAY_DOLBY(401), // 蓝光(杜比)
    ORIGIN(10000), // 原画
    UHD(20000), // 4K
    ;

    public companion object {
        public fun fromCodeOrNull(code: Int): LiveQnQuality? = values().firstOrNull { it.code == code }
    }
}
