package moe.sdl.yabapi.api

import kotlinx.coroutines.delay
import moe.sdl.yabapi.client
import moe.sdl.yabapi.enums.feed.FeedType
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class FeedApiTest {
    init {
        initTest()
    }

    @Test
    fun getFeedContentTest() = runTest {
        client.getFeedContent(619969539813998819uL)
    }

    @Test
    fun getShareCardTest() = runTest {
        client.getFeedContent(618811530730428013uL).data!!.card!!.getCard()
    }

    @Test
    fun getNewFeedTest() = runTest {
        client.apply {
            getNewFeed(getBasicInfo().data.mid!!, intArrayOf(FeedType.ALL.code)).data?.cards?.forEach { node ->
                node.getCard()
            }
        }
    }

    @Test
    fun getHistoryFeedTest() = runTest {
        client.apply {
            val currentUid = getBasicInfo().data.mid!!
            val types = intArrayOf(FeedType.ALL.code)
            var hasNext = true
            var offset = null ?: getNewFeed(currentUid, types).data!!.historyOffset
            while (hasNext) {
                getHistoryFeed(currentUid, types, offset!!).data.also { feeds ->
                    feeds?.cards?.forEach {
                        println(it.getCard())
                    }
                }.also {
                    hasNext = it?.hasMore!!
                    offset = it.nextOffset!!
                    println("=============Now Offset $offset=============")
                }
                delay(2500)
            }
        }
    }

    @Test
    fun getFeedByUidTest() = runTest {
        client.apply { getFeedByUid(getBasicInfo().data.mid!!, 2) }
    }
}
