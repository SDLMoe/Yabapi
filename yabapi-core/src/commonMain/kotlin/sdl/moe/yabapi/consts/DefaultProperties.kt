package sdl.moe.yabapi.consts

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.UserAgent
import io.ktor.client.features.compression.ContentEncoding
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import kotlin.native.concurrent.SharedImmutable

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

@SharedImmutable
public val defaultJsonParser: Json = Json {
    prettyPrint = true
    isLenient = true
    coerceInputValues = true
}

@ExperimentalSerializationApi
@SharedImmutable
public val protoBuf: ProtoBuf = ProtoBuf

// Safari + MacOS User Agent
internal const val WEB_USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Safari/605.1.15"
