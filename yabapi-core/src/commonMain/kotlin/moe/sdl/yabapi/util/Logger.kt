package moe.sdl.yabapi.util

import co.touchlab.kermit.Logger
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.enums.LogLevel
import moe.sdl.yabapi.enums.LogLevel.ASSERT
import moe.sdl.yabapi.enums.LogLevel.DEBUG
import moe.sdl.yabapi.enums.LogLevel.ERROR
import moe.sdl.yabapi.enums.LogLevel.INFO
import moe.sdl.yabapi.enums.LogLevel.VERBOSE
import moe.sdl.yabapi.enums.LogLevel.WARN
import moe.sdl.yabapi.enums.toKermitSeverity
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
public val yabapiLogLevel: AtomicRef<LogLevel> = atomic(INFO)

internal typealias LoggerFunc = (tag: String, level: LogLevel, throwable: Throwable?, message: () -> String) -> Unit

internal typealias Logger = StdOutLogger

internal class StdOutLogger(tag: String) : ILogger {
    private val tag = "[Yabapi] > $tag:"

    @Suppress("NOTHING_TO_INLINE")
    private inline fun log(level: LogLevel, throwable: Throwable?, noinline message: () -> String) {
        Yabapi.log.value(tag, level, throwable, message)
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
        logger.setMinSeverity(yabapiLogLevel.value.toKermitSeverity())
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
