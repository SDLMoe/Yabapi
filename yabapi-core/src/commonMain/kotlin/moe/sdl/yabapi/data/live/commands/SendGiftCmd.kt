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
import moe.sdl.yabapi.serializer.data.RgbColorIntSerializer

@Serializable
public data class SendGiftCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SendGiftData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "SEND_GIFT"
        override fun decode(json: Json, data: JsonElement): SendGiftCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class SendGiftData(
    @SerialName("action") val action: String? = null,
    @SerialName("batch_combo_id") val batchComboId: String? = null,
    @SerialName("batch_combo_send") val batchComboSend: BatchComboGift?,
    @SerialName("beatId") val beatId: String? = null,
    @SerialName("biz_source") val bizSource: String? = null, // "Live" or "live"
    @SerialName("blind_gift") val blindGift: BlindGift? = null,
    @SerialName("broadcast_id") val broadcastId: Int? = null,
    @SerialName("coin_type") val coinType: String? = null,
    @SerialName("combo_resources_id") val comboResourcesId: Int? = null,
    @SerialName("combo_send") val comboSend: ComboSend? = null,
    @SerialName("combo_stay_time") val comboStayTime: Int? = null,
    @SerialName("combo_total_coin") val comboTotalCoin: Int? = null,
    @SerialName("crit_prob") val criticalRate: Double? = null, // 爆击率
    @SerialName("demarcation") val demarcation: Int? = null, // 划界?
    @SerialName("discount_price") val discountPrice: Int? = null,
    @SerialName("dmscore") val danmakuScore: Int? = null,
    @SerialName("draw") val draw: Int? = null,
    @SerialName("effect") val effect: Int? = null,
    @SerialName("effect_block") val effectBlock: Int? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("float_sc_resource_id") val floatScResourceId: Int? = null,
    @SerialName("giftId") val giftId: Int? = null,
    @SerialName("giftName") val giftName: String? = null,
    @SerialName("giftType") val giftType: Int? = null,
    @SerialName("gold") val gold: Int? = null,
    @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN,
    @SerialName("is_first") val isFirst: Boolean? = null,
    @SerialName("is_special_batch") val isSpecialBatch: Boolean? = null,
    @SerialName("magnification") val magnification: Double? = null,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("name_color") val nameColor: String? = null,
    @SerialName("num") val num: Int? = null,
    @SerialName("original_gift_name") val originalGiftName: String? = null,
    @SerialName("price") val price: Int? = null,
    @SerialName("rcost") val targetCost: Int? = null, // 可能是对目标主播的花费总数?
    @SerialName("remain") val remain: Int? = null,
    @SerialName("rnd") val rnd: String? = null,
    @SerialName("send_master") val sendMaster: JsonElement? = null,
    @SerialName("silver") val silver: Int? = null,
    @SerialName("super") val superValue: Int? = null, // super 是关键字...
    @SerialName("super_batch_gift_num") val superBatchGiftNum: Int? = null,
    @SerialName("super_gift_num") val superGiftNum: Int? = null,
    @SerialName("svga_block") val svgaBlock: Int? = null,
    @SerialName("tag_image") val tagImage: String? = null,
    @SerialName("tid") val tid: String? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("top_list") val topList: JsonElement? = null, // 似乎恒为 null
    @SerialName("total_coin") val totalCoin: Int? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val uname: String? = null,
) {
    @Serializable
    public data class ComboSend(
        @SerialName("action") val action: String? = null,
        @SerialName("combo_id") val comboId: String? = null,
        @SerialName("combo_num") val comboNum: Int? = null,
        @SerialName("gift_id") val giftId: Int? = null,
        @SerialName("gift_name") val giftName: String? = null,
        @SerialName("gift_num") val giftNum: Int? = null,
        @SerialName("send_master") val sendMaster: JsonElement? = null,
        @SerialName("uid") val uid: Int? = null,
        @SerialName("uname") val username: String? = null,
    )

    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Int? = null, // 房间id
        @SerialName("anchor_uname") val liverName: String? = null, // 主播名称
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Int? = null, // icon id
        @SerialName("is_lighted") val isLighted: Boolean? = null, // 是否点亮
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color") val medalColor: RgbColor? = null,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor? = null,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor? = null,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor? = null,
        @SerialName("medal_level") val level: Int? = null,
        @SerialName("medal_name") val name: String? = null,
        @SerialName("special") val special: String? = null,
        @SerialName("score") val score: Int? = null,
        @SerialName("target_id") val targetId: Int? = null, // 主播 mid
    )
}
