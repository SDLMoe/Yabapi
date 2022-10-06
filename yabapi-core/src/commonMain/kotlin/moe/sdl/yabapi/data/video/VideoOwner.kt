package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class VideoOwner(
    @SerialName("mid") val mid: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("face") val avatar: String? = null,
)
