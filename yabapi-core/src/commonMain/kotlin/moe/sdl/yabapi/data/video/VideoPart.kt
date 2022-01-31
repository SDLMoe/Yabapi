package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class VideoPart(
    @SerialName("cid") val cid: Int? = null,
    @SerialName("page") val part: Int? = null,
    @SerialName("from") val from: String? = null,
    @SerialName("part") val name: String? = null,
    @SerialName("duration") val duration: Long? = null, // s
    @SerialName("vid") val vid: String? = null,
    @SerialName("weblink") val weblink: String? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("first_frame") val firstFrameUrl: String? = null,
)
