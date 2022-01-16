package sdl.moe.yabapi.api

import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.client
import sdl.moe.yabapi.connect.LiveDanmakuConnectConfig
import sdl.moe.yabapi.connect.onCommandResponse
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class LiveApiTest {
    init {
        initTest()
    }

    suspend fun createConnection(roomId: Int, config: LiveDanmakuConnectConfig.() -> Unit = {}) {
        val realId = client.getRoomInitInfo(roomId).data?.roomId ?: error("Get init info failed")
        val danmakuInfoData = client.getLiveDanmakuInfo(realId).data ?: error("Get live server failed")
        val loginUserMid = client.getBasicInfo().data.mid ?: error("Not login")
        client.createLiveDanmakuConnection(loginUserMid,
            realId,
            danmakuInfoData.token,
            danmakuInfoData.hostList[0],
            Platform.ioDispatcher,
            config
        )
    }

    @Test
    fun getRoomInitInfoTest() {
        runTest {
            client.getRoomInitInfo(213)
        }
    }

    @Test
    fun connectTest() {
        runTest {
            val roomId = 4788550
            createConnection(roomId)
        }
    }

    @Test
    fun serializeTest() {
        runTest {
            val roomId = 7777
            createConnection(roomId) {
                onCommandResponse { //flow ->
                    // flow.collect {}
                }
            }
        }
    }

    @Test
    fun fetchStream() {
        runTest {
            client.fetchLiveStream(22495291)
                .data?.playUrlInfo?.playUrl?.stream
                ?.firstOrNull { it.protocolName == "http_stream" }
                ?.format?.firstOrNull { it.formatName == "flv" }
                ?.codec?.firstOrNull { it.codecName == "avc" }?.playUrl.also {
                    println(it)
                }
        }
    }
}
