// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import com.soywiz.korio.async.launch
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock.System
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test
import sdl.moe.yabapi.config.onRawCommandResponse
import sdl.moe.yabapi.consts.json
import sdl.moe.yabapi.initTest
import java.io.File

internal class LiveApiJvmTest {
    init {
        initTest()
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun captureCommands() {
        runBlocking {
            val roomId = listOf(5050, 271744, 1017, 213, 510, 22384516, 8792912, 23625060, 55, 734, 3044248, 33989)
            roomId.map { id ->
                launch(newSingleThreadContext("capture-thread-$id")) {
                    LiveApiTest().createConnection(id) {
                        onRawCommandResponse { command ->
                            command.collect {
                                val encoded = json.encodeToString(it)
                                withContext(Dispatchers.IO) {
                                    val file = File("~/tmp/commands/${it.operation}/${
                                        System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toJavaLocalDateTime().toLocalDate()
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
}

fun main() = LiveApiJvmTest().captureCommands()
