package sdl.moe.yabapi.api

import sdl.moe.yabapi.client
import sdl.moe.yabapi.initTest
import sdl.moe.yabapi.runTest
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
