package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializer

@Serializable
public data class MatchRoomConfCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: MatchRoomConfData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "MATCH_ROOM_CONF"
        override fun decode(json: Json, data: JsonElement): MatchRoomConfCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class MatchRoomConfData(
    @SerialName("type") val type: String,
    @SerialName("close_button") val closeButton: String,
    @SerialName("force_push") val forcePush: String,
    @SerialName("button_name") val buttonName: String,
    @SerialName("background") val background: String,
    @SerialName("conf_id") val confId: String,
    @SerialName("conf_name") val confName: String,
    @SerialName("rooms_info") val roomsInfo: List<LiveRoomInfo>,
    @SerialName("season_info") val seasonInfo: List<JsonElement>,
    @SerialName("background_url") val backgroundUrl: String,
    @SerialName("scatter") val scatter: LiveScatter,
    @SerialName("button_link") val buttonLink: String,
    @SerialName("rooms_color") val roomsColor: LiveRoomColor,
    @SerialName("state") val state: Int,
) {

    @Serializable
    public data class LiveRoomInfo(
        @SerialName("room_id") private val _roomId: String,
        @SerialName("room_name") val roomName: String,
        @SerialName("live_status") val liveStatus: Int,
    ) {
        val roomId: Int? by lazy { _roomId.toIntOrNull() }
    }

    @Serializable
    public data class LiveRoomColor(
        @Serializable(RgbColorStringSerializer::class)
        @SerialName("font_color") val fontColor: RgbColor,
        @Serializable(RgbColorStringSerializer::class)
        @SerialName("background_color") val backgroundColor: RgbColor,
        @Serializable(RgbColorStringSerializer::class)
        @SerialName("border_color") val borderColor: RgbColor,
    )
}
