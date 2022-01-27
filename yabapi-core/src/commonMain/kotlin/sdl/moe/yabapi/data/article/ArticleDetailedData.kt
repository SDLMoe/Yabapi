@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class ArticleDetailedData(
    @SerialName("cvid") val cvId: Int,
    @SerialName("readInfo") val readInfo: ArticleReadInfo,
    @SerialName("readViewInfo") val readViewInfo: ArticleViewInfo,
    @SerialName("upInfo") val upInfo: ArticleAuthorInfo,
    @SerialName("catalogList") val catalogList: JsonArray?,
    @SerialName("stats") val stats: JsonObject,
    @SerialName("actInfo") val actInfo: JsonObject,
    @SerialName("recommendInfoList") val recommendInfoList: List<ArticleReadInfo>,
)

@Serializable
public data class ArticleReadInfo(
    @SerialName("id") val id: Int,
    @SerialName("category") val category: ArticleCategory,
    @SerialName("categories") val categories: List<ArticleCategory>,
    @SerialName("title") val title: String,
    @SerialName("summary") val summary: String,
    @SerialName("banner_url") val bannerUrl: String,
    @SerialName("template_id") val templateId: Int,
    @SerialName("state") val state: Int,
    @SerialName("author") val author: ArticleAuthor,
    @SerialName("reprint") val notOrigin: Boolean,
    @SerialName("image_urls") val imageUrls: List<String>,
    @SerialName("publish_time") val releaseTime: Long,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("stats") val stats: ArticleStats,
    @SerialName("tags") val tags: List<ArticleTag> = emptyList(),
    @SerialName("attributes") val attributes: Int? = null, // 限流相關, 二進制位
    @SerialName("words") val words: Int,
    @SerialName("origin_image_urls") val originImageUrls: List<String>,
    @SerialName("list") val collection: ArticleCollection? = null,
    @SerialName("is_like") val isLiked: Boolean,
    @SerialName("media") val media: ArticleMedia,
    @SerialName("apply_time") val applyTime: String,
    @SerialName("check_time") val checkTime: String,
    @SerialName("original") val isOriginal: Boolean,
    @SerialName("act_id") val actId: Int,
    @SerialName("dispute") val dispute: ArticleDispute? = null,
    @SerialName("authenMark") val authenMark: JsonElement? = null,
    @SerialName("cover_avid") val coverAvid: Int,
    @SerialName("top_video_info") val topVideoInfo: JsonElement? = null,
    @SerialName("type") val type: Int,
    @SerialName("content") val content: String? = null,
    @SerialName("keywords") val keywords: String? = null,
)

@Serializable
public data class ArticleCategory(
    @SerialName("id") val id: Int,
    @SerialName("parent_id") val parentId: Int,
    @SerialName("name") val name: String,
)

@Serializable
public data class ArticleTag(
    @SerialName("tid") val tid: Int,
    @SerialName("name") val name: String,
)

@Serializable
public data class ArticleDispute(
    @SerialName("dispute") val dispute: String,
    @SerialName("dispute_url") val disputeUrl: String,
)

@Serializable
public data class ArticleViewInfo(
    @SerialName("total") val total: Int,
)

@Serializable
public data class ArticleAuthorInfo(
    @SerialName("fans") val fans: Int,
    @SerialName("readCount") val readCount: Int,
)
