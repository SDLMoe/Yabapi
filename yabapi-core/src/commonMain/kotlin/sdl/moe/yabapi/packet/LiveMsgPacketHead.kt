// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.packet

import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readBytes
import io.ktor.utils.io.core.readUInt
import io.ktor.utils.io.core.readUShort
import io.ktor.utils.io.core.writeFully
import io.ktor.utils.io.core.writeUInt
import io.ktor.utils.io.core.writeUShort
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic
import kotlin.jvm.JvmInline

@JvmInline
public value class Sequence(
    public val value: AtomicLong = atomic(0L),
)

public data class LiveMsgPacketHead(
    val size: UInt,
    val headSize: UShort,
    val protocol: LiveMsgPacketProtocol,
    val type: LiveMsgPacketType,
    val sequence: UInt,
) {

    internal constructor(
        size: UInt, headSize: UShort, protocol: UShort, type: UInt, sequence: UInt,
    ) : this(
        size,
        headSize,
        protocol = LiveMsgPacketProtocol.fromCode(protocol) ?: error("Unknown protocol code: $protocol"),
        type = LiveMsgPacketType.fromCode(type) ?: error("Unknown type code: $type"),
        sequence
    )

    public companion object {
        public fun decode(bytes: ByteArray): LiveMsgPacketHead {
            val size: UInt
            val headSize: UShort
            val protocol: UShort
            val type: UInt
            val sequence: UInt
            buildPacket {
                this.writeFully(bytes)
            }.apply {
                size = readUInt()
                headSize = readUShort()
                protocol = readUShort()
                type = readUInt()
                sequence = readUInt()
            }
            return LiveMsgPacketHead(size, headSize, protocol, type, sequence)
        }

        internal const val HEAD_SIZE: UShort = 0x10u
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    public fun encode(): ByteArray = buildPacket {
        this.writeUInt(this@LiveMsgPacketHead.size)
        this.writeUShort(headSize)
        this.writeUShort(protocol.code)
        this.writeUInt(type.code)
        this.writeUInt(sequence)
    }.readBytes()
}
