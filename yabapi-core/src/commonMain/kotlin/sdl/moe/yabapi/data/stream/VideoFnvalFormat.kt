package sdl.moe.yabapi.data.stream

import kotlinx.serialization.Serializable
import sdl.moe.yabapi.enums.video.VideoFormat
import sdl.moe.yabapi.enums.video.VideoFormat.DASH
import sdl.moe.yabapi.enums.video.VideoFormat.FLV
import sdl.moe.yabapi.enums.video.VideoFormat.MP4
import sdl.moe.yabapi.serializer.data.stream.FnvalFormatSerializer

@Serializable(FnvalFormatSerializer::class)
public data class VideoFnvalFormat(
    val format: VideoFormat = FLV,
    val needHDR: Boolean = false,
    val need4K: Boolean = false,
    val need8K: Boolean = false,
    val needDolby: Boolean = false,
) {
    public companion object {
        public fun fromBinary(bin: Int): VideoFnvalFormat {
            val isMP4 = bin and 0b1 == 1
            val isDASH = bin and 0b1_0000 == 1 shl 4
            val needHDR = bin and 0b100_0000 == 1 shl 6
            val need4K = bin and 0b1000_0000 == 1 shl 7
            val needDolby = bin and 0b1_0000_0000 == 1 shl 8
            val videoFormat = when {
                isDASH -> DASH
                isMP4 -> MP4
                else -> FLV
            }
            return VideoFnvalFormat(videoFormat, needHDR, need4K, needDolby)
        }
    }

    public fun toBinary(): Int = buildList {
        add(format.fnvalCode)
        if (needHDR) add(64)
        if (need4K) add(128)
        if (needDolby) add(256)
        if (need8K) add(1024)
    }.reduce { acc, i -> acc or i }
}
