@file:Suppress("MaxLineLength")

package sdl.moe.yabapi.consts

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.websocket.WebSockets

internal val DefaultHttpClient: HttpClient by lazy {
    HttpClient(CIO) {
        install(WebSockets)
        install(UserAgent) {
            agent = WEB_USER_AGENT
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
}

// Safari + MacOS User Agent 主要为了获取 HEVC
internal const val WEB_USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Safari/605.1.15"
