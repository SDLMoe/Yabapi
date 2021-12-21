package sdl.moe.yabapi.api

import kotlinx.coroutines.delay
import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.PassportApi.logOut
import sdl.moe.yabapi.api.PassportApi.loginWebQRCodeInteractive
import sdl.moe.yabapi.api.PassportApi.passportApi

class PassportApiTest {
    @Test
    fun loginQRCodeInteractive() {
        val client = BiliClient()
        client.passportApi.loginWebQRCodeInteractive(client)
    }
}

suspend fun main() {
    val client = BiliClient()
    client.loginWebQRCodeInteractive()
    delay(10_000) // wait 10s for login
    client.logOut()
}
