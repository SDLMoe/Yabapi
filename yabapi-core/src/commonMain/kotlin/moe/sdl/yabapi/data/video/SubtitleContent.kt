package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class SubtitleContent(
    @SerialName("font_size") val fontSize: Double? = null,
    @SerialName("font_color") val fontColor: String? = null,
    @SerialName("background_alpha") val backgroundAlpha: Double? = null,
    @SerialName("background_color") val backgroundColor: String? = null,
    @SerialName("Stroke") val stroke: String? = null,
    @SerialName("body") val body: List<SubtitleDialog>? = null,
)

@Serializable
public data class SubtitleDialog(
    @SerialName("from") val from: Double? = null,
    @SerialName("to") val to: Double? = null,
    @SerialName("location") val location: Int? = null, // always '2', bottom center
    @SerialName("content") val content: String? = null,
)
