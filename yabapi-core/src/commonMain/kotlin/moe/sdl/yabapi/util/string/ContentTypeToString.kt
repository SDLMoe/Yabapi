package moe.sdl.yabapi.util.string

import io.ktor.http.ContentType

internal fun ContentType.toHeaderValue() = "$contentType/$contentSubtype"
