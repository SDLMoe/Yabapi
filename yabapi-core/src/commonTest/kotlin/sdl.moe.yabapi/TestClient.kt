@file:JvmName("TestClientJvm")

package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineScope
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.jvm.JvmName
import kotlin.test.Test

internal const val TEST_COOKIE_PATH = "./cookies.json"

internal expect val client: BiliClient

expect fun <T> runTest(block: suspend CoroutineScope.() -> T)

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
