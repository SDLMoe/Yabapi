package moe.sdl.yabapi.enums

import moe.sdl.yabapi.enums.video.Douga
import moe.sdl.yabapi.enums.video.VideoType
import kotlin.test.Test
import kotlin.test.assertEquals

internal class VideoTypeTest {
    @Test
    fun getAllTypesTest() {
        VideoType.getAllTypes()
        val fromCode = VideoType.fromCode("douga")
        val fromTid = VideoType.fromTid(1)
        assertEquals(Douga, fromCode)
        assertEquals(Douga, fromTid)
    }

    @Test
    fun getAllRouteUrlTest() {
        VideoType.getAllUrl().forEach(::println)
    }
}
