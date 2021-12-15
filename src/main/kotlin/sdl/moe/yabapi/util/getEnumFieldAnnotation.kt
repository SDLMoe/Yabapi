package sdl.moe.yabapi.util

inline fun <reified A : Annotation> Enum<*>.getEnumFieldAnnotation(): A? =
    javaClass.getDeclaredField(name).getAnnotation(A::class.java)
