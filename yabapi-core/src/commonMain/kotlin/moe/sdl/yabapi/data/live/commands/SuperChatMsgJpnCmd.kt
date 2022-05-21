@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializerNullable

public data class SuperChatMsgJpnCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SuperChatJpnData? = null,
    @SerialName("roomid") val roomid: Int? = null,
) : LiveCommand {

    public companion object : LiveCommandFactory() {
        override val operation: String = "SUPER_CHAT_MESSAGE_JPN"
        override fun decode(json: Json, data: JsonElement): SuperChatMsgJpnCmd = json.decodeFromJsonElement(data)
    }
}

/**
 * 基本同 [SuperChatData], 多了一个 [messageJpn], 机翻日语
 */
public data class SuperChatJpnData(
    @SerialName("id") private val _id: String? = null,
    @SerialName("uid") private val _uid: String? = null,
    @SerialName("price") val price: Int? = null,
    @SerialName("rate") val rate: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("message_jpn") val messageJpn: String? = null,
    @SerialName("is_ranked") val isRanked: Boolean? = null,
    @SerialName("background_image") val backgroundImage: String? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_color") val backgroundColor: RgbColor? = null,
    @SerialName("background_icon") val backgroundIcon: String? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_price_color") val backgroundPriceColor: RgbColor? = null,
    @Serializable(RgbColorStringSerializerNullable::class)
    @SerialName("background_bottom_color") val backgroundBottomColor: RgbColor? = null,
    @SerialName("ts") val timestamp: Long? = null,
    @SerialName("token") val token: String? = null,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("user_info") val userInfo: SuperChatUserInfo? = null,
    @SerialName("time") val time: Int? = null,
    @SerialName("start_time") val startTime: Long? = null,
    @SerialName("end_time") val endTime: Long? = null,
    @SerialName("gift") val gift: SuperChatLiveGift? = null, // name: 醒目留言 id: 12000
) {
    val id: Int? by lazy { _id?.toIntOrNull() }
    val uid: Int? by lazy { _uid?.toIntOrNull() }

    @Serializable
    public data class LiveMedal(
        @SerialName("icon_id") val iconId: Int? = null, // icon id
        @SerialName("target_id") val targetId: Int? = null, // 主播 mid
        @SerialName("special") val special: String? = null,
        @SerialName("anchor_uname") val liverName: String? = null, // 主播名称
        @SerialName("anchor_roomid") val roomId: Int? = null, // 房间id
        @SerialName("medal_level") val level: Int? = null,
        @SerialName("medal_name") val name: String? = null,
        @Serializable(RgbColorStringSerializerNullable::class)
        @SerialName("medal_color") val medalColor: RgbColor? = null,
    )
}
