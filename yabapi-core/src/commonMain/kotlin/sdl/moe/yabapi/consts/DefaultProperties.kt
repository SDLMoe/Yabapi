// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:JvmName("DefaultPropertiesJvm")

package sdl.moe.yabapi.consts

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.UserAgent
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import kotlin.jvm.JvmName

public expect fun getDefaultEngine(): HttpClientEngineFactory<*>

public fun getDefaultHttpClient(): HttpClient = HttpClient(getDefaultEngine()) {
    install(WebSockets)
    install(UserAgent) {
        agent = WEB_USER_AGENT
    }
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
    defaultRequest {
        header(HttpHeaders.Accept, "*/*")
    }
}

internal val json = kotlinx.serialization.json.Json {
    prettyPrint = true
    isLenient = true
    coerceInputValues = true
}

// Safari + MacOS User Agent
internal const val WEB_USER_AGENT: String =
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 12_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Safari/605.1.15"
