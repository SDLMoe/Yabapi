// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class VideoHonorData(
    @SerialName("honor") val honors: List<VideoHonor> = emptyList(),
)

/**
 * 視頻榮譽 (入站必刷, 排行榜, 每週必看)
 * @param aid av
 * @param type 類型
 * @param description 簡介
 * @param weeklyRecommendNum 每週必看期數
 */
@Serializable
public data class VideoHonor(
    @SerialName("aid") val aid: Int,
    @SerialName("type") val type: Int,
    @SerialName("desc") val description: String,
    @SerialName("weekly_recommend_num") val weeklyRecommendNum: Int,
)
