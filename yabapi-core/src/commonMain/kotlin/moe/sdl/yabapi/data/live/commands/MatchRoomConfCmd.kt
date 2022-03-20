package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializerNullable

@Serializable
public data class MatchRoomConfCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: MatchRoomConfData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "MATCH_ROOM_CONF"
        override fun decode(json: Json, data: JsonElement): MatchRoomConfCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class MatchRoomConfData(
    @SerialName("type") val type: String? = null,
    @SerialName("close_button") val closeButton: String? = null,
    @SerialName("force_push") val forcePush: String? = null,
    @SerialName("button_name") val buttonName: String? = null,
    @SerialName("background") val background: String? = null,
    @SerialName("conf_id") val confId: String? = null,
    @SerialName("conf_name") val confName: String? = null,
    @SerialName("rooms_info") val roomsInfo: List<LiveRoomInfo> = emptyList(),
    @SerialName("season_info") val seasonInfo: List<JsonElement> = emptyList(),
    @SerialName("background_url") val backgroundUrl: String? = null,
    @SerialName("scatter") val scatter: LiveScatter? = null,
    @SerialName("button_link") val buttonLink: String? = null,
    @SerialName("rooms_color") val roomsColor: LiveRoomColor? = null,
    @SerialName("state") val state: Int? = null,
) {

    @Serializable
    public data class LiveRoomInfo(
        @SerialName("room_id") private val _roomId: String? = null,
        @SerialName("room_name") val roomName: String? = null,
        @SerialName("live_status") val liveStatus: Int? = null,
    ) {
        val roomId: Int? by lazy { _roomId?.toIntOrNull() }
    }

    @Serializable
    public data class LiveRoomColor(
        @Serializable(RgbColorStringSerializerNullable::class)
        @SerialName("font_color") val fontColor: RgbColor? = null,
        @Serializable(RgbColorStringSerializerNullable::class)
        @SerialName("background_color") val backgroundColor: RgbColor? = null,
        @Serializable(RgbColorStringSerializerNullable::class)
        @SerialName("border_color") val borderColor: RgbColor? = null,
    )
}
