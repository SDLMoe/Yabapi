// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual object Platform {
    actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    actual val IS_BROWSER: Boolean = false
    actual val IS_NODE: Boolean = false
    actual val IS_JVM: Boolean = true
    actual val IS_NATIVE: Boolean = false
}
