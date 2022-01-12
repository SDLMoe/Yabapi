// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.runTest
import sdl.moe.yabapi.util.KermitLogger
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = KermitLogger("InfoApiTest")

internal object InfoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    fun getBasicInfoTest() {
        runTest {
            // client.loginWebQRCodeInteractive()
            client.getBasicInfo()
        }
    }

    fun getStatTest() {
        runTest {
            client.getStat()
        }
    }

    fun getCoinTest() {
        runTest {
            client.getCoinInfo()
        }
    }

    fun getAccountInfoTest() {
        runTest {
            client.getAccountInfo()
        }
    }

    fun getExpTest() {
        runTest {
            val expData = client.getExpReward().data
            val coinExpData = client.getCoinExp()
            expData?.replaceWithCoinExp(coinExpData)?.countTodayRewarded().also {
                logger.info { "Sum: $it" }
            }
        }
    }

    fun getVipStatTest() {
        runTest {
            client.getVipStat()
        }
    }

    fun getSecureInfoTest() {
        runTest {
            client.getSecureInfo()
        }
    }

    fun getRealNameInfoTest() {
        runTest {
            client.getRealNameInfo()
        }
    }

    fun getRealNameDetailedTest() {
        runTest {
            client.getRealNameDetailed()
        }
    }

    fun getCoinLogTest() {
        runTest {
            client.getCoinLog()
        }
    }

    fun getUserSpaceTest() {
        runTest {
            listOf(2, 264155183, 699766742, 399308420, 1887658, 2648514, 2746732, 546195, 63231)
                .forEach {
                    client.getUserSpace(it)
                }
        }
    }

    fun getUserCardTest() {
        runTest {
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
        runTest {
            client.getMySpace()
        }
    }

    fun nickCheckTest() {
        runTest {
            // lol 星黛*露b*ot 是敏感詞
            listOf("//", "test11145141919810123123123", "星黛露bot", "0", "1", "哈哈哈哈哈", "儑厫爊").forEach {
                client.checkNick(it)
            }
        }
    }
}

fun main() {
    InfoApiTest.nickCheckTest()
}

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
    InfoApiTest.nickCheckTest()
}
