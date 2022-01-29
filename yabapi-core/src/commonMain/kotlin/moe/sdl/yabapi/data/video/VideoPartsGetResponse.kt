package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.video.VideoInfoGetCode.UNKNOWN

@Serializable
public data class VideoPartsGetResponse(
    @SerialName("code") val code: VideoInfoGetCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<VideoPart> = emptyList(),
)
