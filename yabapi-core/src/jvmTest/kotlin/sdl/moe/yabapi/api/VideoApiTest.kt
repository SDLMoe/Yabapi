// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.VideoApi.getVideoInfo
import sdl.moe.yabapi.enums.LogLevel.DEBUG
import sdl.moe.yabapi.storage.FileCookieStorage
import sdl.moe.yabapi.util.yabapiLogLevel
import kotlin.test.Test

internal class VideoApiTest {

    init {
        yabapiLogLevel = DEBUG
    }

    private val client = BiliClient(cookieStorage = FileCookieStorage("cookies.json"))

    @Test
    fun getVideoInfo() {
        runBlocking {
            listOf(
                2,
                7,
                507448290,
                933731156,
                971149764,
                422651576,
                974531866,
                892816331,
                252043983,
                252702924,
                206735865,
                549481623,
            ).forEach {
                client.getVideoInfo(it)
            }
        }
    }

    @Test
    fun getUgcSeasonInfo() {
        runBlocking {
            listOf("BV1ei4y1X7mo", "BV1jF411B7sw", "BV17A411575p", "BV1h34y1o7bz").forEach {
                client.getVideoInfo(it)
            }
        }
    }
}
