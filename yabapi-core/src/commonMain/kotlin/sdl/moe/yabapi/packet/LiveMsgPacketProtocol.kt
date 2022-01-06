// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.packet

/**
 * @property COMMAND_NO_COMPRESSION 普通包正文無壓縮
 * @property SPECIAL_NO_COMPRESSION 心跳/認證正文無壓縮
 * @property COMMAND_ZLIB 普通包正文使用 ZLIB 壓縮
 * @property COMMAND_BROTLI 普通包正文使用 BROTLI 壓縮, 解壓爲帶頭部普通包
 */
public enum class LiveMsgPacketProtocol(public val code: UShort) {
    COMMAND_NO_COMPRESSION(0u),

    SPECIAL_NO_COMPRESSION(1u),

    COMMAND_ZLIB(2u),

    COMMAND_BROTLI(3u);

    public companion object {
        public fun fromCode(code: UShort): LiveMsgPacketProtocol? = values().firstOrNull { code == it.code }
    }
}
