@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.RgbColor
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import sdl.moe.yabapi.serializer.data.RgbColorStringSerializerNullable

@Serializable
public data class FeedDecorateCard(
    @SerialName("mid") val mid: Int,
    @SerialName("id") val id: Int,
    @SerialName("card_url") val cardUrl: String,
    @SerialName("card_type") val cardType: Int,
    @SerialName("name") val name: String,
    @SerialName("expire_time") val expireTime: Long,
    @SerialName("card_type_name") val cardTypeName: String,
    @SerialName("uid") val uid: Int,
    @SerialName("item_id") val itemId: Int,
    @SerialName("item_type") val itemType: Int,
    @SerialName("big_card_url") val bigCardUrl: String,
    @SerialName("jump_url") val jumpUrl: String,
    @SerialName("fan") val fan: Fan,
    @SerialName("image_enhance") val imageEnhance: String,
) {
    @Serializable
    public data class Fan(
        @SerialName("is_fan") val isFan: Boolean,
        @SerialName("number") val number: Int,
        @Serializable(RgbColorStringSerializerNullable::class)
        @SerialName("color") val color: RgbColor? = null,
        @SerialName("num_desc") val numDesc: String,
    )
}
