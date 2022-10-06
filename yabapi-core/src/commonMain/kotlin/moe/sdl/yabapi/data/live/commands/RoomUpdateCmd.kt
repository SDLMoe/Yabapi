package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class RoomUpdateCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: RoomUpdateData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ROOM_REAL_TIME_MESSAGE_UPDATE"

        override fun decode(json: Json, data: JsonElement): RoomUpdateCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class RoomUpdateData(
    @SerialName("roomid") val roomId: Long? = null,
    @SerialName("fans") val fans: Int? = null,
    @SerialName("red_notice") val redNotice: Int? = null,
    @SerialName("fans_club") val fansClub: Int? = null, // 粉丝团数量
)
