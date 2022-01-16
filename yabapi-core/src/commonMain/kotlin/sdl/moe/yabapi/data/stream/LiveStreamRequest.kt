// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.stream

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.stream.LiveQnQuality.ORIGIN

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
        parameter("format", protocols.joinToString(",") { it.code.toString() })
        parameter("codec", protocols.joinToString(",") { it.code.toString() })
        parameter("qn", qnQuality.code)
        parameter("platform", platform)
        parameter("ptype", pType)
    }
}
