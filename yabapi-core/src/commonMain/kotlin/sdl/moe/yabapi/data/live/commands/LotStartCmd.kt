@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import sdl.moe.yabapi.data.live.commands.LotStatus.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LotStartCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: LotStartData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ANCHOR_LOT_START"
        override fun decode(json: Json, data: JsonElement): LotStartCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class LotStartData(
    @SerialName("asset_icon") val assetIcon: String,
    @SerialName("award_image") val awardImage: String,
    @SerialName("award_name") val awardName: String,
    @SerialName("award_num") val awardNum: Int, // 總數
    @SerialName("cur_gift_num") val curGiftNum: Int, // 未知
    @SerialName("current_time") val currentTime: Long,
    @SerialName("danmu") val danmu: String,
    @SerialName("gift_id") val giftId: Int,
    @SerialName("gift_name") val giftName: String,
    @SerialName("gift_num") val giftNum: Int, // 每人的數量
    @SerialName("gift_price") val giftPrice: Int,
    @SerialName("goaway_time") val goawayTime: Int,
    @SerialName("goods_id") val goodsId: Int,
    @SerialName("id") val id: Int,
    @SerialName("is_broadcast") val isBroadcast: Boolean,
    @SerialName("join_type") val joinType: Int,
    @SerialName("lot_status") val lotStatus: LotStatus = UNKNOWN,
    @SerialName("max_time") val maxTime: Int,
    @SerialName("require_text") val requireText: String,
    @SerialName("require_value") val requireValue: String,
    @SerialName("room_id") val roomId: Int,
    @SerialName("send_gift_ensure") val sendGiftEnsure: Boolean, // 確認界面
    @SerialName("show_panel") val showPanel: Boolean,
    @SerialName("status") val status: Boolean,
    @SerialName("time") val time: Int,
    @SerialName("url") val url: String,
    @SerialName("web_url") val webUrl: String,
)
