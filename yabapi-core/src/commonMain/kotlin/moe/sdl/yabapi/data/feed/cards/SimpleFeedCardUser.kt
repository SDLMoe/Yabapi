package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SimpleFeedCardUser(
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("face") val face: String? = null,
)
