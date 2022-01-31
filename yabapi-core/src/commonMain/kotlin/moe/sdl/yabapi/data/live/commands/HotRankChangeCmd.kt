package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankChangeCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankChangeData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_CHANGED"
        override fun decode(json: Json, data: JsonElement): HotRankChangeCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankChangeData(
    @SerialName("rank") val rank: Int? = null,
    @SerialName("trend") val trend: Int? = null,
    @SerialName("countdown") val countdown: Int? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("web_url") val webUrl: String? = null,
    @SerialName("live_url") val liveUrl: String? = null,
    @SerialName("blink_url") val blinkUrl: String? = null,
    @SerialName("live_link_url") val liveLinkUrl: String? = null,
    @SerialName("pc_link_url") val pcLinkUrl: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("rank_desc") val rankDescription: String? = null,
)
