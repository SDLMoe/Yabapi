package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.data.feed.cards.ImageCard
import sdl.moe.yabapi.data.feed.cards.TextCard
import sdl.moe.yabapi.data.feed.cards.RepostCard
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
            getNewFeed(getBasicInfo().data.mid!!, intArrayOf(1, 2)).data?.cards?.forEach { node ->
                val card = node.getCard()
                println(when (card) {
                    is TextCard -> card.item
                    is RepostCard -> card.item
                    is ImageCard -> card.item
                    else -> {
                        "Unknown card type $card"
                    }
                })
            }
        }
    }

}
