// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:Suppress("MaxLineLength")

package sdl.moe.yabapi.consts

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.UserAgent
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders

public val DefaultHttpClient: HttpClient by lazy {
    HttpClient(CIO) {
        install(WebSockets)
        install(UserAgent) {
            agent = WEB_USER_AGENT
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            }
            serializer = KotlinxSerializer(json)
        }
        defaultRequest {
            header(HttpHeaders.Accept, "*/*")
        }
    }
}

// Safari + MacOS User Agent 主要为了获取 HEVC
internal const val WEB_USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Safari/605.1.15"

internal const val APP_KEY = "1d8b6e7d45233436"
internal const val APP_SIGN = "560c52ccd288fed045859ed18bffd973"
