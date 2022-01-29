package moe.sdl.yabapi.packet

import com.soywiz.korio.lang.toByteArray
import kotlinx.atomicfu.atomic
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.COMMAND_NO_COMPRESSION
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.COMMAND_ZLIB
import moe.sdl.yabapi.packet.LiveMsgPacketType.COMMAND
import moe.sdl.yabapi.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LiveMsgPacketTest {

    @ExperimentalUnsignedTypes
    @Test
    fun headReEncodeTest() {
        val raw = LiveMsgPacketHead(19999u, 51312u, COMMAND_ZLIB, COMMAND, 1111121u)
        val encoded = raw.encode()
        val decoded = LiveMsgPacketHead.decode(encoded)
        assertEquals(raw, decoded)
    }

    @ExperimentalUnsignedTypes
    @Test
    fun packetReEncodeTest() {
        runTest {
            val packet = LiveMsgPacket(COMMAND_NO_COMPRESSION, COMMAND, atomic(0L), "1141191293211".toByteArray())
            val encoded = packet.encode()
            val decoded = LiveMsgPacket.decode(encoded)
            assertEquals(packet, decoded)
        }
    }
}
