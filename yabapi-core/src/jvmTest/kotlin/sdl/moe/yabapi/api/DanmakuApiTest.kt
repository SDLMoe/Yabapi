// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.DanmakuApi.getDanmaku
import sdl.moe.yabapi.api.VideoApi.getVideoParts
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class DanmakuApiTest {
    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    init {
        yabapiLogLevel = DEBUG
    }

    @ExperimentalSerializationApi
    @Test
    fun getDanmakuTest() {
        runBlocking {
            val aid = 810872
            client.getVideoParts(aid).data.forEach { part ->
                client.getDanmaku(part.cid).danmakus.forEach {
                    println(it.color)
                }
            }
        }
    }
}
