package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleMedia(
    @SerialName("score") val score: Int,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("title") val title: String,
    @SerialName("cover") val cover: String,
    @SerialName("area") val area: String,
    @SerialName("type_id") val typeId: Int,
    @SerialName("type_name") val typeName: String,
    @SerialName("spoiler") val spoiler: Int,
    @SerialName("season_id") val seasonId: Int,
)
