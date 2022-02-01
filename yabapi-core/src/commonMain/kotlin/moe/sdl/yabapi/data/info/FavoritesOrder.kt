package moe.sdl.yabapi.data.info

import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.info.FavoritesOrder.FAVORITE_TIME
import moe.sdl.yabapi.data.info.FavoritesOrder.RELEASE_TIME
import moe.sdl.yabapi.data.info.FavoritesOrder.VIEW

/**
 * @property FAVORITE_TIME 收藏时间 新 -> 旧
 * @property RELEASE_TIME 发布时间 新 -> 旧
 * @property VIEW 播放降序
 */
@Serializable
public enum class FavoritesOrder(public val code: String) {
    FAVORITE_TIME("mtime"),

    RELEASE_TIME("pubtime"),

    VIEW("view"),
    ;
}
