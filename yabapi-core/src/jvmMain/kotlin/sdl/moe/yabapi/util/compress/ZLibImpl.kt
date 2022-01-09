// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.compress

import java.io.ByteArrayOutputStream
import java.util.zip.Deflater
import java.util.zip.Deflater.BEST_COMPRESSION
import java.util.zip.Inflater

internal actual object ZLibImpl : ICompress {

    private const val BUFFER_SIZE = 1024

    override suspend fun compress(data: ByteArray): ByteArray {
        val d = Deflater().apply {
            setLevel(BEST_COMPRESSION)
            setInput(data)
        }
        return ByteArrayOutputStream(data.size).use {
            d.finish()
            val buffer = ByteArray(BUFFER_SIZE)
            while (!d.finished()) {
                val count = d.deflate(buffer)
                it.write(buffer, 0, count)
            }
            it.toByteArray()
        }
    }

    override suspend fun decompress(data: ByteArray): ByteArray {
        val i = Inflater().apply {
            setInput(data)
        }
        return ByteArrayOutputStream(data.size).use {
            val buffer = ByteArray(BUFFER_SIZE)
            while (!i.finished()) {
                val count = i.inflate(buffer)
                it.write(buffer, 0, count)
            }
            it.toByteArray()
        }
    }
}
