package moe.sdl.yabapi.api

import kotlinx.datetime.Clock
import moe.sdl.yabapi.client
import moe.sdl.yabapi.data.message.MessageContent
import moe.sdl.yabapi.data.message.MessageSettingBuilder
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import moe.sdl.yabapi.util.now
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.DurationUnit.HOURS
import kotlin.time.toDuration

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
            client.sendMessageTo(
                30358448,
                MessageContent.Image(
                    "https://i2.hdslb.com/bfs/face/2e07981b42bc26ebc2b25f374a8fb99c36e875b1.jpg@168w_168h_1c.webp",
                )
            )
        }
    }

    @Test
    fun recallMsg() {
        runTest {
            val response = client.sendMessageTo(30358448, MessageContent.Text("这是一条应该被撤回的消息-${now()}"))
            client.sendMessageTo(30358448, MessageContent.Recall(checkNotNull(response.data?.key.toString())))
        }
    }

    @Test
    fun fetchMsgSessionsTest(): Unit = runTest {
        client.fetchMessageSessions().list.orEmpty().forEach {
            it.lastMsg?.content
        }
    }

    @Test
    fun modifyMessageSettingTest(): Unit = runTest {
        client.modifyMessageSetting {
            Intercept set off
            FoldUnfollowed set off
        }
    }

    @Test
    fun `message setting builder duplicates must be distinct`() {
        assertEquals(
            1,
            MessageSettingBuilder().apply {
                Intercept set off
                Intercept set on
                Intercept set on
            }.build().size,
        )
    }

    @Test
    fun `message setting builder keep last`() {
        assertEquals(
            1, // code of `FOLLOWED_ONLY` is `1`
            MessageSettingBuilder().apply {
                Comment set on
                Comment set off
                Comment set followed
            }.build().first().second,
        )
    }

    @Test
    fun fetchNewMessageSessionsTest(): Unit = runTest {
        client.fetchNewMessageSessions(Clock.System.now() - 5.toDuration(HOURS))
    }

    @Test
    fun fetchSessionMessageTest(): Unit = runTest {
        client.fetchSessionMessage(1823806389)
    }
}
