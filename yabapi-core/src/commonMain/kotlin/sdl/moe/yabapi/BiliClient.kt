package sdl.moe.yabapi

import io.ktor.client.HttpClient
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.client.features.cookies.cookies
import io.ktor.http.Cookie
import io.ktor.http.ParametersBuilder
import kotlinx.coroutines.CoroutineName
import sdl.moe.yabapi.api.getBasicInfo
import sdl.moe.yabapi.consts.getDefaultHttpClient
import kotlin.coroutines.CoroutineContext

/**
 * API入口
 *
 * 所有可用 Api 请参见 [sdl.moe.yabapi.api]
 *
 * @param client [HttpClient] Ktor 的實現, 通過 [getDefaultHttpClient] 獲取默認實現
 * @see CookiesStorage
 */
public class BiliClient(
    public var client: HttpClient,
    public val context: CoroutineContext = Platform.ioDispatcher + CoroutineName("yabapi"),
) {
    private suspend fun getBiliCookies(): List<Cookie> = client.cookies("https://.bilibili.com")

    private suspend fun getCsrfToken(): Cookie? = getBiliCookies().firstOrNull { it.name == "bili_jct" }

    internal suspend fun ParametersBuilder.putCsrf(key: String = "csrf") {
        val csrf = getCsrfToken()?.value
        requireNotNull(csrf)
        append(key, csrf)
    }

    public suspend fun isLogin(): Boolean = getBasicInfo().data.isLogin

    internal suspend fun needLogin() {
        if (!isLogin()) throw IllegalStateException("You need login first!")
    }

    internal suspend fun noNeedLogin() {
        if (isLogin()) throw IllegalStateException("You are already logged in!")
    }
}
