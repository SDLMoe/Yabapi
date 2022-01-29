package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbaColor
import moe.sdl.yabapi.serializer.data.RgbaColorStringSerializer

@Serializable
public data class NoticeMsgCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("full") val full: Full,
    @SerialName("room_id") val roomId: Int,
    @SerialName("real_roomid") val realRoomid: Int,
    @SerialName("msg_common") val msgCommon: String,
    @SerialName("msg_self") val msgSelf: String,
    @SerialName("link_url") val linkUrl: String,
    @SerialName("msg_type") val msgType: Int,
    @SerialName("shield_uid") val shieldUid: Int,
    @SerialName("business_id") val businessId: String,
    @SerialName("scatter") val scatter: LiveScatter,
    @SerialName("marquee_id") val marqueeId: String,
    @SerialName("notice_type") val noticeType: String,
) : LiveCommand {
    @Serializable
    public data class Full(
        @SerialName("head_icon") val headIcon: String,
        @SerialName("tail_icon") val tailIcon: String,
        @SerialName("head_icon_fa") val headIconFa: String,
        @SerialName("tail_icon_fa") val tailIconFa: String,
        @SerialName("head_icon_fan") val headIconFan: Int,
        @SerialName("tail_icon_fan") val tailIconFan: Int,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("background") val background: RgbaColor,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("color") val color: RgbaColor,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("highlight") val highlight: RgbaColor,
        @SerialName("time") val time: Int,
    )

    @Serializable
    public data class Half(
        @SerialName("head_icon") val headIcon: String,
        @SerialName("tail_icon") val tailIcon: String,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("background") val background: RgbaColor,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("color") val color: RgbaColor,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("highlight") val highlight: RgbaColor,
        @SerialName("time") val time: Int,
    )

    @Serializable
    public data class Side(
        @SerialName("head_icon") val headIcon: String,
        @SerialName("background") val background: String,
        @SerialName("color") val color: String,
        @SerialName("highlight") val highlight: String,
        @SerialName("border") val border: String,
    )

    public companion object : LiveCommandFactory() {
        override val operation: String = "NOTICE_MSG"
        override fun decode(json: Json, data: JsonElement): NoticeMsgCmd = json.decodeFromJsonElement(data)
    }
}
