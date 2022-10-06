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
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("full") val full: Full? = null,
    @SerialName("half") val half: Half? = null,
    @SerialName("room_id") val roomId: Long? = null,
    @SerialName("real_roomid") val realRoomid: Long? = null,
    @SerialName("msg_common") val msgCommon: String? = null,
    @SerialName("msg_self") val msgSelf: String? = null,
    @SerialName("link_url") val linkUrl: String? = null,
    @SerialName("msg_type") val msgType: Int? = null,
    @SerialName("shield_uid") val shieldUid: Long? = null,
    @SerialName("business_id") val businessId: String? = null,
    @SerialName("scatter") val scatter: LiveScatter? = null,
    @SerialName("marquee_id") val marqueeId: String? = null,
    @SerialName("notice_type") val noticeType: String? = null,
) : LiveCommand {
    @Serializable
    public data class Full(
        @SerialName("head_icon") val headIcon: String? = null,
        @SerialName("tail_icon") val tailIcon: String? = null,
        @SerialName("head_icon_fa") val headIconFa: String? = null,
        @SerialName("tail_icon_fa") val tailIconFa: String? = null,
        @SerialName("head_icon_fan") val headIconFan: Int? = null,
        @SerialName("tail_icon_fan") val tailIconFan: Int? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("background") val background: RgbaColor? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("color") val color: RgbaColor? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("highlight") val highlight: RgbaColor? = null,
        @SerialName("time") val time: Int? = null,
    )

    @Serializable
    public data class Half(
        @SerialName("head_icon") val headIcon: String? = null,
        @SerialName("tail_icon") val tailIcon: String? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("background") val background: RgbaColor? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("color") val color: RgbaColor? = null,
        @Serializable(RgbaColorStringSerializer::class)
        @SerialName("highlight") val highlight: RgbaColor? = null,
        @SerialName("time") val time: Int? = null,
    )

    @Serializable
    public data class Side(
        @SerialName("head_icon") val headIcon: String? = null,
        @SerialName("background") val background: String? = null,
        @SerialName("color") val color: String? = null,
        @SerialName("highlight") val highlight: String? = null,
        @SerialName("border") val border: String? = null,
    )

    public companion object : LiveCommandFactory() {
        override val operation: String = "NOTICE_MSG"
        override fun decode(json: Json, data: JsonElement): NoticeMsgCmd = json.decodeFromJsonElement(data)
    }
}
