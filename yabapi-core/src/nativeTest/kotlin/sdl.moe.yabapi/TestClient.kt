// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi

import com.soywiz.korio.file.std.localVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.storage.FileCookieStorage
import kotlin.test.Test

internal actual val client: BiliClient = BiliClient(cookieStorage = FileCookieStorage(localVfs(TEST_COOKIE_PATH)))

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T) {
    runBlocking { block() }
}

internal class Test() {
    @Test
    fun test() {
        println(client)
    }
}
