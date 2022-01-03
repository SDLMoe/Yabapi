// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.PassportApi.loginCookie
import sdl.moe.yabapi.api.PassportApi.loginWebConsole
import sdl.moe.yabapi.api.PassportApi.loginWebQRCodeInteractive
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.requireCmdInputString
import sdl.moe.yabapi.util.yabapiLogLevel

class PassportApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    suspend fun loginPwd() {
        val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))
        client.loginWebConsole()
    }

    suspend fun loginCookie() {
        val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))
        val cookies = requireCmdInputString("Please Input Cookies")
        client.loginCookie(cookies)
    }

    suspend fun loginWebQRCodeInteractive() {
        val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))
        client.loginWebQRCodeInteractive()
    }
}

suspend fun main() = PassportApiTest().loginWebQRCodeInteractive()
