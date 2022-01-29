package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class PassportApiTest {

    init {
        initTest()
    }

    suspend fun loginPwd() {
        client.loginWebConsole()
    }

    @Test
    fun loginWebQRCodeInteractive() {
        runTest {
            client.loginWebQRCodeInteractive()
        }
    }
}
