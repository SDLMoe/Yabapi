package moe.sdl.yabapi.packet

import kotlinx.serialization.Serializable
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.COMMAND_BROTLI
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.COMMAND_NO_COMPRESSION
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.COMMAND_ZLIB
import moe.sdl.yabapi.packet.LiveMsgPacketProtocol.SPECIAL_NO_COMPRESSION

/**
 * @property COMMAND_NO_COMPRESSION 普通包正文無壓縮
 * @property SPECIAL_NO_COMPRESSION 心跳/認證正文無壓縮
 * @property COMMAND_ZLIB 普通包正文使用 ZLIB 壓縮
 * @property COMMAND_BROTLI 普通包正文使用 BROTLI 壓縮, 解壓爲帶頭部普通包
 */
@Serializable
public enum class LiveMsgPacketProtocol(public val code: UShort) {
    COMMAND_NO_COMPRESSION(0u),

    SPECIAL_NO_COMPRESSION(1u),

    COMMAND_ZLIB(2u),

    COMMAND_BROTLI(3u),
    ;

    public companion object {
        public fun fromCode(code: UShort): LiveMsgPacketProtocol? = values().firstOrNull { code == it.code }
    }
}
