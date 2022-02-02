package moe.sdl.yabapi.api

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromHexString
import kotlinx.serialization.protobuf.ProtoBuf
import moe.sdl.yabapi.client
import moe.sdl.yabapi.data.danmaku.DanmakuMetadata
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import moe.sdl.yabapi.util.encoding.avInt
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
                client.getDanmaku(part.cid!!).danmakus.forEach {
                    // println(it.color)
                }
            }
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun getDanmakuMetadataTest() {
        runTest {
            val aid = "BV13r4y1Y7VU".avInt
            val cid = client.getVideoParts(aid).data[0].cid
            client.getDanmakuMetadata(cid!!)/*.also {
                it.viewReply?.isOpen
                it.viewReply?.commandDanmakus?.forEach {
                    it.getExtra()
                }
            }*/
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun test() {
        ProtoBuf.decodeFromHexString<DanmakuMetadata>("7b22636f6465223a2d3430302c226d657373616765223a224b65793a20275365676d656e74496e64657827204572726f723a4669656c642076616c69646174696f6e20666f7220275365676d656e74496e64657827206661696c6564206f6e2074686520276d696e2720746167222c2274746c223a317d")
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
