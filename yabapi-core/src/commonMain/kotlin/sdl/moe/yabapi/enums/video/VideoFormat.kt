package sdl.moe.yabapi.enums.video

public enum class VideoFormat(public val fnvalCode: Int) {
    FLV(0) {
        override val supportHevc: Boolean = false
    },
    MP4(1) {
        override val supportHevc: Boolean = false
    },
    DASH(16) {
        override val supportHevc: Boolean = true
    };

    public abstract val supportHevc: Boolean
}
