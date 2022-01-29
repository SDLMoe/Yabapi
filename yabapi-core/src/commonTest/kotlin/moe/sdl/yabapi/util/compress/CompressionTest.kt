package moe.sdl.yabapi.util.compress

import com.soywiz.krypto.SecureRandom
import moe.sdl.yabapi.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals

internal class CompressionTest {
    private fun compressTest(compressor: ICompress) {
        runTest {
            val source = SecureRandom.nextBytes(5000)
            val encoded = compressor.compress(source)
            val decoded = compressor.decompress(encoded)
            assertContentEquals(source, decoded)
        }
    }

    @Test
    fun deflate() {
        compressTest(ZLibImpl)
    }

    @Test
    fun br() {
        compressTest(BrotliImpl)
    }
}
