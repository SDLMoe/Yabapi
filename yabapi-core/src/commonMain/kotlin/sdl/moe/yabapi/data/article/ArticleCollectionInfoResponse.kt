package sdl.moe.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class ArticleCollectionInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: ArticleCollectionInfoData? = null,
)

@Serializable
public data class ArticleCollectionInfoData(
    @SerialName("list") val info: ArticleCollection,
    @SerialName("articles") val articles: List<SimpleArticleInfo>,
    @SerialName("author") val author: ArticleAuthor,
    @SerialName("last") val last: SimpleArticleInfo,
    @SerialName("attention") val isSubscribed: Boolean,
)

@Serializable
public data class SimpleArticleInfo(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("state") val state: Int,
    @SerialName("publish_time") val releaseTime: Long,
    @SerialName("words") val words: Int,
    @SerialName("image_urls") val imageUrls: List<String>,
    @SerialName("category") val category: ArticleCategory,
    @SerialName("categories") val categories: List<ArticleCategory> = emptyList(),
    @SerialName("summary") val summary: String,
    @SerialName("stats") val stats: ArticleStats? = null,
    @SerialName("like_state") val likeState: Int? = null,
)
