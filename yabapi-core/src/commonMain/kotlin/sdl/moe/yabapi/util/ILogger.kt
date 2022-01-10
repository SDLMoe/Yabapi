// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

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
