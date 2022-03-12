package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankSettlementV2Cmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankSettlementV2Data? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_SETTLEMENT_V2"
        override fun decode(json: Json, data: JsonElement): HotRankSettlementV2Cmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankSettlementV2Data(
    @SerialName("rank") val rank: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("area_name") val areaName: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("cache_key") val cacheKey: String? = null,
    @SerialName("dm_msg") val dmMsg: String? = null, // 恭喜主播 <% Username %> 荣登限时热门榜主机游戏榜榜首! 即将获得热门流量推荐哦！
)
