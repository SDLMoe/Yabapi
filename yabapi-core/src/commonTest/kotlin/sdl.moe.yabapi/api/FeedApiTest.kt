package sdl.moe.yabapi.api

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

}
