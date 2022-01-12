// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util

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
