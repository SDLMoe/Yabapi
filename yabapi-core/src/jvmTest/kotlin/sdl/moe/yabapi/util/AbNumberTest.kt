package sdl.moe.yabapi.util

import org.junit.jupiter.api.Test

internal class AbNumberTest {

    @Test
    fun avToBv() {
        val av = "av1919810"
        val result = av.bv
        assert(result == "BV1cx411A7dD")
    }

    @Test
    fun bvToAv() {
        val bv = "BV1cx411A7dD"
        val result = bv.av
        assert(result == "av1919810")
    }

}
