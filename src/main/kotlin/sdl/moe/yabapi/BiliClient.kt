package sdl.moe.yabapi

import io.ktor.client.HttpClient
import io.ktor.client.features.UserAgent
import io.ktor.client.features.websocket.WebSockets
import sdl.moe.yabapi.consts.WEB_USER_AGENT

class BiliClient(
    val client: HttpClient,
) {
    init {
        client.config {
            install(WebSockets)
            install(UserAgent) {
                agent = WEB_USER_AGENT
            }
        }
    }
}
