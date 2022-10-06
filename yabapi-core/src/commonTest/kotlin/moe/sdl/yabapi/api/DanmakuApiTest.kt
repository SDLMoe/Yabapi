package moe.sdl.yabapi.api

import kotlinx.serialization.ExperimentalSerializationApi
import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class DanmakuApiTest {

    init {
        initTest()
    }

    @ExperimentalSerializationApi
    @Test
    fun getDanmakuTest() {
        runTest {
            val aid = 810872L
            client.getVideoParts(aid).data.forEach { part ->
                client.getDanmaku(part.cid!!).danmakus.forEach {
                    // println(it.color)
                }
            }
        }
    }
    @ExperimentalSerializationApi
    @Test
    fun getHistoryDanmaku() {
        runTest {
            val cid = client.getVideoParts("BV1mM4y1F7yh").data[0].cid
            val date = client.getDanmakuCalendar(cid!!, 2022, 1).availableList[0]
            client.getHistoryDanmaku(cid, date)
        }
    }
}
