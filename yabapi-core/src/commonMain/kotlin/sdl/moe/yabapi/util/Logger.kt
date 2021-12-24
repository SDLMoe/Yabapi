// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

internal val kermit: co.touchlab.kermit.Logger by lazy {
    co.touchlab.kermit.Logger
}

internal val logger = Logger

internal object Logger : ILogger {
    override fun verbose(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.v(throwable, message)
        } else kermit.v(message)

    override fun debug(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.d(throwable, message)
        } else kermit.d(message)

    override fun info(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.i(throwable, message)
        } else kermit.i(message)

    override fun warn(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.w(throwable, message)
        } else kermit.w(message)

    override fun error(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.e(throwable, message)
        } else kermit.e(message)

    override fun assert(throwable: Throwable?, message: () -> String) =
        if (throwable != null) {
            kermit.a(throwable, message)
        } else kermit.a(message)
}
