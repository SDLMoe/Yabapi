package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class RoomChangeCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: RoomChangeData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ROOM_CHANGE"
        override fun decode(json: Json, data: JsonElement): LiveCommand = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class RoomChangeData(
    @SerialName("title") val title: String,
    @SerialName("area_id") val areaId: Int,
    @SerialName("parent_area_id") val parentAreaId: Int,
    @SerialName("area_name") val areaName: String,
    @SerialName("parent_area_name") val parentAreaName: String,
    @SerialName("live_key") val liveKey: String,
    @SerialName("sub_session_key") val subSessionKey: String,
)
