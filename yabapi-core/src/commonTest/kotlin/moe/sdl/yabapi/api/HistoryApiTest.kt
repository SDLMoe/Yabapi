package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.history.HistoryType
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import moe.sdl.yabapi.util.encoding.avInt
import kotlin.test.Test

internal class HistoryApiTest {
    init {
        initTest()
    }

    @Test
    fun getHistoryTest(): Unit = runTest {
        with(client) {
            val viewAt = getHistory().data!!.list.last().viewAt
            getHistory(fromTime = viewAt!!)
        }
    }

    @Test
    fun deleteHistoryTest(): Unit = runTest {
        client.deleteHistory("BV1Qm4y1D7Rd".avInt, HistoryType.VIDEO)
    }

    @Test
    fun setStopHistoryTest(): Unit = runTest {
        client.setStopHistory(true)
    }

    @Test
    fun getHistoryStatusTest(): Unit = runTest {
        client.getHistoryStatus()
    }

    @Test
    fun clearHistoryTest(): Unit = runTest {
        client.clearHistory()
    }

    @Test
    fun getLaterWatchTest(): Unit = runTest {
        client.getLaterWatch()
    }

    @Test
    fun addLaterWatchTest(): Unit = runTest {
        client.addLaterWatch(170001)
        client.addLaterWatch("BV1au411m76q")
    }

    @Test
    fun addChannelToLaterWatchTest(): Unit = runTest {
        client.addChannelToLaterWatch(72, 63231)
    }

    @Test
    fun deleteLaterWatchTest(): Unit = runTest {
        client.deleteLaterWatch(170001)
        client.deleteViewedLaterWatch()
        client.deleteLaterWatch("BV1Gs411C7Pt")
    }

    @Test
    fun clearLaterWatchTest(): Unit = runTest {
        client.clearLaterWatch()
    }
}
