package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.info.Official
import moe.sdl.yabapi.data.info.UserVip

@Serializable
public data class VideoStaff(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("vip") val vip: UserVip? = null,
    @SerialName("official") val official: Official? = null,
    @SerialName("follower") val follower: Int? = null,
    @SerialName("label_style") val labelStyle: Int? = null,
)
