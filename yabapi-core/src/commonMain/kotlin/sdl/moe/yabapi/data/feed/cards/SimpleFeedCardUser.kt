package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SimpleFeedCardUser(
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val username: String,
    @SerialName("face") val face: String,
)
