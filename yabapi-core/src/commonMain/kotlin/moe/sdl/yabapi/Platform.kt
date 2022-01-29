package moe.sdl.yabapi

import kotlinx.coroutines.CoroutineDispatcher

internal expect object Platform {
    val ioDispatcher: CoroutineDispatcher
    val IS_BROWSER: Boolean
    val IS_NODE: Boolean
    val IS_JVM: Boolean
    val IS_NATIVE: Boolean
}

internal fun Platform.isJs(): Boolean = IS_BROWSER || IS_NODE
