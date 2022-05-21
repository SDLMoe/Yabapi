package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class WidgetBannerCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: WidgetBannerData? = null,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "WIDGET_BANNER"
        override fun decode(json: Json, data: JsonElement): WidgetBannerCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class WidgetBannerData(
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("widget_list") val map: Map<String, WidgetBanner?> = mapOf(),
) {
    @Serializable
    public data class WidgetBanner(
        @SerialName("id") val id: Int? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("cover") val cover: String? = null,
        @SerialName("web_cover") val webCover: String? = null,
        @SerialName("tip_text") val tipText: String? = null,
        @SerialName("tip_text_color") val tipTextColor: String? = null,
        @SerialName("tip_bottom_color") val tipBottomColor: String? = null,
        @SerialName("jump_url") val jumpUrl: String? = null,
        @SerialName("url") val url: String? = null,
        @SerialName("stay_time") val stayTime: Int? = null,
        @SerialName("site") val site: Int? = null,
        @SerialName("platform_in") val platformIn: List<String> = emptyList(),
        @SerialName("type") val type: Int? = null,
        @SerialName("band_id") val bandId: Int? = null,
        @SerialName("sub_key") val subKey: String? = null,
        @SerialName("sub_data") val subData: String? = null,
        @SerialName("is_add") val isAdd: Boolean? = null,
    )
}
