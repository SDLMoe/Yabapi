package moe.sdl.yabapi.util

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Suppress("NOTHING_TO_INLINE")
internal inline fun now() = Clock.System.now()

@Suppress("NOTHING_TO_INLINE")
internal inline fun nowLocal() = now().toLocalDateTime(TimeZone.currentSystemDefault())

internal fun nowLocalString() = run {
    val now = now().toLocalDateTime(TimeZone.currentSystemDefault())
    "${now.date}T" + now.hour.toString().padStart(2, '0') + ":" +
        now.minute.toString().padStart(2, '0') + ":" +
        now.second.toString().padStart(2, '0')
}
