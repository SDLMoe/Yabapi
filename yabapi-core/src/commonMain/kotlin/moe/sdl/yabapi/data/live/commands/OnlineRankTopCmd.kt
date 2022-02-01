package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

/**
 * 直播高能榜前三变动信息
 */
@Serializable
public data class OnlineRankTopCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: OnlineRankTopData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ONLINE_RANK_TOP3"
        override fun decode(json: Json, data: JsonElement): OnlineRankTopCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class OnlineRankTopData(
    @SerialName("list") val list: List<OnlineRankNode> = emptyList(), // 一般只有一个节点
) {
    @Serializable
    public data class OnlineRankNode(
        @SerialName("msg") val msg: String? = null, // 恭喜 <%username%> 成为高能榜
        @SerialName("rank") val rank: Int? = null, // in 1..3
    )
}
