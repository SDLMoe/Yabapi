// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineDispatcher

internal expect object Platform {
    val ioDispatcher: CoroutineDispatcher
    val IS_BROWSER: Boolean
    val IS_NODE: Boolean
    val IS_JVM: Boolean
    val IS_NATIVE: Boolean
}

internal fun Platform.isJs(): Boolean = IS_BROWSER || IS_NODE
