@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializerNullable

@Serializable
public data class FeedDecorateCard(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("id") val id: Int? = null,
    @SerialName("card_url") val cardUrl: String? = null,
    @SerialName("card_type") val cardType: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("expire_time") val expireTime: Long? = null,
    @SerialName("card_type_name") val cardTypeName: String? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("item_id") val itemId: Int? = null,
    @SerialName("item_type") val itemType: Int? = null,
    @SerialName("big_card_url") val bigCardUrl: String? = null,
    @SerialName("jump_url") val jumpUrl: String? = null,
    @SerialName("fan") val fan: UserFanInfo? = null,
    @SerialName("image_enhance") val imageEnhance: String? = null,
)

@Serializable
public data class UserFanInfo(
    @SerialName("name") val name: String? = null,
    @SerialName("is_fan") val isFan: Boolean? = null,
    @SerialName("number") val number: Int? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("color") val color: RgbColor? = null,
    @SerialName("num_desc") val numDesc: String? = null,
)
