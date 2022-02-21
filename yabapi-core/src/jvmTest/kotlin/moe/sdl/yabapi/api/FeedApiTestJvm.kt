package moe.sdl.yabapi.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import moe.sdl.yabapi.client
import moe.sdl.yabapi.data.GeneralCode.SUCCESS
import moe.sdl.yabapi.data.feed.NewFeedResponse
import moe.sdl.yabapi.runTest
import java.io.File

internal class FeedApiTestJvm {
    @Test
    fun storageNewFeedToFile(): Unit = runTest {
        client.apply {
            val mid = getBasicInfo().data.mid!!
            val list = intArrayOf(
                1,
                2,
                4,
                8,
                16,
                32,
                64,
                256,
                512,
                1024,
                2048,
                2049,
                4097,
                4098,
                4099,
                4100,
                4101,
                4200,
                4201,
                4300,
                4302,
                4303,
                4208,
                4310,
                4311,
                1000,
                1001
            )
            list.asFlow()
                .onEach { type ->
                    val file = File("./tmp/feeds/feed-type-$type.json")
                    file.parentFile?.mkdirs()
                    if (!file.exists()) file.createNewFile()
                    println("Downloading feed type $type")
                    suspend fun tryToGet(): NewFeedResponse {
                        val feed = getNewFeed(mid, intArrayOf(type))
                        return if (feed.code != SUCCESS) {
                            delay(5000L)
                            println("Retry to get feed type $type")
                            tryToGet()
                        } else feed
                    }
                    tryToGet().let {
                        file.writeText(Json.encodeToString(it))
                    }
                    delay(1000L)
                }.flowOn(Dispatchers.IO)
                .collect()
        }
    }
}
