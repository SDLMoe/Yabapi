package moe.sdl.yabapi.packet

import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import kotlinx.atomicfu.AtomicLong
import moe.sdl.yabapi.Yabapi
import moe.sdl.yabapi.util.compress.ZLibImpl

public open class LiveMsgPacket(
    internal open val header: LiveMsgPacketHead,
    internal open var body: ByteArray,
) {
    public constructor(
        protocol: LiveMsgPacketProtocol,
        type: LiveMsgPacketType,
        sequence: AtomicLong,
        body: ByteArray,
    ) : this(
        LiveMsgPacketHead(
            body.size.toUInt() + LiveMsgPacketHead.HEAD_SIZE.toUInt(),
            LiveMsgPacketHead.HEAD_SIZE,
            protocol,
            type,
            sequence.value.toUInt(),
        ),
        body
    )

    public companion object {
        public suspend fun decode(packet: ByteArray): LiveMsgPacket {
            val head: LiveMsgPacketHead
            val body: ByteArray
            buildPacket {
                writeFully(packet)
            }.apply {
                val rawHead = readBytes(LiveMsgPacketHead.HEAD_SIZE.toInt())
                head = LiveMsgPacketHead.decode(rawHead)
                val rawBody = readBytes()
                body = when (head.protocol) {
                    LiveMsgPacketProtocol.COMMAND_ZLIB -> ZLibImpl.decompress(rawBody)
                    LiveMsgPacketProtocol.COMMAND_BROTLI ->
                        Yabapi.brotliImpl.value?.decompress(rawBody).let {
                            it?.copyOfRange(head.headSize.toInt(), it.size)
                        } ?: throw UnsupportedOperationException("No implementation for Brotli decompression")
                    else -> rawBody
                }
            }
            return LiveMsgPacket(head, body)
        }
    }

    private suspend inline fun encodeBody(body: ByteArray): ByteArray =
        when (header.protocol) {
            LiveMsgPacketProtocol.COMMAND_ZLIB -> ZLibImpl.compress(body)
            LiveMsgPacketProtocol.COMMAND_BROTLI -> Yabapi.brotliImpl.value?.compress(body)
                ?: throw UnsupportedOperationException("No implementation for Brotli compression")
            else -> body
        }

    public suspend fun encode(): ByteArray = buildPacket {
        writeFully(header.encode())
        writeFully(encodeBody(body))
    }.readBytes()

    override fun toString(): String {
        return "LiveMsgPacket(head=$header, body(head 20)=[${body.joinToString(limit = 20)}])"
    }
}
