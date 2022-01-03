// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual object Platform {
    actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Default
    actual val IS_BROWSER: Boolean = js(
        "typeof window !== 'undefined' && typeof window.document !== 'undefined' || typeof self !== 'undefined' && typeof self.location !== 'undefined'"
    ) as Boolean

    actual val IS_NODE: Boolean = js(
        "typeof process !== 'undefined' && process.versions != null && process.versions.node != null"
    ) as Boolean

    actual val IS_JVM: Boolean = false
    actual val IS_NATIVE: Boolean = false
}
