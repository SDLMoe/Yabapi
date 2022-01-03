// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

private val hexChars by lazy { "0123456789abcdef".toCharArray() }

@Suppress("MagicNumber")
internal val ByteArray.hex: String
    // Exprimental Code, replace when stabled
    // get() = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
    get() {
        val hex = CharArray(2 * this.size)
        this.forEachIndexed { i, byte ->
            val unsigned = 0xff and byte.toInt()
            hex[2 * i] = hexChars[unsigned / 16]
            hex[2 * i + 1] = hexChars[unsigned % 16]
        }

        return hex.joinToString("")
    }
