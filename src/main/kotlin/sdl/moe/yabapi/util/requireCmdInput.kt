// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

import javax.swing.text.html.HTML.Attribute.N

internal inline fun <reified N : Number> requireCmdInputNumber(
    message: String = "Please Input ${N::class.simpleName}:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println
): N {
    var loop = true
    var input: N? = null
    while (loop) {
        outFunc(message)
        input = when (N::class) {
            Byte::class -> readLine()?.toByteOrNull()
            Short::class -> readLine()?.toShortOrNull()
            Int::class -> readLine()?.toIntOrNull()
            Long::class -> readLine()?.toLongOrNull()
            Float::class -> readLine()?.toFloatOrNull()
            Double::class -> readLine()?.toDoubleOrNull()
            UByte::class -> readLine()?.toUByteOrNull()
            UShort::class -> readLine()?.toUShortOrNull()
            UInt::class -> readLine()?.toUIntOrNull()
            ULong::class -> readLine()?.toULongOrNull()
            else -> throw IllegalArgumentException("Unsupported Number Type: ${N::class.simpleName}")
        }.let { it as N? }
        if (input != null) {
            loop = false
        } else outFunc(errorMessage)
    }
    return input.also {
        outFunc("Your input is: $it")
    } ?: throw IllegalArgumentException("Unable to parse input $input to ${N::class.simpleName}")
}

internal fun requireCmdInputString(
    message: String = "Please Input ${N::class.simpleName}:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println
): String {
    outFunc(message)
    return readLine() ?: run {
        outFunc(errorMessage)
        requireCmdInputString(message, errorMessage, outFunc)
    }
}
