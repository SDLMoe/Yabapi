@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class ArticleInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: ArticleInfo? = null,
)

@Serializable
public data class ArticleInfo(
    @SerialName("like") val isLiked: Boolean? = null,
    @SerialName("attention") val isSubscribed: Boolean? = null,
    @SerialName("favorite") val isCollected: Boolean? = null,
    @SerialName("coin") val isCoined: Boolean? = null,
    @SerialName("stats") val stats: ArticleStats? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("banner_url") val bannerUrl: String? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("author_name") val authorName: String? = null,
    @SerialName("is_author") val isAuthor: Boolean? = null,
    @SerialName("image_urls") val imageUrls: List<String> = emptyList(),
    @SerialName("origin_image_urls") val originImageUrls: List<String> = emptyList(),
    @SerialName("shareable") val shareable: Boolean? = null,
    @SerialName("show_later_watch") val showLaterWatch: Boolean? = null,
    @SerialName("show_small_window") val showSmallWindow: Boolean? = null,
    @SerialName("in_list") val inList: Boolean? = null,
    @SerialName("pre") val pre: Int? = null,
    @SerialName("next") val next: Int? = null,
    @SerialName("share_channels") val shareChannels: List<ShareChannel> = emptyList(),
    @SerialName("type") val type: Int? = null,
) {
    @Serializable
    public data class ShareChannel(
        @SerialName("name") val name: String? = null,
        @SerialName("picture") val picture: String? = null,
        @SerialName("share_channel") val shareChannel: String? = null,
    )
}
