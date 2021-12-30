// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data

import kotlin.jvm.Transient

public data class RgbColor(
    @Transient val red: Int,
    @Transient val green: Int,
    @Transient val blue: Int,
) {
    public companion object {
        @Transient
        private val range = 1..255

        @Transient
        private val hexRegex: Regex = Regex("""^#([0-9a-fA-F]{6})$""")

        /**
         * @param hex hex RGB color code start with `#`, e.g. #B11EF6
         */
        public fun fromHex(hex: String): RgbColor {
            val deblanked = hex.replace(Regex("""\s+"""), "")
            val result = hexRegex.find(deblanked)?.groupValues?.getOrNull(1)
            requireNotNull(result) { "Input must be matched by ${hexRegex.pattern}" }
            val parsedHex = result.toIntOrNull(16)
            requireNotNull(parsedHex) { "Failed to parse hex number to Int" }
            return fromHex(parsedHex)
        }

        public fun fromHex(hex: Int): RgbColor {
            val r = (hex and 0xFF0000) shr 16
            val g = (hex and 0x00FF00) shr 8
            val b = (hex and 0x0000FF) shr 0
            return RgbColor(r, g, b)
        }
    }

    init {
        require(red in range && green in range && blue in range) {
            "RGB Value Should be in [1, 255]"
        }
    }

    val hex: String by lazy {
        "#" + intHex.toString(16).padStart(6, '0')
    }

    val intHex: Int by lazy {
        ((red and 0xFF) shl 16) or
            ((green and 0xFF) shl 8) or
            ((blue and 0xFF) shl 0)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return when (other) {
            is RgbColor -> (this.red == other.red) && (this.green == other.green) && (this.blue == other.blue)
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = red
        result = 31 * result + green
        result = 31 * result + blue
        return result
    }
}
