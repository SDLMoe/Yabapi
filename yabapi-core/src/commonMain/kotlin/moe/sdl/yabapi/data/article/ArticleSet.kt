package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleSet(
    @SerialName("id") val id: Int? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("update_time") val updateTime: Long? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("publish_time") val publishTime: Long? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("words") val words: Int? = null,
    @SerialName("read") val read: Int? = null,
    @SerialName("articles_count") val articlesCount: Int? = null, // fake
    @SerialName("state") val state: Int? = null,
    @SerialName("reason") val reason: String? = null,
    @SerialName("apply_time") val applyTime: String? = null,
    @SerialName("check_time") val checkTime: String? = null,
)
