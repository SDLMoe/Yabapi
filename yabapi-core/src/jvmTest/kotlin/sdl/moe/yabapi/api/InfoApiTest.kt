// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getAccountInfo
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.api.InfoApi.getCoinExp
import sdl.moe.yabapi.api.InfoApi.getCoinInfo
import sdl.moe.yabapi.api.InfoApi.getCoinLog
import sdl.moe.yabapi.api.InfoApi.getExpReward
import sdl.moe.yabapi.api.InfoApi.getMySpace
import sdl.moe.yabapi.api.InfoApi.getRealNameDetailed
import sdl.moe.yabapi.api.InfoApi.getRealNameInfo
import sdl.moe.yabapi.api.InfoApi.getSecureInfo
import sdl.moe.yabapi.api.InfoApi.getStat
import sdl.moe.yabapi.api.InfoApi.getUserCard
import sdl.moe.yabapi.api.InfoApi.getUserSpace
import sdl.moe.yabapi.api.InfoApi.getVipStat
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.logger
import sdl.moe.yabapi.util.yabapiLogLevel

internal object InfoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    fun getBasicInfoTest() {
        runBlocking {
            // client.loginWebQRCodeInteractive()
            client.getBasicInfo()
        }
    }

    fun getStatTest() {
        runBlocking {
            client.getStat()
        }
    }

    fun getCoinTest() {
        runBlocking {
            client.getCoinInfo()
        }
    }

    fun getAccountInfoTest() {
        runBlocking {
            client.getAccountInfo()
        }
    }

    fun getExpTest() {
        runBlocking {
            val expData = client.getExpReward().data
            val coinExpData = client.getCoinExp()
            expData?.replaceWithCoinExp(coinExpData)?.countTodayRewarded().also {
                logger.info { "Sum: $it" }
            }
        }
    }

    fun getVipStatTest() {
        runBlocking {
            client.getVipStat()
        }
    }

    fun getSecureInfoTest() {
        runBlocking {
            client.getSecureInfo()
        }
    }

    fun getRealNameInfoTest() {
        runBlocking {
            client.getRealNameInfo()
        }
    }

    fun getRealNameDetailedTest() {
        runBlocking {
            client.getRealNameDetailed()
        }
    }

    fun getCoinLogTest() {
        runBlocking {
            client.getCoinLog()
        }
    }

    fun getUserSpaceTest() {
        runBlocking {
            listOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
                .forEach {
                    client.getUserSpace(it)
                }
        }
    }

    fun getUserCardTest() {
        runBlocking {
            val random = listOf(true, false)
            // val body: String = client.client.get(USER_CARD_GET_URL) {
            //     parameter("mid", "2")
            //     parameter("photo", "true")
            // }
            // println(body)
            listOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
                .forEach {
                    client.getUserCard(it, random.random())
                }
        }
    }

    fun getMySpaceTest() {
        runBlocking {
            client.getMySpace()
        }
    }
}

fun main() {
    fun testAll() {
        InfoApiTest.getBasicInfoTest()
        InfoApiTest.getStatTest()
        InfoApiTest.getCoinTest()
        InfoApiTest.getAccountInfoTest()
        InfoApiTest.getExpTest()
        InfoApiTest.getVipStatTest()
        InfoApiTest.getSecureInfoTest()
        InfoApiTest.getRealNameInfoTest()
        InfoApiTest.getRealNameDetailedTest()
        InfoApiTest.getCoinLogTest()
        InfoApiTest.getUserSpaceTest()
        InfoApiTest.getUserCardTest()
        InfoApiTest.getMySpaceTest()
    }
    testAll()
}
