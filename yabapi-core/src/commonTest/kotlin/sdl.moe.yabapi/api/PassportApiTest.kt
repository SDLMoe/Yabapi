// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.api.PassportApi.loginCookie
import sdl.moe.yabapi.api.PassportApi.loginWebConsole
import sdl.moe.yabapi.api.PassportApi.loginWebQRCodeInteractive
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
