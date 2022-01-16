// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

import sdl.moe.yabapi.util.reflect.qualifiedOrSimpleName

public fun requireLeastAndOnlyOne(
    a: Any?,
    b: Any?,
    message: String = "Parameter [${a?.qualifiedOrSimpleName}, ${b?.qualifiedOrSimpleName}] must be at least AND only one",
) {
    require(a != null || b != null) { message }
    require(!(a != null && b != null)) { message }
}
