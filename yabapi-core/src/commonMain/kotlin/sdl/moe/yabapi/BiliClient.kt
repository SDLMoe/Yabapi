// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.Cookie
import io.ktor.http.ParametersBuilder
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.api.getBasicInfo
import sdl.moe.yabapi.consts.defaultJsonParser
import sdl.moe.yabapi.consts.getDefaultHttpClient
import sdl.moe.yabapi.consts.internal.MAIN
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext

private val logger = Logger("BiliClient")

/**
 * API入口
 *
 * 所有可用 Api 请参见 [sdl.moe.yabapi.api]
 *
 * @param client [HttpClient] Ktor 的實現, 根據平台選擇預設值 [getDefaultHttpClient]
 * @param cookieStorage 默认 [AcceptAllCookiesStorage] 可用 [FileCookieStorage]
 * @see CookiesStorage
 */
public class BiliClient(
    public val context: CoroutineContext = Platform.ioDispatcher + CoroutineName("yabapi"),
    public var client: HttpClient = getDefaultHttpClient(),
    private val cookieStorage: CookiesStorage = AcceptAllCookiesStorage(),
    public val json: Json = defaultJsonParser,
) {
    init {
        client = client.config {
            install(HttpCookies) {
                storage = cookieStorage
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }
    }

    public suspend fun getBiliCookies(): List<Cookie> {
        return cookieStorage.get(Url("https://.bilibili.com"))
    }

    public suspend fun getCsrfToken(): Cookie? = getBiliCookies().firstOrNull { it.name == "bili_jct" }

    internal suspend fun ParametersBuilder.putCsrf(key: String = "csrf") {
        val csrf = getCsrfToken()?.value
        requireNotNull(csrf)
        append(key, csrf)
    }

    public suspend fun isLogin(): Boolean = getBasicInfo().data.isLogin

    public suspend fun needLogin() {
        if (!isLogin()) throw IllegalStateException("You need login first!")
    }

    public suspend fun noNeedLogin() {
        if (isLogin()) throw IllegalStateException("You are already logged in!")
    }

    public suspend fun addCookie(vararg cookie: Cookie): Unit = withContext(context) {
        cookie.forEach {
            cookieStorage.addCookie(Url(MAIN), it)
        }
    }
}
