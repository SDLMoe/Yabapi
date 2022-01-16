package sdl.moe.yabapi.data.stream

public enum class LiveStreamProtocol(public val code: Int) {
    HTTP_STREAM(0),

    HTTP_HLS(1),
    ;
}
