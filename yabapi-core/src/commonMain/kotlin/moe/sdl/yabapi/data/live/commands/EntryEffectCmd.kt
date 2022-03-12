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
    @SerialName("data") val data: EntryEffectData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "ENTRY_EFFECT"
        override fun decode(json: Json, data: JsonElement): EntryEffectCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class EntryEffectData(
    @SerialName("id") val id: Int? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("target_id") val targetId: Int? = null,
    @SerialName("mock_effect") val mockEffect: Int? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("privilege_type") val privilegeType: Int? = null,
    @SerialName("copy_writing") val copyWriting: String? = null,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("copy_color") val copyColor: RgbColor? = null,
    @Serializable(RgbColorStringSerializer::class)
    @SerialName("highlight_color") val highlightColor: RgbColor? = null,
    @SerialName("priority") val priority: Int? = null,
    @SerialName("basemap_url") val basemapUrl: String? = null,
    @SerialName("show_avatar") val showAvatar: Boolean? = null,
    @SerialName("effective_time") val effectiveTime: Int? = null,
    @SerialName("web_basemap_url") val webBasemapUrl: String? = null,
    @SerialName("web_effective_time") val webEffectiveTime: Int? = null,
    @SerialName("web_effect_close") val webEffectClose: Int? = null,
    @SerialName("web_close_time") val webCloseTime: Int? = null,
    @SerialName("business") val business: Int? = null,
    @SerialName("copy_writing_v2") val copyWritingV2: String? = null, // 欢迎 <^icon^> 提督 <%username%> 进入直播间",
    @SerialName("icon_list") val iconList: List<Int> = emptyList(),
    @SerialName("max_delay_time") val maxDelayTime: Int? = null,
    @SerialName("trigger_time") val triggerTime: Long? = null,
    @SerialName("identities") val identities: Int? = null,
    @SerialName("effect_silent_time") val effectSilentTime: Int? = null,
)
