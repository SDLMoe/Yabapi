package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankSettlementCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankSettlementData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_SETTLEMENT"
        override fun decode(json: Json, data: JsonElement): HotRankSettlementCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankSettlementData(
    @SerialName("area_name") val areaName: String,
    @SerialName("cache_key") val cacheKey: String,
    @SerialName("dm_msg") val danmakuMsg: String,
    @SerialName("dmscore") val danmakuScore: Int,
    @SerialName("face") val avatar: String,
    @SerialName("icon") val icon: String,
    @SerialName("rank") val rank: Int,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("uname") val userName: String,
    @SerialName("url") val url: String,
)
