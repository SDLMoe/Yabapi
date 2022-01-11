// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class HotRankSettlementV2Cmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: HotRankSettlementV2Data,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "HOT_RANK_SETTLEMENT_V2"
        override fun decode(json: Json, data: JsonElement): HotRankSettlementV2Cmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class HotRankSettlementV2Data(
    @SerialName("rank") val rank: Int,
    @SerialName("uname") val username: String,
    @SerialName("face") val avatar: String,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("icon") val icon: String,
    @SerialName("area_name") val areaName: String,
    @SerialName("url") val url: String,
    @SerialName("cache_key") val cacheKey: String,
    @SerialName("dm_msg") val dmMsg: String, //恭喜主播 <% Username %> 荣登限时热门榜主机游戏榜榜首! 即将获得热门流量推荐哦！
)
