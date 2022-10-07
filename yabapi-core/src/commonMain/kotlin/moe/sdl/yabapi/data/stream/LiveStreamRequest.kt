package moe.sdl.yabapi.data.stream

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.stream.LiveQnQuality.ORIGIN

@Serializable
public data class LiveStreamRequest(
    val protocols: Set<LiveStreamProtocol> = LiveStreamProtocol.values().toSet(),
    val formats: Set<LiveStreamFormat> = LiveStreamFormat.values().toSet(),
    val codecs: Set<LiveStreamCodec> = LiveStreamCodec.values().toSet(),
    val qnQuality: LiveQnQuality = ORIGIN,
    val platform: String = "web",
    val pType: Int = 8, // platform type?
)

internal fun HttpRequestBuilder.putLiveStreamRequest(request: LiveStreamRequest) {
    request.apply {
        parameter("protocol", protocols.joinToString(",") { it.code.toString() })
        parameter("format", formats.joinToString(",") { it.code.toString() })
        parameter("codec", codecs.joinToString(",") { it.code.toString() })
        parameter("qn", qnQuality.code)
        parameter("platform", platform)
        parameter("ptype", pType)
    }
}
