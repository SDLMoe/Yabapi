package sdl.moe.yabapi.util.compress

internal interface ICompress {
    suspend fun compress(data: ByteArray): ByteArray
    suspend fun decompress(data: ByteArray): ByteArray
}
