package moe.sdl.yabapi.api

import moe.sdl.yabapi.BiliClient
import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class PassportApiTest {

    init {
        initTest()
    }

    @Test
    fun loginWebQRCodeInteractive() {
        runTest {
            BiliClient().loginWebQRCodeInteractive()
        }
    }

    @Test
    fun getCallingCodeTest(): Unit = runTest {
        client.getCallingCode()
    }
}
