package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.consts.getDefaultHttpClient
import sdl.moe.yabapi.storage.FileCookieStorage

internal actual val client: BiliClient by lazy {
    val storage = FileCookieStorage(TEST_COOKIE_PATH)
    BiliClient(getDefaultHttpClient(storage))
}

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T) {
    runBlocking { block() }
}
