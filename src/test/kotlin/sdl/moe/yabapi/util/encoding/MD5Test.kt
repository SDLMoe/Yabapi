package sdl.moe.yabapi.util.encoding

import org.junit.jupiter.api.Test

class MD5Test {
    @Test
    fun `result should equals to excepted`() {
        val input = "114514"
        val excepted = "c4d038b4bed09fdb1471ef51ec3a32cd"
        val result = input.md5()
        assert(result == excepted)
    }
}
