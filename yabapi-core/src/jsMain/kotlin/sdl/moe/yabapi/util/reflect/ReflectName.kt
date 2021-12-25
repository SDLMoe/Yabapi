// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.reflect

internal actual val Any.qualifiedOrSimpleName: String
    get() = Any::class.simpleName ?: "null"
