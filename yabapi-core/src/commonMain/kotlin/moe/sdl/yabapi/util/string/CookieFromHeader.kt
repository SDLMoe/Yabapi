package moe.sdl.yabapi.util.string

import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import io.ktor.http.Url
import io.ktor.util.date.GMTDate
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import moe.sdl.yabapi.storage.fillDefaults

public fun cookieFromHeader(
    string: String,
    requestUrl: Url? = null,
    encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
    expires: GMTDate = GMTDate(Clock.System.now().plus(30 * 24, DateTimeUnit.HOUR).epochSeconds * 1_000),
): MutableList<Cookie> = string.replace(" ", "")
    .split(";")
    .fold(mutableListOf()) { acc, s ->
        val split2 = s.split("=")
        if (split2.size == 2) {
            var cookie = Cookie(split2[0], split2[1], encoding, expires = expires)
            requestUrl?.let { cookie = cookie.fillDefaults(it) }
            acc.add(cookie)
        }
        acc
    }
