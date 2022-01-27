@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class ArticleInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: ArticleInfo? = null,
)

@Serializable
public data class ArticleInfo(
    @SerialName("like") val isLiked: Boolean,
    @SerialName("attention") val isSubscribed: Boolean,
    @SerialName("favorite") val isCollected: Boolean,
    @SerialName("coin") val isCoined: Boolean,
    @SerialName("stats") val stats: ArticleStats,
    @SerialName("title") val title: String,
    @SerialName("banner_url") val bannerUrl: String,
    @SerialName("mid") val mid: Int,
    @SerialName("author_name") val authorName: String,
    @SerialName("is_author") val isAuthor: Boolean,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("origin_image_urls") val originImageUrls: List<String> = emptyList(),
    @SerialName("shareable") val shareable: Boolean,
    @SerialName("show_later_watch") val showLaterWatch: Boolean,
    @SerialName("show_small_window") val showSmallWindow: Boolean,
    @SerialName("in_list") val inList: Boolean,
    @SerialName("pre") val pre: Int,
    @SerialName("next") val next: Int,
    @SerialName("share_channels") val shareChannels: List<ShareChannel>,
    @SerialName("type") val type: Int,
) {
    @Serializable
    public data class ShareChannel(
        @SerialName("name") val name: String,
        @SerialName("picture") val picture: String,
        @SerialName("share_channel") val shareChannel: String,
    )
}
