// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.http.Cookie
import io.ktor.http.ParametersBuilder
import io.ktor.http.Url
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.api.BiliApi
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.consts.MAIN
import sdl.moe.yabapi.consts.getDefaultHttpClient
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.logger
import kotlin.collections.set

/**
 * API入口
 *
 * 所有可用 Api 请参见 [BiliApi] 的子类
 *
 * @param client [HttpClient] Ktor 的實現, 根據平台選擇預設值 [getDefaultHttpClient]
 * @param cookieStorage 默认 [AcceptAllCookiesStorage] 可用 [FileCookieStorage]
 * @see CookiesStorage
 */
public class BiliClient(
    public var client: HttpClient = getDefaultHttpClient(),
    private val cookieStorage: CookiesStorage = AcceptAllCookiesStorage(),
) {
    init {
        client = client.config {
            install(HttpCookies) {
                storage = cookieStorage
            }
        }
    }

    public companion object {
        /** API 列表 */
        public val apiList: HashMap<String, BiliApi> = hashMapOf()

        /**
         * 把 API 註冊到列表
         *
         * 一般在 [BiliApi] 實例的 init 函數中調用
         */
        @Suppress("SENSELESS_COMPARISON")
        internal fun registerApi(api: BiliApi) {
            require(api.apiName != null) { "Invoke registerApi before apiName property initialized." }
            logger.debug { "Registering ${api.apiName} api.." }
            this.apiList[api.apiName] = api
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

    public suspend fun addCookie(vararg cookie: Cookie): Unit = withContext(Platform.ioDispatcher) {
        cookie.forEach {
            cookieStorage.addCookie(Url(MAIN), it)
        }
    }
}
