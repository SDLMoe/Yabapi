package sdl.moe.yabapi.util

import sdl.moe.yabapi.util.reflect.qualifiedOrSimpleName

@Suppress("NOTHING_TO_INLINE")
internal inline infix fun Boolean.xnor(other: Boolean) = !(this xor other)

internal fun requireLeastAndOnlyOne(
    a: Any?,
    b: Any?,
    message: String = "Parameter [${a?.qualifiedOrSimpleName}, ${b?.qualifiedOrSimpleName}] must be at least AND only one",
) {
    require(a != null || b != null) { message }
    require(!(a != null && b != null)) { message }
}

internal fun requireXnorNullable(
    a: Any?,
    b: Any?,
    message: String = "Parameter [${a?.qualifiedOrSimpleName}, ${b?.qualifiedOrSimpleName}] need both are null or both are not null (XNOR nullable)",
): Unit = require((a == null) xnor (b == null)) { message }
