package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleStats(
    @SerialName("view") val view: Int? = null,
    @SerialName("favorite") val favorite: Int? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("dislike") val dislike: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("share") val share: Int? = null,
    @SerialName("coin") val coin: Int? = null,
    @SerialName("dynamic") val dynamic: Int? = null,
)
