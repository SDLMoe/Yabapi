package sdl.moe.yabapi.util.encoding

internal interface MD5 {
    fun digest(byte: ByteArray): ByteArray
}

internal object MD5Impl : MD5 {
    override fun digest(byte: ByteArray): ByteArray = com.soywiz.krypto.MD5.digest(byte).bytes
}
