// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

internal interface MD5 {
    fun digest(byte: ByteArray): ByteArray
}

internal object MD5Impl : MD5 {
    override fun digest(byte: ByteArray): ByteArray = com.soywiz.krypto.MD5.digest(byte).bytes
}
