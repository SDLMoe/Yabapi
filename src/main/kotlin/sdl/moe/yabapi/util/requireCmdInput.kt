// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

internal inline fun <reified T : Number> requireCmdInputNumber(
    message: String = "Please Input ${T::class.simpleName}:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println
): T  {
    var loop = true

    var input: T? = null
    while (loop) {
        outFunc(message)
        input = when (T::class) {
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
            else -> throw IllegalArgumentException("Unsupported Number Type: ${T::class.qualifiedName}")
        }.let { it as T? }
        if (input != null) {
            loop = false
        } else outFunc(errorMessage)
    }
    return input ?: throw IllegalArgumentException("Unable to parse input $input to ${T::class.qualifiedName}")
}

internal fun requireCmdInputString(
    message: String = "Please Input String:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println
): String {
    outFunc(message)
    return readLine() ?: run {
        outFunc(errorMessage)
        requireCmdInputString(message, errorMessage, outFunc)
    }
}
