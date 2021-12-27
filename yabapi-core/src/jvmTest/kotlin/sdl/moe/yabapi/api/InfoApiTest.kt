// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel

internal class InfoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    @Test
    suspend fun getBasicInfoTest() {
        val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))
        // client.loginWebQRCodeInteractive()
        client.getBasicInfo()
    }
}

suspend fun main() = InfoApiTest().getBasicInfoTest()
