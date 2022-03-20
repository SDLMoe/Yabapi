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
public data class InteractWordCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: InteractWordData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "INTERACT_WORD"
        override fun decode(json: Json, data: JsonElement): InteractWordCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class InteractWordData(
    @SerialName("contribution") val contribution: Contribution? = null,
    @SerialName("dmscore") val danmakuScore: Int? = null,
    @SerialName("fans_medal") val fansMedal: LiveMedal? = null,
    @SerialName("identities") val identities: List<Int> = emptyList(),
    @SerialName("is_spread") val isSpread: Boolean? = null,
    @SerialName("msg_type") val msgType: Int? = null,
    @SerialName("roomid") val roomId: Int? = null,
    @SerialName("score") val score: Long? = null,
    @SerialName("spread_desc") val spreadDesc: String? = null,
    @SerialName("spread_info") val spreadInfo: String? = null,
    @SerialName("tail_icon") val tailIcon: Int? = null,
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("trigger_time") val triggerTime: Long? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val userName: String? = null,
    @SerialName("uname_color") val userNameColor: String? = null,
) {
    @Serializable
    public data class Contribution(
        @SerialName("grade") val grade: Int? = null,
    )

    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Int? = null, // 房间id
        @SerialName("guard_level") val guardLevel: GuardLevel = UNKNOWN, // 等级
        @SerialName("icon_id") val iconId: Int? = null, // icon id
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
        @SerialName("score") val score: Int? = null,
        @SerialName("special") val special: String? = null,
        @SerialName("target_id") val targetId: Int? = null, // 主播 mid
    )
}
