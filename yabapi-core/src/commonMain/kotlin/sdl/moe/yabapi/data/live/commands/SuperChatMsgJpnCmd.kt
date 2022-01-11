// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import sdl.moe.yabapi.data.RgbColor
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import sdl.moe.yabapi.serializer.data.RgbColorStringSerializer

public data class SuperChatMsgJpnCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SuperChatJpnData,
    @SerialName("roomid") val roomid: Int,
) : LiveCommand {

    public companion object : LiveCommandFactory() {
        override val operation: String = "SUPER_CHAT_MESSAGE_JPN"
        override fun decode(json: Json, data: JsonElement): LiveCommand = json.decodeFromJsonElement(data)
    }
}

/**
 * 基本同 [SuperChatData], 多了一个 [messageJpn], 机翻日语
 */
public data class SuperChatJpnData(
    @SerialName("id") private val _id: String,
    @SerialName("uid") private val _uid: String,
    @SerialName("price") val price: Int,
    @SerialName("rate") val rate: Int,
    @SerialName("message") val message: String,
    @SerialName("message_jpn") val messageJpn: String,
    @SerialName("is_ranked") val isRanked: Boolean,
    @SerialName("background_image") val backgroundImage: String,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_color") val backgroundColor: RgbColor,
    @SerialName("background_icon") val backgroundIcon: String,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_price_color") val backgroundPriceColor: RgbColor,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("background_bottom_color") val backgroundBottomColor: RgbColor,
    @SerialName("ts") val timestamp: Long,
    @SerialName("token") val token: String,
    @SerialName("medal_info") val medalInfo: LiveMedal? = null,
    @SerialName("user_info") val userInfo: SuperChatUserInfo,
    @SerialName("time") val time: Int,
    @SerialName("start_time") val startTime: Long,
    @SerialName("end_time") val endTime: Long,
    @SerialName("gift") val gift: SuperChatLiveGift, // name: 醒目留言 id: 12000
) {
    val id: Int? by lazy { _id.toIntOrNull() }
    val uid: Int? by lazy { _uid.toIntOrNull() }

    @Serializable
    public data class LiveMedal(
        @SerialName("icon_id") val iconId: Int, // icon id
        @SerialName("target_id") val targetId: Int, // 主播 mid
        @SerialName("special") val special: String,
        @SerialName("anchor_uname") val liverName: String, // 主播名称
        @SerialName("anchor_roomid") val roomId: Int, // 房间id
        @SerialName("medal_level") val level: Int,
        @SerialName("medal_name") val name: String,
        @Serializable(RgbColorStringSerializer::class)
        @SerialName("medal_color") val medalColor: RgbColor,
    )
}
