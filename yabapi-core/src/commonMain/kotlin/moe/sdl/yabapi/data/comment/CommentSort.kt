package moe.sdl.yabapi.data.comment

import kotlinx.serialization.Serializable

@Serializable
public enum class CommentSort(public val code: Int) {
    // 混合排序
    MIXED(1),
    // 按时间排序
    TIME(2),
    // 按热度排序
    HOT(3),
    ;
}
