package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
        @SerialName("ctrl") val ctrl: String,
        @SerialName("orig_by_id") val originFeedId: Int,
        @SerialName("pre_dynamic_id") val preFeedId: Int,
        @SerialName("timestamp") val timestamp: Long,
        @SerialName("reply") val reply: Int,
    )

    public companion object: FeedCardFactory() {
        override val code: Int = 4

        override fun decode(json: Json, data: String): TextCard = json.decodeFromString(data)
    }
}
