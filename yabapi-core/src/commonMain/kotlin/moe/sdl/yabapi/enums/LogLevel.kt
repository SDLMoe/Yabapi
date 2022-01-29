package moe.sdl.yabapi.enums

import co.touchlab.kermit.Severity
import moe.sdl.yabapi.enums.LogLevel.ASSERT
import moe.sdl.yabapi.enums.LogLevel.DEBUG
import moe.sdl.yabapi.enums.LogLevel.ERROR
import moe.sdl.yabapi.enums.LogLevel.INFO
import moe.sdl.yabapi.enums.LogLevel.VERBOSE
import moe.sdl.yabapi.enums.LogLevel.WARN

public enum class LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT,
    ;
}

internal fun LogLevel.toKermitSeverity() = when (this) {
    VERBOSE -> Severity.Verbose
    DEBUG -> Severity.Debug
    INFO -> Severity.Info
    WARN -> Severity.Warn
    ERROR -> Severity.Error
    ASSERT -> Severity.Assert
}
