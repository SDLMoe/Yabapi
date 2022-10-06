package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class UserNameplateData(
    @SerialName("nid") val nid: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("image_small") val smallImage: String? = null,
    @SerialName("level") val level: String? = null,
    @SerialName("condition") val condition: String? = null,
)
