package sdl.moe.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SpaceVideoButton(
    @SerialName("text") val text: String,
    @SerialName("uri") val playUri: String, // like: //www.bilibili.com/medialist/play/$mid?from=space
)
