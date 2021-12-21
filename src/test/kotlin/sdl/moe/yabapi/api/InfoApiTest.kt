// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import kotlinx.coroutines.runBlocking
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.api.InfoApi.getBasicInfo

private val client: BiliClient = BiliClient()

internal class InfoApiTest {


}

fun main() {
    runBlocking {
        client.getBasicInfo()
    }
}
