package moe.sdl.yabapi.data.stream

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import moe.sdl.yabapi.enums.video.VideoFormat.DASH

public data class StreamRequest(
    val qnQuality: QnQuality = QnQuality.V1080P,
    val fnvalFormat: VideoFnvalFormat = VideoFnvalFormat(),
) {
    val fourk: Int = if (fnvalFormat.need4K) 0 else 1

    public fun putParameter(builder: HttpRequestBuilder): HttpRequestBuilder = builder.apply {
        if (fnvalFormat.format != DASH) parameter("qn", qnQuality.code)
        parameter("fnval", fnvalFormat.toBinary())
        parameter("fnver", "0")
        parameter("fourk", fourk)
    }
}
