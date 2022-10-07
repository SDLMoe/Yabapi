package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class BangumiApiTest {
    init {
        initTest()
    }

    @Test
    fun getBanugmiInfoTest(): Unit = runTest {
        client.getBangumiInfo(28220978)
    }

    @Test
    fun getBangumiReviewInfoTest(): Unit = runTest {
        client.getBangumiReviewInfo(28234837)
    }

    @Test
    fun getBangumiDetailed() {
        runTest {
            // listOf(40257, 40028, 39462).forEach {
            //     client.getBangumiDetailedBySeason(it)
            // }
            longArrayOf(450012).forEach {
                client.getBangumiDetailedByEp(it)
            }
        }
    }
}
