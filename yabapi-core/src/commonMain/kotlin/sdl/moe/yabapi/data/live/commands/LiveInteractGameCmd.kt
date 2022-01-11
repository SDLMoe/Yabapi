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
import sdl.moe.yabapi.data.live.GuardLevel
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveInteractGameCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: LiveGameData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "LIVE_INTERACTIVE_GAME"
        override fun decode(json: Json, data: JsonElement): LiveInteractGameCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class LiveGameData(
    @SerialName("type") val type: Int,
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val userName: String,
    @SerialName("uface") val userAvatar: String,
    @SerialName("gift_id") val giftId: Int,
    @SerialName("gift_name") val giftName: String,
    @SerialName("gift_num") val giftNum: Int,
    @SerialName("price") val price: Int,
    @SerialName("paid") val paid: Boolean,
    @SerialName("msg") val msg: String,
    @SerialName("fans_medal_level") val fansMedalLevel: Int,
    @SerialName("guard_level") val guardLevel: GuardLevel,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("anchor_lottery") val anchorLottery: JsonElement? = null,
    @SerialName("pk_info") val pkInfo: JsonElement? = null,
    @SerialName("anchor_info") val anchorInfo: AnchorInfo? = null,
) {
    @Serializable
    public data class AnchorInfo(
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val userName: String,
        @SerialName("uface") val userAvatar: String,
    )
}
