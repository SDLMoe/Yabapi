package moe.sdl.yabapi.api

import io.ktor.utils.io.streams.asInput
import kotlinx.coroutines.runBlocking
import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import org.junit.jupiter.api.Test
import java.io.File

internal class AlbumTest {
    init {
        initTest()
    }

    @Test
    fun uploadTest(): Unit = runBlocking {
        client.uploadImage {
            File("/Users/hbj/Downloads/12_(6).jpeg").inputStream().asInput()
        }
    }
}
