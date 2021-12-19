// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data

import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding.RAW
import io.ktor.util.date.GMTDate
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test

internal class CookieWrapperTest {
    private val cookie = Cookie(
        "name test",
        "value test",
        RAW,
        114514,
        GMTDate(114514),
        "test.com",
        "/get",
        true,
        false,
    )
    @Test
    fun fromCookie() {
        val cookieWrapper = CookieWrapper.fromCookie(cookie)
        val newCookie = cookieWrapper.toCookie()
        assert(newCookie == cookie)
        val encoded = Json.encodeToString(CookieWrapper.serializer(), cookieWrapper)
        val decoded = Json.decodeFromString(CookieWrapper.serializer(), encoded)
        assert(decoded == cookieWrapper)
        assert(cookieWrapper.toCookie() == newCookie)
    }
}
