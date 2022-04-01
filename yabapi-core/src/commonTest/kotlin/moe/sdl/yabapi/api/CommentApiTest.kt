package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

class CommentApiTest {
    init {
        initTest()
    }

    @Test
    fun getLazyCommentTest(): Unit = runTest {
        client.getComment {
            Type video 170001
            Lazy set on
        }.data?.cursor
        client.getComment {
            Type video 170001
            Lazy set off
        }.data?.page
    }
}
