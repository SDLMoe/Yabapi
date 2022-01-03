// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.stream

public data class StreamRequest(
    val qnQuality: QnQuality = QnQuality.V1080P,
    val fnvalFormat: VideoFnvalFormat = VideoFnvalFormat(),
) {
    val fourk: Int = if (fnvalFormat.need4K) 0 else 1
}
