package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.live.BatteryCurrency
import moe.sdl.yabapi.data.live.GuardLevel
import moe.sdl.yabapi.data.live.GuardLevel.UNKNOWN

@Serializable
public data class GuardBuyCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: GuardBuyInfo? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "GUARD_BUY"
        override fun decode(json: Json, data: JsonElement): LiveCommand =
            json.decodeFromJsonElement<GuardBuyCmd>(data)
    }
}

@Serializable
public data class GuardBuyInfo(
    @SerialName("uid") val uid: Int? = null, // 用户 uid
    @SerialName("username") val username: String? = null, // 用户名
    @SerialName("guard_level") val level: GuardLevel = UNKNOWN,
    @SerialName("num") val num: Int? = null, // 开通数量
    @SerialName("price") val price: BatteryCurrency? = null, // 花费额, 电池
    @SerialName("gift_id") val giftId: Int? = null, // 礼物 id 舰长为 10003
    @SerialName("gift_name") val giftName: String? = null, // 礼物名称
    @SerialName("start_time") val startTime: Long? = null, // startTime 和 endTime 在这里一样, 应该都是开通时间
    @SerialName("end_time") val endTime: Long? = null,
)
