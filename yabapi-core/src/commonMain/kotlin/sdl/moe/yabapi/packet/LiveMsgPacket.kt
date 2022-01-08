// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.packet

import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.writeFully
import sdl.moe.yabapi.packet.LiveMsgPacketProtocol.COMMAND_BROTLI
import sdl.moe.yabapi.packet.LiveMsgPacketProtocol.COMMAND_ZLIB
import sdl.moe.yabapi.util.compress.BrotliImpl
import sdl.moe.yabapi.util.compress.ZLibImpl
import kotlin.math.min

public open class LiveMsgPacket(
    internal open val head: LiveMsgPacketHead,
    internal open var body: ByteArray,
) {
    public constructor(
        protocol: LiveMsgPacketProtocol, type: LiveMsgPacketType, sequence: Sequence, body: ByteArray,
    ) : this(
        LiveMsgPacketHead(
            body.size.toUInt() + LiveMsgPacketHead.HEAD_SIZE.toUInt(),
            LiveMsgPacketHead.HEAD_SIZE,
            protocol,
            type,
            sequence.value.value.toUInt(),
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
                    COMMAND_ZLIB -> ZLibImpl.decompress(rawBody)
                    COMMAND_BROTLI -> BrotliImpl.decompress(rawBody)
                    else -> rawBody
                }
            }
            return LiveMsgPacket(head, body)
        }
    }

    internal suspend fun encode(): ByteArray = buildPacket {
        body = when (head.protocol) {
            COMMAND_ZLIB -> ZLibImpl.compress(body)
            COMMAND_BROTLI -> BrotliImpl.compress(body)
            else -> body
        }
        writeFully(head.encode())
        writeFully(body)
    }.readBytes()

    override fun toString(): String {
        return "LiveMsgPacket(head=$head, body(head 20)=${body.copyOfRange(0, min(20, body.size)).contentToString()})"
    }
}
