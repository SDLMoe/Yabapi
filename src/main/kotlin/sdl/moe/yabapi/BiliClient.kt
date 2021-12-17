// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.http.Cookie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import sdl.moe.yabapi.api.BiliApi
import sdl.moe.yabapi.consts.DefaultHttpClient
import sdl.moe.yabapi.consts.MAIN
import kotlin.collections.set

private val logger = KotlinLogging.logger {}

/**
 * API入口
 * @param client [HttpClient] Ktor 的實現, 預設為 [DefaultHttpClient]
 * @param cookieStorage [Cookie] 存儲器, 預設為 [AcceptAllCookiesStorage]
 */
public class BiliClient(
    public val client: HttpClient = DefaultHttpClient,
    private val cookieStorage: AcceptAllCookiesStorage = AcceptAllCookiesStorage()
) {
    init {
        client.config {
            install(HttpCookies) {
                storage = cookieStorage
            }
        }
    }

    public companion object {
        /** API 列表 */
        public val api: HashMap<String, BiliApi> = hashMapOf()

        /**
         * 把 API 註冊到列表
         *
         * 一般在 [BiliApi] 實例的 init 函數中調用
         */
        internal fun registerApi(name: String, api: BiliApi) {
            logger.debug { "Registering $name api.." }
            this.api[name] = api
        }
    }

    public var isLogin: Boolean = false
        internal set(value) {
            field = value
            logger.debug { "isLogin status changed to $value" }
        }

    public suspend fun addCookie(vararg cookie: Cookie): Unit = withContext(Dispatchers.IO) {
        cookie.forEach {
            cookieStorage.addCookie(Url(MAIN), it)
        }
    }
}
