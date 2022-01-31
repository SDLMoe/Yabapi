package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleMedia(
    @SerialName("score") val score: Int? = null,
    @SerialName("media_id") val mediaId: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("area") val area: String? = null,
    @SerialName("type_id") val typeId: Int? = null,
    @SerialName("type_name") val typeName: String? = null,
    @SerialName("spoiler") val spoiler: Int? = null,
    @SerialName("season_id") val seasonId: Int? = null,
)
