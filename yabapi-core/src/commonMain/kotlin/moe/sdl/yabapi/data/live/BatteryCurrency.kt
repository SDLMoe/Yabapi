package moe.sdl.yabapi.data.live

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class BatteryCurrency(
    public val value: Int,
) {
    public val cny: Double
        get() = (value.toDouble() / 1000.0)
}
