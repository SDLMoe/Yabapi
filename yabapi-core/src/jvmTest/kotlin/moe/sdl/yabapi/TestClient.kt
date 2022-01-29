package moe.sdl.yabapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import moe.sdl.yabapi.consts.getDefaultHttpClient
import moe.sdl.yabapi.storage.FileCookieStorage

internal actual val client: BiliClient by lazy {
    val storage = FileCookieStorage(TEST_COOKIE_PATH)
    BiliClient(getDefaultHttpClient(storage))
}

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T) {
    runBlocking { block() }
}
