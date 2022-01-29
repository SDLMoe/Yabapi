package moe.sdl.yabapi.data

import kotlinx.serialization.Serializable
import kotlin.math.round

@Serializable
public data class RgbaColor(
    val red: Int,
    val green: Int,
    val blue: Int,
    val alpha: Int,
) {
    init {
        requireColors(red, green, blue, alpha)
    }

    public val longHex: Long
        get() = ((red.toLong() and 0xFF) shl 24) or
            ((green.toLong() and 0xFF) shl 16) or
            ((blue.toLong() and 0xFF) shl 8) or
            ((alpha.toLong() and 0xFF) shl 0)

    public val hex: String
        get() = "#${longHex.toString(16).padStart(8, '0')}"

    public companion object {
        public fun fromLong(hex: Long): RgbaColor {
            val r = (hex and 0xFF000000) shr 24
            val g = (hex and 0x00FF0000) shr 16
            val b = (hex and 0x0000FF00) shr 8
            val a = (hex and 0x000000FF) shr 0
            return RgbaColor(r.toInt(), g.toInt(), b.toInt(), a.toInt())
        }

        public fun fromHex(data: String): RgbaColor {
            val parsed = trimColor(data).toLongOrNull(16)
            requireNotNull(parsed) { "Decode failed" }
            return fromLong(parsed)
        }
    }
}

/**
 * convert rgba to rgb, overlapped with white background
 */
public fun RgbaColor.toRgb(): RgbColor {
    val alpha = alpha / 255.0
    return RgbColor(
        round(red * alpha).toInt(),
        round(green * alpha).toInt(),
        round(blue * alpha).toInt()
    )
}
