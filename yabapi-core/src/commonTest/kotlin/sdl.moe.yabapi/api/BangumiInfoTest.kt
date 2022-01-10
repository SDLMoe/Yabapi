// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

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
