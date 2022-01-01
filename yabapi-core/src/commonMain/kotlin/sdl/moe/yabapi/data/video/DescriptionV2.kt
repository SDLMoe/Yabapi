// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

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
