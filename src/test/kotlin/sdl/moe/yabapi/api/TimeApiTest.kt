package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.TimeApi.getServerUTC
import sdl.moe.yabapi.api.TimeApi.getTimestamp

private val client = BiliClient()

class TimeApiTest {
    @Test
    fun getNowTime(): Unit = runBlocking {
        client.getTimestamp()
    }

    @Test
    fun getUTC(): Unit = runBlocking {
        client.getServerUTC()
    }
}