package sdl.moe.yabapi.util.compress

internal actual object BrotliImpl : ICompress {
    override suspend fun compress(data: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }

    override suspend fun decompress(data: ByteArray): ByteArray {
        TODO("Not yet implemented")
    }
}
