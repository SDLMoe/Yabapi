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
import sdl.moe.yabapi.data.live.GuardLevel
import sdl.moe.yabapi.data.live.GuardLevel.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer
import sdl.moe.yabapi.serializer.data.RgbColorIntSerializer

@Serializable
public data class InteractWordCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: InteractWordData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "INTERACT_WORD"
        override fun decode(json: Json, data: JsonElement): InteractWordCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class InteractWordData(
    @SerialName("contribution") val contribution: Contribution,
    @SerialName("dmscore") val danmakuScore: Int,
    @SerialName("fans_medal") val fansMedal: LiveMedal? = null,
    @SerialName("identities") val identities: List<Int> = emptyList(),
    @SerialName("is_spread") val isSpread: Boolean,
    @SerialName("msg_type") val msgType: Int,
    @SerialName("roomid") val roomId: Int,
    @SerialName("score") val score: Long,
    @SerialName("spread_desc") val spreadDesc: String,
    @SerialName("spread_info") val spreadInfo: String,
    @SerialName("tail_icon") val tailIcon: Int,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("trigger_time") val triggerTime: Long,
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val userName: String,
    @SerialName("uname_color") val userNameColor: String,
) {
    @Serializable
    public data class Contribution(
        @SerialName("grade") val grade: Int,
    )


    @Serializable
    public data class LiveMedal(
        @SerialName("anchor_roomid") val roomId: Int, // 房间id
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
        @SerialName("medal_name") val name: String,
        @SerialName("score") val score: Int,
        @SerialName("special") val special: String,
        @SerialName("target_id") val targetId: Int, // 主播 mid
    )

}
