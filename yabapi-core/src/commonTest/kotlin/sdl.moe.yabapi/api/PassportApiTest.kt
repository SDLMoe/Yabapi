package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
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
