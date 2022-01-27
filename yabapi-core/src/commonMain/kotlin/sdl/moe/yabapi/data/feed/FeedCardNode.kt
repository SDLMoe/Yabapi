@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.Yabapi.defaultJson
import sdl.moe.yabapi.data.feed.cards.FeedCard
import sdl.moe.yabapi.data.feed.cards.FeedCardFactory
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import sdl.moe.yabapi.util.Logger
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("FeedCardNode") }

@Serializable
public data class FeedCardNode(
    @SerialName("activity_infos") val activityInfos: FeedActivity? = null,
    @SerialName("desc") val description: FeedDescription? = null,
    @SerialName("card") private val _card: String? = null,
    @SerialName("rcmd_cards") val recommendCards: JsonObject? = null,
    @SerialName("extension") val extension: JsonObject? = null,
    @SerialName("extend_json") private val _extendJson: String? = null,
    @SerialName("extra") val extra: JsonObject? = null,
    @SerialName("need_refresh") val needRefresh: Boolean? = null,
    @SerialName("display") val display: JsonObject? = null,
) {
    public fun getCard(json: Json = defaultJson.value): FeedCard? =
        _card?.let {
            FeedCardFactory.map[description?.type]?.decode(json, _card) ?: run {
                logger.warn { "Failed to decode feed card type ${description?.type}, raw: $_card" }
                null
            }
        }

    public fun getExtendJson(json: Json = defaultJson.value): JsonObject? =
        _extendJson?.let { json.decodeFromString<JsonObject>(this._extendJson) }
}
