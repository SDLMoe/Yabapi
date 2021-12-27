// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.api.InfoApi.getCoinInfo
import sdl.moe.yabapi.api.InfoApi.getStat
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel

internal class InfoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    @Test
    suspend fun getBasicInfoTest() {
        // client.loginWebQRCodeInteractive()
        client.getBasicInfo()
    }

    @Test
    suspend fun getStatTest() {
        client.getStat()
    }
    
    @Test
    suspend fun getCoinTest() {
        client.getCoinInfo()
    }
}

suspend fun main() = InfoApiTest().getCoinTest()
