package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.info.Official
import moe.sdl.yabapi.data.info.UserVip

@Serializable
public data class VideoStaff(
    @SerialName("mid") val mid: Int,
    @SerialName("title") val title: String,
    @SerialName("name") val name: String,
    @SerialName("face") val avatar: String,
    @SerialName("vip") val vip: UserVip,
    @SerialName("official") val official: Official,
    @SerialName("follower") val follower: Int,
    @SerialName("label_style") val labelStyle: Int? = null,
)
