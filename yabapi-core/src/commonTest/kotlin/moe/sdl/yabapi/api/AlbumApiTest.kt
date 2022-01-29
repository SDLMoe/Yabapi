package moe.sdl.yabapi.api

import moe.sdl.yabapi.client
import moe.sdl.yabapi.initTest
import moe.sdl.yabapi.runTest
import kotlin.test.Test

internal class AlbumApiTest {
    init {
        initTest()
    }
    @Test
    fun getAlbumInfoTest() = runTest {
        client.getVcAlbumInfo(1102793).getData()
    }
}
