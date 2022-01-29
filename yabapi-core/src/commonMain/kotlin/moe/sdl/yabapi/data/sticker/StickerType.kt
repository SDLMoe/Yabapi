package moe.sdl.yabapi.data.sticker

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.sticker.StickerType.EMOTICON
import moe.sdl.yabapi.data.sticker.StickerType.NORMAL
import moe.sdl.yabapi.data.sticker.StickerType.PAID
import moe.sdl.yabapi.data.sticker.StickerType.UNKNOWN
import moe.sdl.yabapi.data.sticker.StickerType.VIP

/**
 * 表情类型
 * @property UNKNOWN 未知
 * @property NORMAL 普通
 * @property VIP 會員專屬
 * @property PAID 付費
 * @property EMOTICON 顏文字
 */
@Serializable
public enum class StickerType {
    UNKNOWN,

    @SerialName("1")
    NORMAL,

    @SerialName("2")
    VIP,

    @SerialName("3")
    PAID,

    @SerialName("4")
    EMOTICON;
}
