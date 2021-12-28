// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getAccountInfo
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.api.InfoApi.getCoinExp
import sdl.moe.yabapi.api.InfoApi.getCoinInfo
import sdl.moe.yabapi.api.InfoApi.getExpReward
import sdl.moe.yabapi.api.InfoApi.getSecureInfo
import sdl.moe.yabapi.api.InfoApi.getStat
import sdl.moe.yabapi.api.InfoApi.getVipStat
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.logger
import sdl.moe.yabapi.util.yabapiLogLevel

internal class InfoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

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

    @Test
    suspend fun getAccountInfoTest() {
        client.getAccountInfo()
    }

    @Test
    suspend fun getExpTest() {
        val expData = client.getExpReward().data
        val coinExpData = client.getCoinExp()
        expData.replaceWithCoinExp(coinExpData).countTodayRewarded().also {
            logger.info { "Sum: $it" }
        }
    }

    @Test
    suspend fun getVipStatTest() {
        client.getVipStat()
    }

    @Test
    suspend fun getSecureInfoTest() {
        client.getSecureInfo()
    }
}

suspend fun main() = InfoApiTest().getSecureInfoTest()
