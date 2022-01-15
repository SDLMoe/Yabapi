// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class RoomUpdateCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: RoomUpdateData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ROOM_REAL_TIME_MESSAGE_UPDATE"

        override fun decode(json: Json, data: JsonElement): RoomUpdateCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class RoomUpdateData(
    @SerialName("roomid") val roomId: Int,
    @SerialName("fans") val fans: Int,
    @SerialName("red_notice") val redNotice: Int,
    @SerialName("fans_club") val fansClub: Int, // 粉丝团数量
)
