package moe.sdl.yabapi.consts

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

public expect fun getDefaultEngine(): HttpClientEngineFactory<*>

/**
 * 根据平台选择默认的 HttpClient
 * 目前仅有 Engine 不同
 * @see getDefaultEngine
 */
public fun getDefaultHttpClient(
    cookiesStorage: CookiesStorage = AcceptAllCookiesStorage(),
): HttpClient =
    HttpClient(getDefaultEngine()) {
        install(WebSockets) {
            this.pingInterval = 500
        }
        install(UserAgent) {
            agent = WEB_USER_AGENT
        }
        install(ContentEncoding) {
            gzip()
            deflate()
            identity()
        }
        install(HttpCookies) {
            storage = cookiesStorage
        }
        defaultRequest {
            header(HttpHeaders.Accept, "*/*")
            header(HttpHeaders.AcceptCharset, "UTF-8")
        }
    }

// Safari + MacOS User Agent
internal const val WEB_USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_2) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.2 Safari/605.1.15"
