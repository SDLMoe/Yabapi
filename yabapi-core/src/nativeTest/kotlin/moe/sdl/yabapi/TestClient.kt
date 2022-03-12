package moe.sdl.yabapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import moe.sdl.yabapi.api.getStat
import moe.sdl.yabapi.consts.getDefaultHttpClient
import moe.sdl.yabapi.storage.FileCookieStorage
import okio.FileSystem
import okio.Path.Companion.toPath
import kotlin.native.concurrent.SharedImmutable
import kotlin.test.Test

@SharedImmutable
internal actual val client: BiliClient by lazy {
    val httpClient = getDefaultHttpClient(
        FileCookieStorage(FileSystem.SYSTEM, TEST_COOKIE_PATH.toPath()) {
            saveInTime = true
        }
    )
    BiliClient(httpClient)
}

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T): T {
    return runBlocking { block() }
}

internal class Test {
    init {
        initTest()
    }

    @Test
    fun test(): Unit = runTest {
        // client.loginWebQRCodeInteractive()
        client.getStat()
    }
}
