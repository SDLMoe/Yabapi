package moe.sdl.yabapi.util

import moe.sdl.yabapi.util.encoding.av
import moe.sdl.yabapi.util.encoding.bv
import kotlin.test.Test
import kotlin.test.assertEquals

internal class AbNumberTest {

    @Test
    fun avToBv() {
        val av = 1919810L
        val result = av.bv
        assertEquals(result, "BV1cx411A7dD")
    }

    @Test
    fun bvToAv() {
        val bv = "BV1cx411A7dD"
        val result = bv.av
        assertEquals(result, 1919810L)
    }
}
