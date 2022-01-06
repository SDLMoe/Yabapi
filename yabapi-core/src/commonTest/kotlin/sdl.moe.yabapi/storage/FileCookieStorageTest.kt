// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import io.ktor.http.Cookie
import io.ktor.http.Url
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class FileCookieStorageTest(private val storage: FileCookieStorage) {
    @Test
    fun test() = runTest {
        storage.addCookie(Url("https://example.com"), Cookie("test", "test"))
        storage.get(Url("https://example.com"))
        storage.close()
    }

    fun test2() = runTest {
        for (i in 0..100) {
            storage.addCookie(Url("https://example.com"), Cookie("test$i", "test$i"))
        }
        storage.get(Url("https://example.com"))
        storage.close()
    }

    fun test3() = runTest {
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
