package moe.sdl.yabapi.enums

public enum class LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    ASSERT,
    ;
}

// internal fun LogLevel.toKermitSeverity() = when (this) {
//     VERBOSE -> Severity.Verbose
//     DEBUG -> Severity.Debug
//     INFO -> Severity.Info
//     WARN -> Severity.Warn
//     ERROR -> Severity.Error
//     ASSERT -> Severity.Assert
// }
