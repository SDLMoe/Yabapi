package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.enums.feed.FeedType

@Serializable
public data class TextCard(
    @SerialName("user") val user: SimpleFeedCardUser? = null,
    @SerialName("item") val item: Item? = null,
) : FeedCard {
    @Serializable
    public data class Item(
        @SerialName("rp_id") val rpId: ULong? = null,
        @SerialName("uid") val uid: Long? = null,
        @SerialName("content") val content: String? = null,
        @SerialName("ctrl") val ctrl: String? = null,
        @SerialName("orig_dy_id") val originFeedId: Long? = null,
        @SerialName("pre_dy_id") val preFeedId: Long? = null,
        @SerialName("timestamp") val timestamp: Long? = null,
        @SerialName("at_uids") val atUids: List<Long> = emptyList(),
        @SerialName("reply") val reply: Int? = null,
    )

    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.TEXT.code

        override fun decode(json: Json, data: String): TextCard = json.decodeFromString(data)
    }
}
