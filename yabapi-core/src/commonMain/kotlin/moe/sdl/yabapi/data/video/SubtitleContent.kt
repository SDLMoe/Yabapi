package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.DurationUnit.NANOSECONDS
import kotlin.time.DurationUnit.SECONDS
import kotlin.time.toDuration

@Serializable
public data class SubtitleContent(
    @SerialName("font_size") val fontSize: Double? = null,
    @SerialName("font_color") val fontColor: String? = null,
    @SerialName("background_alpha") val backgroundAlpha: Double? = null,
    @SerialName("background_color") val backgroundColor: String? = null,
    @SerialName("Stroke") val stroke: String? = null,
    @SerialName("body") val body: List<SubtitleDialog> = emptyList(),
)

@Serializable
public data class SubtitleDialog(
    @SerialName("from") val from: Double? = null,
    @SerialName("to") val to: Double? = null,
    @SerialName("location") val location: Int? = null, // always '2', bottom center
    @SerialName("content") val content: String? = null,
)

public fun List<SubtitleDialog>.encodeToSrt(): String {
    val sb = StringBuilder()
    this.asSequence().filter {
        it.to != null && it.from != null
    }.forEachIndexed { idx, dialog ->
        sb.appendLine(idx + 1)
        sb.appendLine(dialog.from!!.toSrtTime() + " --> " + dialog.to!!.toSrtTime())
        sb.appendLine(dialog.content)
        sb.appendLine()
    }
    return sb.toString()
}

private fun Double.toSrtTime(): String = this.toDuration(SECONDS).toComponents { h, m, s, ns ->
    "${h.padZero()}:${m.padZero()}:${s.padZero()},${ns.toDuration(NANOSECONDS).inWholeMilliseconds.padZero(3)}"
}

@Suppress("NOTHING_TO_INLINE")
private inline fun Number.padZero(digit: Int = 2) = this.toString().padStart(digit, '0')
