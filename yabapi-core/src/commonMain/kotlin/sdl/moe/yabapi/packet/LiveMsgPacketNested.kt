// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.packet

import sdl.moe.yabapi.packet.LiveMsgPacketType.COMMAND
import sdl.moe.yabapi.util.logger

public class LiveMsgPacketNested(
    override val head: LiveMsgPacketHead, // outer head
    override var body: ByteArray, // raw body, nested a list of live msg packet
    internal val list: List<LiveMsgPacket> = emptyList(),
) : LiveMsgPacket(head, body) {

    internal val stringBodies = list.map { it.body.decodeToString() }

    public companion object {
        public suspend fun decode(packet: ByteArray): LiveMsgPacketNested {
            val p = LiveMsgPacket.decode(packet)
            var decodedList: List<LiveMsgPacket>? = null
            if (p.head.type == COMMAND) {
                val decodedPart = p.body.copyOfRange(0, LiveMsgPacketHead.HEAD_SIZE.toInt()).decodeToString()
                logger.verbose { "Decoded part: $decodedPart" }
                val isTail = decodedPart.startsWith('{') // infer is or not json
                if (!isTail) {
                    decodedList = decodeList(p.body)
                }
            }
            return LiveMsgPacketNested(p.head, p.body, decodedList ?: emptyList())
        }

        private tailrec fun decodeList(
            rawBody: ByteArray,
            decodedList: MutableList<LiveMsgPacket> = mutableListOf(),
        ): List<LiveMsgPacket> {
            if (rawBody.isEmpty()) return decodedList
            val head = LiveMsgPacketHead.decode(rawBody.copyOfRange(0, LiveMsgPacketHead.HEAD_SIZE.toInt()))
            decodedList.add(LiveMsgPacket(head, rawBody.copyOfRange(LiveMsgPacketHead.HEAD_SIZE.toInt(), rawBody.size)))
            return decodeList(rawBody.copyOfRange(head.size.toInt(), rawBody.size), decodedList)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as LiveMsgPacketNested

        if (head != other.head) return false
        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = head.hashCode()
        result = 31 * result + body.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "LiveMsgPacketNested(head=$head, list=$list)"
    }
}
