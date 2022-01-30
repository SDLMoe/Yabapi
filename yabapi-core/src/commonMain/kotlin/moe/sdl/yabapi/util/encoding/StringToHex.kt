package moe.sdl.yabapi.util.encoding

import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
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
