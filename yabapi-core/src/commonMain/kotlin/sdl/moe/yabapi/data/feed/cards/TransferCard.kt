package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.consts.defaultJsonParser
import sdl.moe.yabapi.data.feed.FeedUserProfile
import kotlin.native.concurrent.ThreadLocal

@Serializable
public data class TransferCard(
    @SerialName("user") val user: User,
    @SerialName("item") val item: Item,
    @SerialName("origin") private val _origin: String,
    @SerialName("origin_extension") val originExtension: JsonObject,
    @SerialName("origin_extend_json") val originExtendJson: String,
    @SerialName("origin_user") val originUser: FeedUserProfile,
) : FeedCard {
    public fun getOrigin(json: Json = defaultJsonParser): FeedCard? =
        FeedCardFactory.map[item.originType]?.decode(json, _origin)

    @Serializable
    public data class User(
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val username: String,
        @SerialName("face") val face: String,
    )

    @Serializable
    public data class Item(
        @SerialName("rp_id") val rpId: ULong,
        @SerialName("uid") val uid: Int,
        @SerialName("content") val content: String,
        @SerialName("ctrl") val ctrl: String,
        @SerialName("orig_dy_id") val originFeedId: ULong,
        @SerialName("pre_dy_id") val preDyId: ULong,
        @SerialName("timestamp") val timestamp: Long,
        @SerialName("reply") val reply: Int,
        @SerialName("orig_type") val originType: Int,
    )

    @ThreadLocal
    public companion object : FeedCardFactory() {
        override val code: Int = 2
        override fun decode(json: Json, data: String): TransferCard = json.decodeFromString(data)
    }
}
