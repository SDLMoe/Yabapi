package sdl.moe.yabapi.util.reflect

internal actual val Any.qualifiedOrSimpleName: String
    get() = Any::class.qualifiedName ?: "null"
