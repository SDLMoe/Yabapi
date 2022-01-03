// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.stream

import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.enums.video.VideoFormat.FLV
import sdl.moe.yabapi.enums.video.VideoFormat.MP4
import kotlin.test.Test
import kotlin.test.assertEquals

internal class VideoFnvalFormatTest() {

    @Test
    fun encodeTest() {
        listOf(
            VideoFnvalFormat(FLV, true, false, true),
            VideoFnvalFormat(MP4, true, false, false),
            VideoFnvalFormat(DASH, false, true, true)
        ).forEach {
            val bin = it.toBinary().also(::println)
            val decoded = VideoFnvalFormat.fromBinary(bin).also(::println)
            assertEquals(it, decoded)
        }
    }
}
