package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class FeedApiTest {
    init {
        initTest()
    }
    @Test
    fun getFeedContentTest() = runTest {
        client.getFeedContent(619969539813998819uL)
    }
}
