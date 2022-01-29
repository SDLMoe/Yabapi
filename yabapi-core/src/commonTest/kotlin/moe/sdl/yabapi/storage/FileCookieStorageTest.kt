package moe.sdl.yabapi.storage

import io.ktor.http.Cookie
import io.ktor.http.Url
import moe.sdl.yabapi.runTest
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
