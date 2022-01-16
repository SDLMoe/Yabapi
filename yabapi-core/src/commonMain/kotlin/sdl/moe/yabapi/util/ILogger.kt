package sdl.moe.yabapi.util

internal interface ILogger {

    fun verbose(throwable: Throwable?, message: () -> String)

    fun debug(throwable: Throwable?, message: () -> String)

    fun info(throwable: Throwable?, message: () -> String)

    fun warn(throwable: Throwable?, message: () -> String)

    fun error(throwable: Throwable?, message: () -> String)

    fun assert(throwable: Throwable?, message: () -> String)

    fun verbose(message: () -> String)

    fun debug(message: () -> String)

    fun info(message: () -> String)

    fun warn(message: () -> String)

    fun error(message: () -> String)

    fun assert(message: () -> String)
}
