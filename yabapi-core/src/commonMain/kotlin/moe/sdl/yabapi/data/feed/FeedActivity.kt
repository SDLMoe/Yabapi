package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class FeedActivity(
    @SerialName("details") val details: List<FeedActivityNode>,
)

@Serializable
public data class FeedActivityNode(
    @SerialName("type") val type: Int? = null,
    @SerialName("detail") val detail: String? = null,
)
