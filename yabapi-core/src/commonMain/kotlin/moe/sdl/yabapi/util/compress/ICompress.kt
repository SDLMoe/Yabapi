package moe.sdl.yabapi.util.compress

internal interface ICompress {
    suspend fun compress(data: ByteArray): ByteArray
    suspend fun decompress(data: ByteArray): ByteArray
}
