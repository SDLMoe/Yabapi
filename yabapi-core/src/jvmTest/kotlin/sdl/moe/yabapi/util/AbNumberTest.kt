package sdl.moe.yabapi.util

import kotlin.test.Test

internal class AbNumberTest {

    @Test
    fun avToBv() {
        val av = 1919810L
        val result = av.bv
        assert(result == "BV1cx411A7dD")
    }

    @Test
    fun bvToAv() {
        val bv = "BV1cx411A7dD"
        val result = bv.av
        assert(result == 1919810L)
    }
}
