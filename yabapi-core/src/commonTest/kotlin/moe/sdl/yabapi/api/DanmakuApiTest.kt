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
            val aid = 810872
            client.getVideoParts(aid).data.forEach { part ->
                client.getDanmaku(part.cid).danmakus.forEach {
                    // println(it.color)
                }
            }
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun getDanmakuMetadataTest() {
        runTest {
            val aid = 810872
            val cid = client.getVideoParts(aid).data[0].cid
            client.getDanmakuMetadata(cid)/*.also {
                it.viewReply?.isOpen
                it.viewReply?.commandDanmakus?.forEach {
                    it.getExtra()
                }
            }*/
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun getHistoryDanmaku() {
        runTest {
            val cid = client.getVideoParts("BV1mM4y1F7yh").data[0].cid
            val date = client.getDanmakuCalendar(cid, 2022, 1).availableList[0]
            client.getHistoryDanmaku(cid, date)
        }
    }
}
