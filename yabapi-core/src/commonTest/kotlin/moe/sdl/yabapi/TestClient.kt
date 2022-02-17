@file:JvmName("TestClientJvm")

package moe.sdl.yabapi

import kotlinx.coroutines.CoroutineScope
import moe.sdl.yabapi.Yabapi.yabapiLogLevel
import moe.sdl.yabapi.enums.LogLevel.DEBUG
import kotlin.jvm.JvmName
import kotlin.test.Test

internal const val TEST_COOKIE_PATH = "./cookies.json"

internal expect val client: BiliClient

expect fun <T> runTest(block: suspend CoroutineScope.() -> T): T

internal fun initTest() {
    yabapiLogLevel.getAndSet(DEBUG)
}

internal class ClientTest {
    @Test
    fun cookieStoragePathTest() {
        initTest()
        runTest {
            // client.addCookie(Cookie("testKey", "1919810"))
        }
    }
}
