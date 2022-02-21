package moe.sdl.yabapi.api

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock.System
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.connect.onRawCommandResponse
import moe.sdl.yabapi.initTest
import org.junit.jupiter.api.Test
import java.io.File

internal class LiveApiJvmTest {
    init {
        initTest()
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun captureCommands(): Unit = runBlocking {
        val roomId = listOf(5050, 271744, 1017, 213, 510, 22384516, 8792912, 23625060, 55, 734, 3044248, 33989)
        roomId.map { id ->
            launch(newSingleThreadContext("capture-thread-$id")) {
                LiveApiTest().createConnection(id) {
                    onRawCommandResponse { command ->
                        command.collect {
                            val encoded = Json.encodeToString(it)
                            withContext(Dispatchers.IO) {
                                val file = File("./tmp/commands/${it.operation}/${
                                    System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime()
                                        .toLocalDate()
                                }/${System.now()}-${System.now().nanosecondsOfSecond}.json")
                                file.parentFile.mkdirs()
                                file.createNewFile()
                                file.writeText(encoded)
                            }
                        }
                    }
                }
            }
        }.joinAll()
    }
}

fun main() = LiveApiJvmTest().captureCommands()
