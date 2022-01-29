package moe.sdl.yabapi.data.stream

public data class StreamRequest(
    val qnQuality: QnQuality = QnQuality.V1080P,
    val fnvalFormat: VideoFnvalFormat = VideoFnvalFormat(),
) {
    val fourk: Int = if (fnvalFormat.need4K) 0 else 1
}
