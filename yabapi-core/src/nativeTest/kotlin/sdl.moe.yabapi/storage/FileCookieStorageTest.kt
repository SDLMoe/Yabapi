// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import com.soywiz.korio.async.runBlockingNoJs
import com.soywiz.korio.file.std.localVfs
import io.ktor.http.Cookie
import io.ktor.http.Url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.test.Test

class FileCookieStorageTest {
    @Test
    fun test2() = runBlockingNoJs {
        val vfs = localVfs("/Users/hbj/Downloads/native-test.txt")
        val storage = FileCookieStorage(vfs)
        CoroutineScope(Dispatchers.Default).launch {
            for (i in 0..100) {
                storage.addCookie(Url("https://example.com"), Cookie("test$i", "test$i"))
            }
        }
        CoroutineScope(Dispatchers.Default).launch {
            repeat(100) {
                storage.get(Url("https://example.com"))
            }
        }
        storage.get(Url("https://example.com"))
        storage.close()
    }
}
