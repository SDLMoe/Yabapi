// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.compress

internal actual object ZLibImpl : ICompress {
    override suspend fun compress(byteArray: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    override suspend fun decompress(byteArray: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }
}
