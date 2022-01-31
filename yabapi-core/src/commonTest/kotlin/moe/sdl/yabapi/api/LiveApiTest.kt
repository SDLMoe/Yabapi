package moe.sdl.yabapi.api

import moe.sdl.yabapi.Platform
import moe.sdl.yabapi.client
import moe.sdl.yabapi.connect.LiveDanmakuConnectConfig
import moe.sdl.yabapi.connect.onCommandResponse
import moe.sdl.yabapi.enums.live.LiveRankType
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class LiveApiTest {
    init {
        initTest()
    }

    @Test
    fun getLiveAreasTest() {
        runTest {
            client.getLiveAreas()
        }
    }

    @Test
    fun generateAreasCode() = runTest {
        val sb = StringBuilder()
        sb.appendLine("public sealed class LiveArea(val id: Int, val name: String) {")
        client.getLiveAreas().data.sortedBy {
            it.id
        }.forEach {
            sb.appendLine()
            sb.appendLine("""    public object  : LiveArea(id = ${it.id}, name = "${it.name}")""")
        }
        sb.appendLine("}")
        println(sb.toString())
    }

    @Test
    fun getRoomIdByUid() = runTest {
        client.getRoomIdByUid(2)
    }

    @Test
    fun getLiverInfo(): Unit = runTest {
        client.getLiverInfo(63231)
    }

    suspend fun createConnection(roomId: Int, config: LiveDanmakuConnectConfig.() -> Unit = {}) {
        val realId = client.getRoomInitInfo(roomId).data?.roomId ?: error("Get init info failed")
        val danmakuInfoData = client.getLiveDanmakuInfo(realId).data ?: error("Get live server failed")
        val loginUserMid = client.getBasicInfo().data.mid ?: error("Not login")
        client.createLiveDanmakuConnection(loginUserMid,
            realId,
            danmakuInfoData.token ?: error("Failed To Get Token"),
            danmakuInfoData.hostList.getOrNull(0) ?: error("Failed to fetch bilibili live host"),
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
    fun getLiveIndexListTest() = runTest {
        client.getLiveIndexList()
    }

    @Test
    fun getLiveHover() = runTest {
        client.getLiveHover(2)
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

    @Test
    fun signTest() = runTest {
        client.signLive()
    }

    @Test
    fun getLiveSignInfoTest() = runTest {
        client.getLiveSignInfo()
    }

    @Test
    fun getLiveSignLastMonthInfo() = runTest {
        client.getLiveSignLastMonthInfo()
    }

    @Test
    fun getLiveRank() = runTest {
        LiveRankType.values().forEach {
            client.getLiveRank(it)
        }
    }
    @Test
    fun getLiveMedalRank() = runTest {
        client.getLiveMedalRank()
    }
}
