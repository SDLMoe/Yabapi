@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.data.live.GuardLevel
import moe.sdl.yabapi.data.live.GuardLevel.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorIntSerializerNullable

@Serializable
public data class ComboSendCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: ComboSendData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "COMBO_SEND"
        override fun decode(json: Json, data: JsonElement): ComboSendCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class ComboSendData(
    @SerialName("action") val action: String? = null,
    // batch:gift:combo_id:$uid:$targetUid:$giftId:$timestamp
    // e.g. batch:gift:combo_id:178979439:13164144:30607:1641743385.6827
    @SerialName("batch_combo_id") val batchComboId: String? = null,
    @SerialName("batch_combo_num") val batchComboNum: Int? = null,
    @SerialName("combo_id") val comboId: String? = null,
    @SerialName("combo_num") val comboNum: Int? = null,
    @SerialName("combo_total_coin") val comboTotalCoin: Int? = null,
    @SerialName("dmscore") val danmakuScore: Int? = null,
    @SerialName("gift_id") val giftId: Long? = null,
    @SerialName("gift_name") val giftName: String? = null,
    @SerialName("gift_num") val giftNum: Int? = null,
    @SerialName("is_show") val isShow: Boolean? = null,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("name_color") val nameColor: String? = null, // 似乎恒为 ""
    @SerialName("r_uname") val targetName: String? = null,
    @SerialName("ruid") val targetUid: Long? = null,
    @SerialName("send_master") val sendMaster: JsonElement?, // 恒为 null
    @SerialName("total_num") val totalNum: Int? = null,
    @SerialName("uid") val uid: Long? = null,
    @SerialName("uname") val username: String? = null, // 发送者的 username
) {
    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Long? = null, // 房间id
        @SerialName("anchor_uname") val liverName: String? = null, // 主播名称
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Long? = null, // icon id
        @SerialName("is_lighted") val isLighted: Boolean? = null, // 是否点亮
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color") val medalColor: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor? = null,
        @SerialName("medal_level") val level: Int? = null,
        @SerialName("medal_name") val name: String? = null,
        @SerialName("special") val special: String? = null,
        @SerialName("target_id") val targetId: Long? = null, // 主播 mid
    )
}
