package sdl.moe.yabapi.api

import kotlinx.coroutines.delay
import sdl.moe.yabapi.client
import sdl.moe.yabapi.enums.feed.FeedType
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
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
    fun getNewFeedTest() = runTest {
        client.apply {
            getNewFeed(getBasicInfo().data.mid!!, intArrayOf(FeedType.ALL.code)).data?.cards?.forEach { node ->
                val card = node.getCard()
                // println(card)
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
