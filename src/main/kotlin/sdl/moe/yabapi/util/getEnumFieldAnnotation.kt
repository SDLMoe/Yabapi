package sdl.moe.yabapi.util

internal inline fun <reified A : Annotation> Enum<*>.getEnumFieldAnnotation(): A? =
    javaClass.getDeclaredField(name).getAnnotation(A::class.java)
