// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

import co.touchlab.kermit.Logger
import sdl.moe.yabapi.enums.LogLevel
import sdl.moe.yabapi.enums.LogLevel.ASSERT
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.enums.LogLevel.ERROR
import sdl.moe.yabapi.enums.LogLevel.INFO
import sdl.moe.yabapi.enums.LogLevel.VERBOSE
import sdl.moe.yabapi.enums.LogLevel.WARN
import sdl.moe.yabapi.enums.toKermitSeverity
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
public var yabapiLogLevel: LogLevel = INFO

internal typealias Logger = StdOutLogger

internal class StdOutLogger(tag: String) : ILogger {
    private val tag = "[Yabapi] > $tag:"

    @Suppress("NOTHING_TO_INLINE")
    private inline fun log(level: LogLevel, throwable: Throwable?, noinline message: () -> String) {
        if (level >= yabapiLogLevel) {
            println("${nowLocalString()} [${level.name}] $tag ${message()}")
            throwable?.printStackTrace()
        }
    }

    override fun verbose(throwable: Throwable?, message: () -> String) = log(VERBOSE, throwable, message)

    override fun verbose(message: () -> String) = log(VERBOSE, null, message)

    override fun debug(throwable: Throwable?, message: () -> String) = log(DEBUG, throwable, message)

    override fun debug(message: () -> String) = log(DEBUG, null, message)

    override fun info(throwable: Throwable?, message: () -> String) = log(INFO, throwable, message)

    override fun info(message: () -> String) = log(INFO, null, message)

    override fun warn(throwable: Throwable?, message: () -> String) = log(WARN, throwable, message)

    override fun warn(message: () -> String) = log(WARN, null, message)

    override fun error(throwable: Throwable?, message: () -> String) = log(ERROR, throwable, message)

    override fun error(message: () -> String) = log(ERROR, null, message)

    override fun assert(throwable: Throwable?, message: () -> String) = log(ASSERT, throwable, message)

    override fun assert(message: () -> String) = log(ASSERT, null, message)
}

@Suppress("OVERRIDE_BY_INLINE") // if a class is open, override inline function may cause error, but here is final
internal class KermitLogger(tag: String? = null) : ILogger {
    private val kermit: Logger = run {
        val logger = Logger
        tag?.also {
            logger.setTag("Yabapi > $it")
        } ?: logger.setTag("Yabapi")
        logger.setMinSeverity(yabapiLogLevel.toKermitSeverity())
        logger
    }

    override inline fun verbose(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.v(throwable, message) else kermit.v(message)

    override inline fun verbose(message: () -> String) = kermit.v(message)

    override inline fun debug(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.d(throwable, message) else kermit.w(message)

    override inline fun debug(message: () -> String) = kermit.d(message)

    override inline fun info(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.i(throwable, message) else kermit.w(message)

    override inline fun info(message: () -> String) = kermit.i(message)

    override inline fun warn(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.w(throwable, message) else kermit.w(message)

    override inline fun warn(message: () -> String) = kermit.w(message)

    override inline fun error(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.e(throwable, message) else kermit.w(message)

    override inline fun error(message: () -> String) = kermit.e(message)

    override inline fun assert(throwable: Throwable?, message: () -> String) =
        if (throwable != null) kermit.a(throwable, message) else kermit.w(message)

    override inline fun assert(message: () -> String) = kermit.a(message)
}
