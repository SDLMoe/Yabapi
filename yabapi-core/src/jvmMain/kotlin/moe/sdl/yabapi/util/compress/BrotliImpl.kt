package moe.sdl.yabapi.util.compress

internal actual object BrotliImpl : ICompress {
    override suspend fun compress(data: ByteArray): ByteArray = TODO()
    override suspend fun decompress(data: ByteArray): ByteArray = TODO()
}
