package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleStats(
    @SerialName("view") val view: Int,
    @SerialName("favorite") val favorite: Int,
    @SerialName("like") val like: Int,
    @SerialName("dislike") val dislike: Int,
    @SerialName("reply") val reply: Int,
    @SerialName("share") val share: Int,
    @SerialName("coin") val coin: Int,
    @SerialName("dynamic") val dynamic: Int,
)
