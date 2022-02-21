package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class ArticleApiTest {
    init {
        initTest()
    }

    @Test
    fun getArticleInfoTest(): Unit = runTest {
        client.getArticleInfo(8042693)
    }

    @Test
    fun getArticleDetailedTest(): Unit = runTest {
        listOf(15018183, 15018153, 15018213).forEach {
            client.getArticleDetailed(it)
        }
    }

    @Test
    fun getFavoritesInfoTest(): Unit = runTest {
        client.apply {
            val rlId = getArticleDetailed(15018651)!!.readInfo!!.collection!!.id
            getArticleSetInfo(rlId!!)
        }
    }
}
