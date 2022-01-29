package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.space.SpaceResponseCode.UNKNOWN
import moe.sdl.yabapi.data.video.VideoInfo

@Serializable
public data class RecentCoinedVideoResponse(
    @SerialName("code") val code: SpaceResponseCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<VideoInfo> = emptyList(),
)
