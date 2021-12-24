// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineDispatcher

internal interface IPlatform {
    val ioDispatcher: CoroutineDispatcher
}

internal expect object Platform: IPlatform
