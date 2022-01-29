package moe.sdl.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BangumiRating(
    @SerialName("count") val count: Int,
    @SerialName("score") val score: Double,
)
