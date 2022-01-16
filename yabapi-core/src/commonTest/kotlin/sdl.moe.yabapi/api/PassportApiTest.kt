package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.util.requireCmdInputString
import sdl.moe.yabapi.util.yabapiLogLevel

class PassportApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    suspend fun loginPwd() {
        client.loginWebConsole()
    }

    suspend fun loginCookie() {
        val cookies = requireCmdInputString("Please Input Cookies")
        client.loginCookie(cookies)
    }

    suspend fun loginWebQRCodeInteractive() {
        client.loginWebQRCodeInteractive()
    }
}
