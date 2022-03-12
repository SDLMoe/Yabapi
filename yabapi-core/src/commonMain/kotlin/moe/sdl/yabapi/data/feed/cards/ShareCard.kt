package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.enums.feed.FeedType

@Serializable
public data class ShareCard(
    @SerialName("rid") val rid: ULong? = null,
    @SerialName("user") val user: SimpleFeedCardUser? = null,
    @SerialName("vest") val vest: VestItem? = null,
    @SerialName("sketch") val sketch: SketchItem? = null,
) : FeedCard {
    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.SHARE.code
        override fun decode(json: Json, data: String): ShareCard = json.decodeFromString(data)
    }

    @Serializable
    public data class VestItem(
        @SerialName("uid") val uid: ULong? = null,
        @SerialName("content") val content: String? = null,
    )

    @Serializable
    public data class SketchItem(
        @SerialName("title") val title: String? = null,
        @SerialName("desc_text") val descriptionText: String? = null,
        @SerialName("cover_url") val coverUrl: String? = null,
        @SerialName("target_url") val targetUrl: String? = null,
        @SerialName("sketch_id") val sketchId: ULong? = null,
        @SerialName("biz_type") val bizType: Int? = null,
    )
}
