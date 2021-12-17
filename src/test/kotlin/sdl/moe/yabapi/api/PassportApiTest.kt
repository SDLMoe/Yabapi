package sdl.moe.yabapi.api

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.PassportApi.passport

val client: BiliClient = BiliClient()

class PassportApiTest {
    @Test
    fun loginQRCodeInteractive() {
        client.passport.loginWebQRCodeInteractive(client)
    }
}
