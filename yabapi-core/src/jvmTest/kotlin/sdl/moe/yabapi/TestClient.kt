package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.storage.FileCookieStorage

internal actual val client: BiliClient = BiliClient(cookieStorage = FileCookieStorage(TEST_COOKIE_PATH))

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T) {
    runBlocking { block() }
}
