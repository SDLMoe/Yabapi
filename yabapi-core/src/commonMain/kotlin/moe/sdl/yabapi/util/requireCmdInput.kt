package moe.sdl.yabapi.util

import moe.sdl.yabapi.util.reflect.qualifiedOrSimpleName

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
            else -> throw IllegalArgumentException("Unsupported Number Type: ${T::class.qualifiedOrSimpleName}")
        }.let { it as? T? }
        if (input != null) {
            loop = false
        } else outFunc(errorMessage)
    }
    return input
        ?: throw IllegalArgumentException("Unable to parse input $input to ${T::class.qualifiedOrSimpleName}")
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
