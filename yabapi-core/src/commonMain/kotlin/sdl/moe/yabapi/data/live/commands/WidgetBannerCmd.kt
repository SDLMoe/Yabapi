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
public data class WidgetBannerCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: WidgetBannerData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "WIDGET_BANNER"
        override fun decode(json: Json, data: JsonElement): WidgetBannerCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class WidgetBannerData(
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("widget_list") val map: Map<String, WidgetBanner> = mapOf(),
) {
    @Serializable
    public data class WidgetBanner(
        @SerialName("id") val id: Int,
        @SerialName("title") val title: String,
        @SerialName("cover") val cover: String,
        @SerialName("web_cover") val webCover: String,
        @SerialName("tip_text") val tipText: String,
        @SerialName("tip_text_color") val tipTextColor: String,
        @SerialName("tip_bottom_color") val tipBottomColor: String,
        @SerialName("jump_url") val jumpUrl: String,
        @SerialName("url") val url: String,
        @SerialName("stay_time") val stayTime: Int,
        @SerialName("site") val site: Int,
        @SerialName("platform_in") val platformIn: List<String>,
        @SerialName("type") val type: Int,
        @SerialName("band_id") val bandId: Int,
        @SerialName("sub_key") val subKey: String,
        @SerialName("sub_data") val subData: String,
        @SerialName("is_add") val isAdd: Boolean,
    )
}
