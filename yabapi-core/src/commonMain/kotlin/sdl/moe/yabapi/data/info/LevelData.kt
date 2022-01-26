package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.info.NextExpSerializer
import kotlin.jvm.JvmInline

/**
 * @param currentLevel 當前用戶等級
 * @param currentMin 當前等級最小經驗值
 * @param currentExp 當前經驗值
 * @param nextExp [NextExp] 下一級 **所需的** 經驗值(而非还差多少), 當等級爲 6 時, 值爲 -1 (無下一等級)
 */
@Serializable
public data class LevelInfo(
    @SerialName("current_level") val currentLevel: Int,
    @SerialName("current_min") val currentMin: Int? = null,
    @SerialName("current_exp") val currentExp: Int? = null,
    @SerialName("next_exp") val nextExp: NextExp? = null,
) {
    public fun toReadString(): String =
        "lv.$currentLevel $currentExp/${nextExp?.toReadString()}"
}

/**
 * value class 用於封裝
 * @property value 實際值
 * @see NextExpSerializer
 */
@Serializable(with = NextExpSerializer::class)
@JvmInline
public value class NextExp(public val value: Int) {
    public fun toReadString(): String = if (value != -1) value.toString() else "--"
}
