package moe.sdl.yabapi.data.stream

import moe.sdl.yabapi.enums.video.VideoFormat.DASH
import moe.sdl.yabapi.enums.video.VideoFormat.FLV
import moe.sdl.yabapi.enums.video.VideoFormat.MP4
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
