// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.serialization.ExperimentalSerializationApi
import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.runTest
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class DanmakuApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    @ExperimentalSerializationApi
    @Test
    fun getDanmakuTest() {
        runTest {
            val aid = 810872
            client.getVideoParts(aid).data.forEach { part ->
                client.getDanmaku(part.cid).danmakus.forEach {
                    println(it.color)
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
