package sdl.moe.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ArticleCollection(
    @SerialName("id") val id: Int,
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("update_time") val updateTime: Long,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("publish_time") val publishTime: Long,
    @SerialName("summary") val summary: String,
    @SerialName("words") val words: Int,
    @SerialName("read") val read: Int,
    @SerialName("articles_count") val articlesCount: Int, // fake
    @SerialName("state") val state: Int,
    @SerialName("reason") val reason: String,
    @SerialName("apply_time") val applyTime: String,
    @SerialName("check_time") val checkTime: String,
)
