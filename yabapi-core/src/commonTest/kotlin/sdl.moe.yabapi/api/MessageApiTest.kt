// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.data.message.MessageContent
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import sdl.moe.yabapi.util.now
import kotlin.test.Test

internal class MessageApiTest {
    init {
        initTest()
    }

    @Test
    fun getUnreadCount() {
        runTest {
            client.getUnreadMsgCount()
        }
    }

    @Test
    fun getUnreadWhisperCount() {
        runTest {
            client.getUnreadWhisperCount()
        }
    }

    @Test
    fun sendTextTest() {
        runTest {
            client.sendMessageTo(30358448, MessageContent.Text("测试" + now().epochSeconds))
        }
    }

    @Test
    fun sendImageTest() {
        runTest {
            client.sendMessageTo(30358448, MessageContent.Image(
                "https://i2.hdslb.com/bfs/face/2e07981b42bc26ebc2b25f374a8fb99c36e875b1.jpg@168w_168h_1c.webp",
            ))
        }
    }

    @Test
    fun recallMsg() {
        runTest {
            val response = client.sendMessageTo(30358448, MessageContent.Text("这是一条应该被撤回的消息-${now()}"))
            client.sendMessageTo(30358448, MessageContent.Recall(checkNotNull(response.data?.key.toString())))
        }
    }
}