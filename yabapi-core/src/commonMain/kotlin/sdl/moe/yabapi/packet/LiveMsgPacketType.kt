// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.packet

public enum class LiveMsgPacketType(public val code: UInt) {
    HEARTBEAT(2u),

    HEARTBEAT_RESPONSE(3u),

    COMMAND(5u),

    CERTIFICATE(7u),

    CERTIFICATE_RESPONSE(8u);

    public companion object {
        public fun fromCode(code: UInt): LiveMsgPacketType? = values().firstOrNull { it.code == code }
    }
}
