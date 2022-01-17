package sdl.moe.yabapi.util

import io.ktor.http.ContentType

internal fun ContentType.toHeaderValue() = "$contentType/$contentSubtype"
