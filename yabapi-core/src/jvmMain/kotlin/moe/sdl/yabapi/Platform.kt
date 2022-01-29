package moe.sdl.yabapi

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual object Platform {
    actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    actual val IS_BROWSER: Boolean = false
    actual val IS_NODE: Boolean = false
    actual val IS_JVM: Boolean = true
    actual val IS_NATIVE: Boolean = false
}
