// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import com.soywiz.korio.async.launch
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import org.junit.jupiter.api.Test
import sdl.moe.yabapi.api.InfoApi.getBasicInfo
import sdl.moe.yabapi.api.LiveApi.createLiveDanmakuConnection
import sdl.moe.yabapi.api.LiveApi.getLiveDanmakuInfo
import sdl.moe.yabapi.api.LiveApi.getRoomInitInfo
import sdl.moe.yabapi.client
import sdl.moe.yabapi.config.onCommandResponse
import sdl.moe.yabapi.consts.json
import sdl.moe.yabapi.data.live.commands.LiveCommand
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
            val roomId = listOf(7777, 876396, 1, 21919321, 23919384)
            val tmpName = atomic(0L)
            roomId.map { id ->
                launch(newSingleThreadContext("capture-thread-$id")) {
                    val realId = client.getRoomInitInfo(id).data?.roomId ?: error("Get init info failed")
                    val danmakuInfoData = client.getLiveDanmakuInfo(realId).data ?: error("Get live server failed")
                    val loginUserMid = client.getBasicInfo().data.mid ?: error("Not login")
                    client.createLiveDanmakuConnection(loginUserMid,
                        realId,
                        danmakuInfoData.token,
                        danmakuInfoData.hostList[0]) {
                        onCommandResponse { command: Flow<LiveCommand> ->
                            command.collect {
                                val encoded = json.encodeToString(it)
                                withContext(Dispatchers.IO) {
                                    val file = File("./tmp/commands-2/${it.operation}-${tmpName.value}.json")
                                    file.parentFile.mkdirs()
                                    file.createNewFile()
                                    file.writeText(encoded)
                                    tmpName.getAndIncrement()
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
