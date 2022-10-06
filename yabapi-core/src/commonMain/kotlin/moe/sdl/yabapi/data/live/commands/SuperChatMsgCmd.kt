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
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializerNullable

/**
 * SuperChat command
 */
@Serializable
public data class SuperChatMsgCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SuperChatData? = null,
    @SerialName("roomid") val roomId: Long? = null, // 短房间号
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "SUPER_CHAT_MESSAGE"
        override fun decode(json: Json, data: JsonElement): LiveCommand =
            json.decodeFromJsonElement<SuperChatMsgCmd>(data)
    }
}

@Serializable
public data class SuperChatData(
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_bottom_color") val backgroundBottomColor: RgbColor? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_color") val backgroundColor: RgbColor? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_color_end") val backgroundColorEnd: RgbColor? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_color_start") val backgroundColorStart: RgbColor? = null,
    @SerialName("background_icon") val backgroundIcon: String? = null,
    @SerialName("background_image") val backgroundImage: String? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_price_color") val backgroundPriceColor: RgbColor? = null,
    @SerialName("color_point") val colorPoint: Double? = null,
    @SerialName("dmscore") val danmakuScore: Int? = null,
    @SerialName("end_time") val endTime: Long? = null,
    @SerialName("gift") val gift: SuperChatLiveGift? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("is_ranked") val isRanked: Boolean? = null,
    @SerialName("is_send_audit") val isSendAudit: Boolean? = null,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("message") val message: String? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("message_font_color") val messageFontColor: RgbColor? = null,
    @SerialName("message_trans") val messageTranslated: String? = null,
    @SerialName("price") val price: Int? = null,
    @SerialName("rate") val rate: Int? = null,
    @SerialName("start_time") val startTime: Long? = null,
    @SerialName("time") val time: Int? = null,
    @SerialName("token") val token: String? = null,
    @SerialName("trans_mark") val isTranslated: Boolean? = null,
    @SerialName("ts") val timestamp: Long? = null,
    @SerialName("uid") val uid: Long? = null,
    @SerialName("user_info") val userInfo: SuperChatUserInfo? = null,
) {
    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Long? = null, // 房间id
        @SerialName("anchor_uname") val liverName: String? = null, // 主播名称
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Long? = null, // icon id
        @SerialName("is_lighted") val isLighted: Boolean? = null, // 是否点亮
        @Serializable(RgbColorStringSerializerNullable::class)
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

@Serializable
public data class SuperChatLiveGift(
    @SerialName("gift_id") val id: Long? = null,
    @SerialName("gift_name") val name: String? = null,
    @SerialName("num") val num: Int? = null,
)

@Serializable
public data class SuperChatUserInfo(
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_frame") val avatarFrame: String? = null,
    @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN,
    @SerialName("is_main_vip") val isMainVip: Boolean? = null, // 是否主站大会员
    @SerialName("is_svip") val isSvip: Boolean? = null, // 年费姥爷
    @SerialName("is_vip") val isVip: Boolean? = null, // 月费姥爷
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("level_color") val levelColor: RgbColor? = null,
    @SerialName("manager") val isManager: Boolean? = null, // 是否管理员?
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("name_color") val nameColor: RgbColor? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("uname") val userName: String? = null, // 用户名
    @SerialName("user_level") val userLevel: Int? = null, // 勋章等级
)
