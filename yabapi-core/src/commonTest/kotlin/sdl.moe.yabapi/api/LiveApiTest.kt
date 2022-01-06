// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.api.LiveApi.getRoomInitInfo
import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class LiveApiTest {
    init {
        initTest()
    }

    @Test
    fun getRoomInitInfoTest() {
        runTest {
            client.getRoomInitInfo(213)
        }
    }
}
