@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.data.info.UserVip
import moe.sdl.yabapi.enums.feed.FeedType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class ImageCard(
    @SerialName("item") val item: Item? = null,
    @SerialName("user") val user: User? = null,
) : FeedCard {
    @Serializable
    public data class Item(
        @SerialName("at_control") val atControl: String? = null,
        @SerialName("category") val category: String? = null,
        @SerialName("description") val description: String? = null, // 內容
        @SerialName("id") val id: Long? = null,
        @SerialName("is_fav") val isFav: Boolean? = null,
        @SerialName("pictures") val pictures: List<CardPicture> = emptyList(),
        @SerialName("pictures_count") val picturesCount: Int? = null,
        @SerialName("reply") val reply: Int? = null,
        @SerialName("role") val role: JsonArray? = null,
        @SerialName("settings") val settings: Settings? = null,
        @SerialName("source") val source: JsonArray? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("upload_time") val uploadTime: Long? = null,
    )

    @Serializable
    public data class Settings(
        @SerialName("copy_forbidden") private val _copyForbidden: String? = null,
    ) {
        val copyForbidden: Boolean by lazy { _copyForbidden == "1" }
    }

    @Serializable
    public data class User(
        @SerialName("head_url") val headUrl: String? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("uid") val uid: Long? = null,
        @SerialName("vip") val vip: UserVip? = null,
    )

    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.IMAGE.code
        override fun decode(json: Json, data: String): ImageCard = json.decodeFromString(data)
    }
}

@Serializable
public data class CardPicture(
    @SerialName("img_height") val imgHeight: Int? = null,
    @SerialName("img_size") val imgSize: Double? = null,
    @SerialName("img_src") val imgSrc: String? = null,
    @SerialName("img_tags") val imgTags: JsonElement? = null,
    @SerialName("img_width") val imgWidth: Int? = null,
)
