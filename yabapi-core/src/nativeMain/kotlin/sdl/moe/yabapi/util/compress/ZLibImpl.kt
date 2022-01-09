// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.compress

import kotlinx.cinterop.cValuesOf
import kotlinx.cinterop.refTo

internal actual object ZLibImpl : ICompress {

    @OptIn(ExperimentalUnsignedTypes::class)
    override suspend fun compress(data: ByteArray): ByteArray {
        val ubyteRaw = data.toUByteArray()
        val ubyteDst = ubyteArrayOf()
        val dstLength = ubyteDst.size.toULong()
        platform.zlib.compress(
            dest = ubyteDst.refTo(0),
            destLen = cValuesOf(dstLength),
            source = ubyteRaw.refTo(1),
            sourceLen = data.size.toULong()
        )
        return ubyteDst.toByteArray()
    }

    override suspend fun decompress(data: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }
}
