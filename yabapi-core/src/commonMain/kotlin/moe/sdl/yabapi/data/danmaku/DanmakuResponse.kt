package moe.sdl.yabapi.data.danmaku

import kotlinx.serialization.Serializable
import moe.sdl.yabapi.Platform
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.util.encoding.inverseCrc32
import kotlin.coroutines.CoroutineContext

@Serializable
public data class DanmakuResponse(
    val danmakus: List<DanmakuContent> = emptyList(),
)

@Serializable
public data class DanmakuContent(
    val id: Long? = null,
    val progress: Int? = null, // ms
    private val _mode: Int? = null,
    val fontsize: Int? = null,
    private val _color: UInt? = null,
    val midHash: String? = null,
    val content: String? = null,
    val createdTime: Long? = null,
    val weight: Int? = null,
    val action: String? = null,
    val pool: Int? = null,
    val idStr: String? = null,
) {
    val color: RgbColor?
        get() = _color?.let { RgbColor.fromHex(it) }
    val mode: DanmakuMode?
        get() = _mode?.let { DanmakuMode.fromCode(it) }

    public suspend fun getActualMid(
        context: CoroutineContext = Platform.ioDispatcher,
    ): Int? = midHash?.let { inverseCrc32(midHash, context) }?.toIntOrNull()
}
