// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:JvmName("TestClientJvm")

package sdl.moe.yabapi

import io.ktor.http.Cookie
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.jvm.JvmName
import kotlin.test.Test

internal const val TEST_COOKIE_PATH = "./cookies.json"

internal expect val client: BiliClient

expect fun <T> runTest(block: suspend () -> T)

internal fun initTest() {
    yabapiLogLevel = DEBUG
}

internal class ClientTest {
    @Test
    fun cookieStoragePathTest() {
        initTest()
        runTest {
            client.addCookie(Cookie("testKey", "1919810"))
        }
    }
}
