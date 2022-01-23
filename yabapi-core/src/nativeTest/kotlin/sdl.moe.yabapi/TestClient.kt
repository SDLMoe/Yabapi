package sdl.moe.yabapi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import okio.FileSystem
import okio.Path.Companion.toPath
import sdl.moe.yabapi.api.getStat
import sdl.moe.yabapi.consts.getDefaultHttpClient
import sdl.moe.yabapi.storage.FileCookieStorage
import kotlin.native.concurrent.SharedImmutable
import kotlin.test.Test

@SharedImmutable
internal actual val client: BiliClient by lazy {
    val httpClient = getDefaultHttpClient(FileCookieStorage(FileSystem.SYSTEM, TEST_COOKIE_PATH.toPath()) {
        saveInTime = true
    })
    BiliClient(httpClient)
}

actual inline fun <T> runTest(crossinline block: suspend CoroutineScope.() -> T) {
    runBlocking { block() }
}

internal class Test {
    init {
        initTest()
    }
    @Test
    fun test() = runTest {
        // client.loginWebQRCodeInteractive()
        client.getStat()
    }
}
