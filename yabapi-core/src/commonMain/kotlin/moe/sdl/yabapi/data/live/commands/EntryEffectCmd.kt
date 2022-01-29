@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorStringSerializer

@Serializable
public data class EntryEffectCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: EntryEffectData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ENTRY_EFFECT"
        override fun decode(json: Json, data: JsonElement): EntryEffectCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class EntryEffectData(
    @SerialName("id") val id: Int,
    @SerialName("uid") val uid: Int,
    @SerialName("target_id") val targetId: Int,
    @SerialName("mock_effect") val mockEffect: Int,
    @SerialName("face") val avatar: String,
    @SerialName("privilege_type") val privilegeType: Int,
    @SerialName("copy_writing") val copyWriting: String,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("copy_color") val copyColor: RgbColor,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("highlight_color") val highlightColor: RgbColor,
    @SerialName("priority") val priority: Int,
    @SerialName("basemap_url") val basemapUrl: String,
    @SerialName("show_avatar") val showAvatar: Boolean,
    @SerialName("effective_time") val effectiveTime: Int,
    @SerialName("web_basemap_url") val webBasemapUrl: String,
    @SerialName("web_effective_time") val webEffectiveTime: Int,
    @SerialName("web_effect_close") val webEffectClose: Int,
    @SerialName("web_close_time") val webCloseTime: Int,
    @SerialName("business") val business: Int,
    @SerialName("copy_writing_v2") val copyWritingV2: String, // 欢迎 <^icon^> 提督 <%username%> 进入直播间",
    @SerialName("icon_list") val iconList: List<Int>,
    @SerialName("max_delay_time") val maxDelayTime: Int,
    @SerialName("trigger_time") val triggerTime: Long,
    @SerialName("identities") val identities: Int,
)
