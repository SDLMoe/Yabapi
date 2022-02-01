@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class ArticleDetailedData(
    @SerialName("cvid") val cvId: Int? = null,
    @SerialName("readInfo") val readInfo: ArticleReadInfo? = null,
    @SerialName("readViewInfo") val readViewInfo: ArticleViewInfo? = null,
    @SerialName("upInfo") val upInfo: ArticleAuthorInfo? = null,
    @SerialName("catalogList") val catalogList: JsonArray?,
    @SerialName("stats") val stats: JsonObject? = null,
    @SerialName("actInfo") val actInfo: JsonObject? = null,
    @SerialName("recommendInfoList") val recommendInfoList: List<ArticleReadInfo> = emptyList(),
)

@Serializable
public data class ArticleReadInfo(
    @SerialName("id") val id: Int? = null,
    @SerialName("category") val category: ArticleCategory? = null,
    @SerialName("categories") val categories: List<ArticleCategory> = emptyList(),
    @SerialName("title") val title: String? = null,
    @SerialName("summary") val summary: String? = null,
    @SerialName("banner_url") val bannerUrl: String? = null,
    @SerialName("template_id") val templateId: Int? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("author") val author: ArticleAuthor? = null,
    @SerialName("reprint") val notOrigin: Boolean? = null,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("publish_time") val releaseTime: Long? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("stats") val stats: ArticleStats? = null,
    @SerialName("tags") val tags: List<ArticleTag> = emptyList(),
    @SerialName("attributes") val attributes: Int? = null, // 限流相關, 二進制位
    @SerialName("words") val words: Int? = null,
    @SerialName("origin_image_urls") val originImageUrls: List<String> = emptyList(),
    @SerialName("list") val collection: ArticleCollection? = null,
    @SerialName("is_like") val isLiked: Boolean? = null,
    @SerialName("media") val media: ArticleMedia? = null,
    @SerialName("apply_time") val applyTime: String? = null,
    @SerialName("check_time") val checkTime: String? = null,
    @SerialName("original") val isOriginal: Boolean? = null,
    @SerialName("act_id") val actId: Int? = null,
    @SerialName("dispute") val dispute: ArticleDispute? = null,
    @SerialName("authenMark") val authenMark: JsonElement? = null,
    @SerialName("cover_avid") val coverAvid: Int? = null,
    @SerialName("top_video_info") val topVideoInfo: JsonElement? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("keywords") val keywords: String? = null,
)

@Serializable
public data class ArticleCategory(
    @SerialName("id") val id: Int? = null,
    @SerialName("parent_id") val parentId: Int? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class ArticleTag(
    @SerialName("tid") val tid: Int? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class ArticleDispute(
    @SerialName("dispute") val dispute: String? = null,
    @SerialName("dispute_url") val disputeUrl: String? = null,
)

@Serializable
public data class ArticleViewInfo(
    @SerialName("total") val total: Int? = null,
)

@Serializable
public data class ArticleAuthorInfo(
    @SerialName("fans") val fans: Int? = null,
    @SerialName("readCount") val readCount: Int? = null,
)
