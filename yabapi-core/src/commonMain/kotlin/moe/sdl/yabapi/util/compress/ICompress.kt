package moe.sdl.yabapi.util.compress

public interface ICompress {
    public suspend fun compress(data: ByteArray): ByteArray
    public suspend fun decompress(data: ByteArray): ByteArray
}
