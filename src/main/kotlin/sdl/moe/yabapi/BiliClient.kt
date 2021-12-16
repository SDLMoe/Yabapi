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

public class BiliClient(
    public val client: HttpClient = DefaultHttpClient,
) {
    init {
        client.config {
            install(HttpCookies) {
                storage = cookieStorage
            }
        }
    }

    public companion object {
        public val api: HashMap<String, BiliApi> = hashMapOf()

        internal fun registerApi(name: String, api: BiliApi) {
            logger.debug { "Registering api $name.." }
            this.api[name] = api
        }
    }

    private val cookieStorage = AcceptAllCookiesStorage()

    public var isLogin: Boolean = false
        private set(value) {
            field = value
            logger.debug { "Login status changed to $value" }
        }

    public suspend fun addCookie(vararg cookie: Cookie): Unit = withContext(Dispatchers.IO) {
        cookie.forEach {
            cookieStorage.addCookie(Url(MAIN), it)
        }
    }
}
