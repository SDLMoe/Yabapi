package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankSettlementCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankSettlementData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_SETTLEMENT"
        override fun decode(json: Json, data: JsonElement): HotRankSettlementCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankSettlementData(
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("cache_key") val cacheKey: String? = null,
    @SerialName("dm_msg") val danmakuMsg: String? = null,
    @SerialName("dmscore") val danmakuScore: Int? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("uname") val userName: String? = null,
    @SerialName("url") val url: String? = null,
)
