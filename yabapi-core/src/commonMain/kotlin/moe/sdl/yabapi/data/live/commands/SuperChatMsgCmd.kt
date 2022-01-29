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
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializer

/**
 * SuperChat command
 */
@Serializable
public data class SuperChatMsgCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SuperChatData,
    @SerialName("roomid") val roomId: Int, // 短房间号
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "SUPER_CHAT_MESSAGE"
        override fun decode(json: Json, data: JsonElement): LiveCommand =
            json.decodeFromJsonElement<SuperChatMsgCmd>(data)
    }
}

@Serializable
public data class SuperChatData(
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_bottom_color") val backgroundBottomColor: RgbColor,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_color") val backgroundColor: RgbColor,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_color_end") val backgroundColorEnd: RgbColor,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_color_start") val backgroundColorStart: RgbColor,
    @SerialName("background_icon") val backgroundIcon: String,
    @SerialName("background_image") val backgroundImage: String,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_price_color") val backgroundPriceColor: RgbColor,
    @SerialName("color_point") val colorPoint: Double,
    @SerialName("dmscore") val danmakuScore: Int,
    @SerialName("end_time") val endTime: Long,
    @SerialName("gift") val gift: SuperChatLiveGift,
    @SerialName("id") val id: Int,
    @SerialName("is_ranked") val isRanked: Boolean,
    @SerialName("is_send_audit") val isSendAudit: Boolean,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("message") val message: String,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("message_font_color") val messageFontColor: RgbColor,
    @SerialName("message_trans") val messageTranslated: String,
    @SerialName("price") val price: Int,
    @SerialName("rate") val rate: Int,
    @SerialName("start_time") val startTime: Long,
    @SerialName("time") val time: Int,
    @SerialName("token") val token: String,
    @SerialName("trans_mark") val isTranslated: Boolean,
    @SerialName("ts") val timestamp: Long,
    @SerialName("uid") val uid: Int,
    @SerialName("user_info") val userInfo: SuperChatUserInfo,
) {
    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Int, // 房间id
        @SerialName("anchor_uname") val liverName: String, // 主播名称
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Int, // icon id
        @SerialName("is_lighted") val isLighted: Boolean, // 是否点亮
        @Serializable(RgbColorStringSerializer::class)
        @SerialName("medal_color") val medalColor: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor,
        @SerialName("medal_level") val level: Int,
        @SerialName("medal_name") val name: String,
        @SerialName("special") val special: String,
        @SerialName("target_id") val targetId: Int, // 主播 mid
    )
}

@Serializable
public data class SuperChatLiveGift(
    @SerialName("gift_id") val id: Int,
    @SerialName("gift_name") val name: String,
    @SerialName("num") val num: Int,
)

@Serializable
public data class SuperChatUserInfo(
    @SerialName("face") val avatar: String,
    @SerialName("face_frame") val avatarFrame: String,
    @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN,
    @SerialName("is_main_vip") val isMainVip: Boolean, // 是否主站大会员
    @SerialName("is_svip") val isSvip: Boolean, // 年费姥爷
    @SerialName("is_vip") val isVip: Boolean, // 月费姥爷
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("level_color") val levelColor: RgbColor,
    @SerialName("manager") val isManager: Boolean, // 是否管理员?
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("name_color") val nameColor: RgbColor? = null,
    @SerialName("title") val title: String,
    @SerialName("uname") val userName: String, // 用户名
    @SerialName("user_level") val userLevel: Int, // 勋章等级
)
