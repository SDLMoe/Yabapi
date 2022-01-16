package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankChangeCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankChangeData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_CHANGED"
        override fun decode(json: Json, data: JsonElement): HotRankChangeCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankChangeData(
    @SerialName("rank") val rank: Int,
    @SerialName("trend") val trend: Int,
    @SerialName("countdown") val countdown: Int,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("web_url") val webUrl: String,
    @SerialName("live_url") val liveUrl: String,
    @SerialName("blink_url") val blinkUrl: String,
    @SerialName("live_link_url") val liveLinkUrl: String,
    @SerialName("pc_link_url") val pcLinkUrl: String,
    @SerialName("icon") val icon: String,
    @SerialName("area_name") val areaName: String,
    @SerialName("rank_desc") val rankDescription: String,
)
