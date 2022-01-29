package moe.sdl.yabapi.data.video

import moe.sdl.yabapi.initTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class VideoAttributeTest {
    init {
        initTest()
    }

    @Test
    fun encode() {
        assertEquals(512, VideoAttribute(isPgc = true).encode())
    }

    @Test
    fun decode() {
        assertEquals(true, VideoAttribute.decode(512).isPgc)
    }
}
