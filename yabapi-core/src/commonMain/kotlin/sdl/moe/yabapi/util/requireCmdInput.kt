// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

internal inline fun <reified T : Number> requireCmdInputNumber(
    message: String = "Please Input ${T::class.simpleName}:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println,
): T {
    var loop = true

    var input: T? = null
    while (loop) {
        outFunc(message)
        input = when (T::class) {
            Byte::class -> readlnOrNull()?.toByteOrNull()
            Short::class -> readlnOrNull()?.toShortOrNull()
            Int::class -> readlnOrNull()?.toIntOrNull()
            Long::class -> readlnOrNull()?.toLongOrNull()
            Float::class -> readlnOrNull()?.toFloatOrNull()
            Double::class -> readlnOrNull()?.toDoubleOrNull()
            UByte::class -> readlnOrNull()?.toUByteOrNull()
            UShort::class -> readlnOrNull()?.toUShortOrNull()
            UInt::class -> readlnOrNull()?.toUIntOrNull()
            ULong::class -> readlnOrNull()?.toULongOrNull()
            else -> throw IllegalArgumentException("Unsupported Number Type: ${T::class.qualifiedName ?: T::class.simpleName}")
        }.let { it as T? }
        if (input != null) {
            loop = false
        } else outFunc(errorMessage)
    }
    return input
        ?: throw IllegalArgumentException("Unable to parse input $input to ${T::class.qualifiedName ?: T::class.simpleName}")
}

internal fun requireCmdInputString(
    message: String = "Please Input String:",
    errorMessage: String = "Your input is not valid, please try again.",
    outFunc: (String) -> Unit = ::println,
): String {
    outFunc(message)
    return readlnOrNull() ?: run {
        outFunc(errorMessage)
        requireCmdInputString(message, errorMessage, outFunc)
    }
}
