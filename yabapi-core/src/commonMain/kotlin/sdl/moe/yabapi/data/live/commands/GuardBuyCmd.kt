package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import sdl.moe.yabapi.data.live.BatteryCurrency
import sdl.moe.yabapi.data.live.GuardLevel
import sdl.moe.yabapi.data.live.GuardLevel.UNKNOWN

@Serializable
public data class GuardBuyCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: GuardBuyInfo,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "GUARD_BUY"
        override fun decode(json: Json, data: JsonElement): LiveCommand =
            json.decodeFromJsonElement<GuardBuyCmd>(data)
    }
}

@Serializable
public data class GuardBuyInfo(
    @SerialName("uid") val uid: Int, // 用户 uid
    @SerialName("username") val username: String, // 用户名
    @SerialName("guard_level") val level: GuardLevel = UNKNOWN,
    @SerialName("num") val num: Int, // 开通数量
    @SerialName("price") val price: BatteryCurrency, // 花费额, 电池
    @SerialName("gift_id") val giftId: Int, // 礼物 id 舰长为 10003
    @SerialName("gift_name") val giftName: String, // 礼物名称
    @SerialName("start_time") val startTime: Long, // startTime 和 endTime 在这里一样, 应该都是开通时间
    @SerialName("end_time") val endTime: Long,
)
