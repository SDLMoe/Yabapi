@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.consts.defaultJsonParser
import sdl.moe.yabapi.data.feed.FeedActivity
import sdl.moe.yabapi.data.feed.FeedUserProfile
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import kotlin.native.concurrent.ThreadLocal

@Serializable
public data class RepostCard(
    @SerialName("user") val user: SimpleFeedCardUser,
    @SerialName("item") val item: Item,
    @SerialName("origin") private val _origin: String? = null, // 在源動態失效時可能爲空
    @SerialName("origin_extension") val originExtension: JsonObject? = null,
    @SerialName("origin_extend_json") val originExtendJson: String? = null,
    @SerialName("origin_user") val originUser: FeedUserProfile? = null,
    @SerialName("activity_infos") val activityInfos: FeedActivity? = null,
) : FeedCard {
    public fun getOrigin(json: Json = defaultJsonParser): FeedCard? =
        _origin?.let { FeedCardFactory.map[item.originType]?.decode(json, it) }

    @Serializable
    public data class Item(
        @SerialName("rp_id") val rpId: ULong,
        @SerialName("uid") val uid: Int,
        @SerialName("content") val content: String,
        @SerialName("ctrl") val ctrl: String? = null,
        @SerialName("orig_dy_id") val originFeedId: ULong,
        @SerialName("pre_dy_id") val preDyId: ULong,
        @SerialName("timestamp") val timestamp: Long? = null,
        @SerialName("at_uids") val atUids: List<Int> = emptyList(),
        @SerialName("reply") val reply: Int,
        @SerialName("miss") val miss: Boolean? = null,
        @SerialName("tips") val tips: String? = null,
        @SerialName("orig_type") val originType: Int,
    )

    @ThreadLocal
    public companion object : FeedCardFactory() {
        override val code: Int = 1
        override fun decode(json: Json, data: String): RepostCard = json.decodeFromString(data)
    }
}
