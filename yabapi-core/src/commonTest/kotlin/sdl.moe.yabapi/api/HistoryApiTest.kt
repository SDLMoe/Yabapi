package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.history.HistoryType
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import sdl.moe.yabapi.util.encoding.avInt
import kotlin.test.Test

internal class HistoryApiTest {
    init {
        initTest()
    }

    @Test
    fun getHistoryTest() = runTest {
        with(client) {
            val viewAt = getHistory().data!!.list.last().viewAt
            getHistory(fromTime = viewAt)
        }
    }

    @Test
    fun deleteHistoryTest() = runTest {
        client.deleteHistory("BV1Qm4y1D7Rd".avInt, HistoryType.VIDEO)
    }

    @Test
    fun setStopHistoryTest() = runTest {
        client.setStopHistory(true)
    }

    @Test
    fun getHistoryStatusTest() = runTest {
        client.getHistoryStatus()
    }

    @Test
    fun clearHistoryTest() = runTest {
        client.clearHistory()
    }
}
