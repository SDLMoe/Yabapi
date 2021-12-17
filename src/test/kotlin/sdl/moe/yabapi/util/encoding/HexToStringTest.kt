package sdl.moe.yabapi.util.encoding

import org.junit.jupiter.api.Test

class HexToStringTest {

    @ExperimentalUnsignedTypes
    @Test
    fun `input to hex should equals to expected`() {
        val input = "114514"
        val expected = "313134353134"
        val result = input.toByteArray().hex
        assert(result == expected)
    }
}
