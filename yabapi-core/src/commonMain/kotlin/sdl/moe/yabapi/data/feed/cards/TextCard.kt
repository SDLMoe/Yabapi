package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.enums.feed.FeedType

@Serializable
public data class TextCard(
    @SerialName("user") val user: SimpleFeedCardUser,
    @SerialName("item") val item: Item,
): FeedCard {
    @Serializable
    public data class Item(
        @SerialName("rp_id") val rpId: ULong,
        @SerialName("uid") val uid: Int,
        @SerialName("content") val content: String,
        @SerialName("ctrl") val ctrl: String? = null,
        @SerialName("orig_dy_id") val originFeedId: Int? = null,
        @SerialName("pre_dy_id") val preFeedId: Int? = null,
        @SerialName("timestamp") val timestamp: Long,
        @SerialName("at_uids") val atUids: List<Int> = emptyList(),
        @SerialName("reply") val reply: Int,
    )

    public companion object: FeedCardFactory() {
        override val code: Int = FeedType.TEXT.code

        override fun decode(json: Json, data: String): TextCard = json.decodeFromString(data)
    }
}
