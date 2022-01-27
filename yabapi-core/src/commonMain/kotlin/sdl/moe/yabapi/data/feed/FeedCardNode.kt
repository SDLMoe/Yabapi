package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.consts.defaultJsonParser
import sdl.moe.yabapi.data.feed.cards.FeedCard
import sdl.moe.yabapi.data.feed.cards.FeedCardFactory

@Serializable
public data class FeedCardNode(
    @SerialName("activity_infos") val activityInfos: FeedActivity? = null,
    @SerialName("desc") val description: FeedDescription? = null,
    @SerialName("card") private val _card: String? = null,
    @SerialName("extension") val extension: JsonObject? = null,
    @SerialName("extend_json") private val _extendJson: String? = null,
    @SerialName("extra") val extra: JsonObject? = null,
    @SerialName("display") val display: JsonObject? = null,
) {
    public fun getCard(json: Json = defaultJsonParser): FeedCard? =
        _card?.let {
            FeedCardFactory.map[description?.type]?.decode(json, _card)
        }

    public fun getExtendJson(json: Json = defaultJsonParser): JsonObject? =
        _extendJson?.let { json.decodeFromString<JsonObject>(this._extendJson) }
}
