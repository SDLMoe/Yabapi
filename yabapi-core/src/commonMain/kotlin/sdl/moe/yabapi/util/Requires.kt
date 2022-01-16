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
