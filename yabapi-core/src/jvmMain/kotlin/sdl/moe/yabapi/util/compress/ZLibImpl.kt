// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.compress

import java.util.zip.Deflater
import java.util.zip.Inflater

internal actual object ZLibImpl : ICompress {

    override suspend fun compress(byteArray: ByteArray): ByteArray {
        val d = Deflater(8, true)
        val dst = ByteArray(byteArray.size + 5)
        d.setInput(byteArray)
        d.finish()
        val size = d.deflate(dst)
        d.end()
        return dst.copyOfRange(0, size)
    }

    override suspend fun decompress(byteArray: ByteArray): ByteArray {
        val i = Inflater(true)
        val dst = ByteArray(byteArray.size * 5)
        i.setInput(byteArray)
        val size = i.inflate(dst)
        i.end()
        return dst.copyOfRange(0, size)
    }
}
