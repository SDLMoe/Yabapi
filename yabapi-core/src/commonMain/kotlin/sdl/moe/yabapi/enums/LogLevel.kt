// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.enums

import co.touchlab.kermit.Severity
import sdl.moe.yabapi.enums.LogLevel.ASSERT
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.enums.LogLevel.ERROR
import sdl.moe.yabapi.enums.LogLevel.INFO
import sdl.moe.yabapi.enums.LogLevel.VERBOSE
import sdl.moe.yabapi.enums.LogLevel.WARN

public enum class LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT
}

internal fun LogLevel.toKermitSeverity() = when (this) {
    VERBOSE -> Severity.Verbose
    DEBUG -> Severity.Debug
    INFO -> Severity.Info
    WARN -> Severity.Warn
    ERROR -> Severity.Error
    ASSERT -> Severity.Assert
}
