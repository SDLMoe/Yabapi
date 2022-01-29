package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param rawText 文本
 * @param type 未知
 * @param bizId 未知
 */
@Serializable
public data class DescriptionV2(
    @SerialName("raw_text") val rawText: String,
    @SerialName("type") val type: Int,
    @SerialName("biz_id") val bizId: Int,
)
