package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserNameplateData(
    @SerialName("nid") val nid: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("image_small") val smallImage: String,
    @SerialName("level") val level: String,
    @SerialName("condition") val condition: String,
)
