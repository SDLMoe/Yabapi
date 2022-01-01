// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data

import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import io.ktor.util.date.GMTDate
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.GMTDateSerializer

@Serializable
public data class CookieWrapper(
    val name: String,
    val value: String,
    val encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
    val maxAge: Int = 0,
    @Serializable(with = GMTDateSerializer::class)
    val expires: GMTDate? = null,
    val domain: String? = null,
    val path: String? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false,
    val extensions: Map<String, String?> = emptyMap(),
) {
    public companion object {
        public fun fromCookie(cookie: Cookie): CookieWrapper =
            CookieWrapper(
                cookie.name,
                cookie.value,
                cookie.encoding,
                cookie.maxAge,
                cookie.expires,
                cookie.domain,
                cookie.path,
                cookie.secure,
                cookie.httpOnly,
                cookie.extensions
            )

        public fun fromCookies(cookies: List<Cookie>): List<CookieWrapper> =
            cookies.fold(mutableListOf()) { acc, c ->
                acc.add(fromCookie(c))
                acc
            }
    }

    public fun toCookie(): Cookie =
        Cookie(name, value, encoding, maxAge, expires, domain, path, secure, httpOnly, extensions)
}

public fun Collection<CookieWrapper>.toCookies(): List<Cookie> =
    this.fold(mutableListOf()) { acc, c ->
        acc.add(c.toCookie())
        acc
    }
