package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.enums.feed.FeedType

@Serializable
public data class ShareCard(
    @SerialName("rid") val rid: ULong,
    @SerialName("user") val user: SimpleFeedCardUser,
    @SerialName("vest") val vest: VestItem,
    @SerialName("sketch") val sketch: SketchItem,
): FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.SHARE.code
        override fun decode(json: Json, data: String): ShareCard = json.decodeFromString(data)
    }

    @Serializable
    public data class VestItem(
        @SerialName("uid") val uid: ULong,
        @SerialName("content") val content: String?,
    )

    @Serializable
    public data class SketchItem(
        @SerialName("title") val title: String?,
        @SerialName("desc_text") val descriptionText: String?,
        @SerialName("cover_url") val coverUrl: String?,
        @SerialName("target_url") val targetUrl: String?,
        @SerialName("sketch_id") val sketchId: ULong,
        @SerialName("biz_type") val bizType: Int,
    )
}