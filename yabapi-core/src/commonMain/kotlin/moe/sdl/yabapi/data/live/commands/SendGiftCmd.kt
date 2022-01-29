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
    @SerialName("data") val data: SendGiftData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "SEND_GIFT"
        override fun decode(json: Json, data: JsonElement): SendGiftCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class SendGiftData(
    @SerialName("action") val action: String,
    @SerialName("batch_combo_id") val batchComboId: String,
    @SerialName("batch_combo_send") val batchComboSend: BatchComboGift?,
    @SerialName("beatId") val beatId: String,
    @SerialName("biz_source") val bizSource: String, // "Live" or "live"
    @SerialName("blind_gift") val blindGift: BlindGift? = null,
    @SerialName("broadcast_id") val broadcastId: Int,
    @SerialName("coin_type") val coinType: String,
    @SerialName("combo_resources_id") val comboResourcesId: Int,
    @SerialName("combo_send") val comboSend: ComboSend? = null,
    @SerialName("combo_stay_time") val comboStayTime: Int,
    @SerialName("combo_total_coin") val comboTotalCoin: Int,
    @SerialName("crit_prob") val criticalRate: Double, // 爆击率
    @SerialName("demarcation") val demarcation: Int, // 划界?
    @SerialName("discount_price") val discountPrice: Int,
    @SerialName("dmscore") val danmakuScore: Int,
    @SerialName("draw") val draw: Int,
    @SerialName("effect") val effect: Int,
    @SerialName("effect_block") val effectBlock: Int,
    @SerialName("face") val avatar: String,
    @SerialName("float_sc_resource_id") val floatScResourceId: Int,
    @SerialName("giftId") val giftId: Int,
    @SerialName("giftName") val giftName: String,
    @SerialName("giftType") val giftType: Int,
    @SerialName("gold") val gold: Int,
    @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN,
    @SerialName("is_first") val isFirst: Boolean,
    @SerialName("is_special_batch") val isSpecialBatch: Boolean,
    @SerialName("magnification") val magnification: Double,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("name_color") val nameColor: String,
    @SerialName("num") val num: Int,
    @SerialName("original_gift_name") val originalGiftName: String,
    @SerialName("price") val price: Int,
    @SerialName("rcost") val targetCost: Int, // 可能是对目标主播的花费总数?
    @SerialName("remain") val remain: Int,
    @SerialName("rnd") val rnd: String,
    @SerialName("send_master") val sendMaster: JsonElement? = null,
    @SerialName("silver") val silver: Int,
    @SerialName("super") val superValue: Int, // super 是关键字...
    @SerialName("super_batch_gift_num") val superBatchGiftNum: Int,
    @SerialName("super_gift_num") val superGiftNum: Int,
    @SerialName("svga_block") val svgaBlock: Int,
    @SerialName("tag_image") val tagImage: String,
    @SerialName("tid") val tid: String,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("top_list") val topList: JsonElement? = null, // 似乎恒为 null
    @SerialName("total_coin") val totalCoin: Int,
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val uname: String,
) {
    @Serializable
    public data class ComboSend(
        @SerialName("action") val action: String,
        @SerialName("combo_id") val comboId: String,
        @SerialName("combo_num") val comboNum: Int,
        @SerialName("gift_id") val giftId: Int,
        @SerialName("gift_name") val giftName: String,
        @SerialName("gift_num") val giftNum: Int,
        @SerialName("send_master") val sendMaster: JsonElement? = null,
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val username: String,
    )

    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Int, // 房间id
        @SerialName("anchor_uname") val liverName: String, // 主播名称
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Int, // icon id
        @SerialName("is_lighted") val isLighted: Boolean, // 是否点亮
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color") val medalColor: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor,
        @SerialName("medal_level") val level: Int,
        @SerialName("medal_name") val name: String? = null,
        @SerialName("special") val special: String? = null,
        @SerialName("score") val score: Int? = null,
        @SerialName("target_id") val targetId: Int, // 主播 mid
    )
}
