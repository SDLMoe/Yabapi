package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class VideoOwner(
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("face") val avatar: String,
)
