package moe.sdl.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.sticker.StickerSize.LARGE
import moe.sdl.yabapi.data.sticker.StickerSize.SMALL

/**
 * 表情大小
 * @property SMALL 小
 * @property LARGE 大
 */
@Serializable
public enum class StickerSize {
    UNKNOWN,

    @SerialName("1")
    SMALL,

    @SerialName("2")
    LARGE;
}
