package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class ArticleSetInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: ArticleSetInfoData? = null,
)

@Serializable
public data class ArticleSetInfoData(
    @SerialName("list") val info: ArticleSet? = null,
    @SerialName("articles") val articles: List<SimpleArticleInfo> = emptyList(),
    @SerialName("author") val author: ArticleAuthor? = null,
    @SerialName("last") val last: SimpleArticleInfo? = null,
    @SerialName("attention") val isSubscribed: Boolean? = null,
)

@Serializable
public data class SimpleArticleInfo(
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("publish_time") val releaseTime: Long? = null,
    @SerialName("words") val words: Int? = null,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("category") val category: ArticleCategory? = null,
    @SerialName("categories") val categories: List<ArticleCategory> = emptyList(),
    @SerialName("summary") val summary: String? = null,
    @SerialName("stats") val stats: ArticleStats? = null,
    @SerialName("like_state") val likeState: Int? = null,
)
