package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
import kotlin.test.Test

internal class ArticleApiTest {
    init {
        initTest()
    }

    @Test
    fun getArticleInfoTest() = runTest {
        client.getArticleInfo(8042693)
    }

    @Test
    fun getArticleDetailedTest() = runTest {
        listOf(15018183, 15018153, 15018213).forEach {
            client.getArticleDetailed(it)
        }
    }

    @Test
    fun getCollectionInfoTest() = runTest {
        client.apply {
            val rlId = getArticleDetailed(15018651)!!.readInfo.collection!!.id
            getCollectionInfo(rlId)
        }
    }
}
