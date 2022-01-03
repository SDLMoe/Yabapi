// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import com.soywiz.korio.async.runBlockingNoJs
import com.soywiz.korio.file.std.localVfs
import io.ktor.http.Cookie
import io.ktor.http.Url
import java.io.File
import kotlin.test.Test

internal class FileCookieStorageTest {
    @Test
    fun test() = runBlockingNoJs {
        val storage = FileCookieStorage(localVfs(File("test.txt")))
        storage.addCookie(Url("https://example.com"), Cookie("test", "test"))
        storage.get(Url("https://example.com"))
        storage.close()
    }

    @Test
    fun test2() = runBlockingNoJs {
        val storage = FileCookieStorage(localVfs(File("test1.txt")))
        for (i in 0..100) {
            storage.addCookie(Url("https://example.com"), Cookie("test$i", "test$i"))
        }
        storage.get(Url("https://example.com"))
        storage.close()
    }

    @Test
    fun test3() = runBlockingNoJs {
        val storage = FileCookieStorage(localVfs(File("test1.txt")))
        for (i in 0..100) {
            storage.addCookie(Url("https://example.com"), Cookie("test$i", "test$i"))
        }
        storage.get(Url("https://example.com"))
        storage.close()
        for (i in 101..200) {
            storage.addCookie(Url("https://example.com"), Cookie("test$i", "test$i"))
        }
    }
}
