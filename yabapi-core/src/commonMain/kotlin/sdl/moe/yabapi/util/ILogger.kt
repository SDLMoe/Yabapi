// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

internal interface ILogger {

    fun verbose(throwable: Throwable? = null, message: () -> String)

    fun debug(throwable: Throwable? = null, message: () -> String)

    fun info(throwable: Throwable? = null, message: () -> String)

    fun warn(throwable: Throwable? = null, message: () -> String)

    fun error(throwable: Throwable? = null, message: () -> String)

    fun assert(throwable: Throwable? = null, message: () -> String)
}
