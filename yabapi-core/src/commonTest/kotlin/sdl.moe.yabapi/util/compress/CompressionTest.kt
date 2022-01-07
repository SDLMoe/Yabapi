// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.compress

import com.soywiz.krypto.SecureRandom
import sdl.moe.yabapi.runTest
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
