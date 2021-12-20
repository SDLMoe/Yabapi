// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import io.ktor.http.Cookie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.system.measureTimeMillis

internal class FileCookieStorageTest {

    @Test
    fun reset() {
        runBlocking {
            val storage = FileCookieStorage(File(".", "/tmp/test.json"))
            launch {
                repeat(10) { storage.addCookie(Url("https://www.google.com"), Cookie("test", "test")) }
                delay(1000)
            }
            launch {
                repeat(10) {
                    storage.reset()
                }
                delay(950)
            }
        }
    }

    @Test
    fun costTest() {
        runBlocking {
            measureTimeMillis {
                launch(Dispatchers.IO) {
                    for (i in 1..50) {
                        launch(Dispatchers.IO) {
                            measureTimeMillis {
                                val storage = FileCookieStorage(File("./tmp/test$i.json"))
                                for (j in 1..1000) {
                                    storage.addCookie(Url("https://www.google.com"), Cookie("test-$j", "test$j"))
                                }
                                storage.close()
                            }.also {
                                println("One Thread: $it ms")
                            }
                        }
                    }
                }.join()
            }.also {
                println("Total: $it ms")
            }
        }
    }
}
