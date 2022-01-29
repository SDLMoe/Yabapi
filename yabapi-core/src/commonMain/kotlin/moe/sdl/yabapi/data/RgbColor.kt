package moe.sdl.yabapi.data

import kotlinx.serialization.Serializable
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val colorRange by lazy { 0..255 }

private val hexRegex: Regex = Regex("""^#([0-9a-fA-F]{6,8})$""")

internal fun trimColor(input: String): String {
    val deblanked = input.replace(Regex("""\s+"""), "")
    val result = hexRegex.find(deblanked)?.groupValues?.getOrNull(1)
    requireNotNull(result) { "Input must be matched by ${hexRegex.pattern}, input: \"$input\"" }
    return result
}

internal fun requireColors(vararg color: Int) {
    color.forEach {
        require(it in colorRange) { "Color should be in range 0..255" }
    }
}

@Serializable
public data class RgbColor(
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    public companion object {
        /**
         * @param hex hex RGB color code start with `#`, e.g. #B11EF6
         */
        public fun fromHex(hex: String): RgbColor {
            val parsedHex = trimColor(hex).toIntOrNull(16)
            requireNotNull(parsedHex) { "Failed to parse hex number to Int" }
            return fromHex(parsedHex)
        }

        public fun fromHex(hex: Int): RgbColor {
            val r = (hex and 0xFF0000) shr 16
            val g = (hex and 0x00FF00) shr 8
            val b = (hex and 0x0000FF) shr 0
            return RgbColor(r, g, b)
        }

        public fun fromHex(hex: UInt): RgbColor {
            val r = (hex and 0xFF0000u) shr 16
            val g = (hex and 0x00FF00u) shr 8
            val b = (hex and 0x0000FFu) shr 0
            return RgbColor(r.toInt(), g.toInt(), b.toInt())
        }
    }

    init {
        requireColors(red, green, blue)
    }

    public val hex: String by lazy {
        "#" + intHex.toString(16).padStart(6, '0')
    }

    public val intHex: Int by lazy {
        ((red and 0xFF) shl 16) or
            ((green and 0xFF) shl 8) or
            ((blue and 0xFF) shl 0)
    }
}

public fun RgbColor.toRgba(): RgbaColor = RgbaColor(red, green, blue, 0xFF)
