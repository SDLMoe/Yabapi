package moe.sdl.yabapi.packet

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
