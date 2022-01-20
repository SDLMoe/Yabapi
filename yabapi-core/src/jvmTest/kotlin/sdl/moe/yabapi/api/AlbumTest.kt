package sdl.moe.yabapi.api

import io.ktor.utils.io.streams.asInput
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
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
