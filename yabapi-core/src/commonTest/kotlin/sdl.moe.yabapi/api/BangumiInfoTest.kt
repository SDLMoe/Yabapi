package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class BangumiApiTest {
    init {
        initTest()
    }

    @Test
    fun getBanugmiInfoTest() {
        runTest {
            client.getBangumiInfo(28220978)
        }
    }

    @Test
    fun getBangumiDetailed() {
        runTest {
            listOf(40257, 40028, 39462).forEach {
                client.getBangumiDetailedBySeason(it)
            }
            listOf(450012).forEach {
                client.getBangumiDetailedByEp(it)
            }
        }
    }
}
