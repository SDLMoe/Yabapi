package moe.sdl.yabapi.util.reflect

internal actual val Any.qualifiedOrSimpleName: String
    get() = this::class.qualifiedName ?: "null"
