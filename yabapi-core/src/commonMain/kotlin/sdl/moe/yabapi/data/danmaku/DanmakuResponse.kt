package sdl.moe.yabapi.data.danmaku

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.data.RgbColor
import sdl.moe.yabapi.util.encoding.inverseCrc32
import kotlin.coroutines.CoroutineContext

@Serializable
public data class DanmakuResponse(
    @SerialName("elems") val danmakus: List<DanmakuContent> = emptyList(),
)

@Serializable
public data class DanmakuContent(
    @SerialName("id") val id: Long? = null,
    @SerialName("progress") val progress: Int? = null, // ms
    @SerialName("mode") private val _mode: Int? = null,
    @SerialName("fontsize") val fontsize: Int? = null,
    @SerialName("color") private val _color: UInt? = null,
    @SerialName("midHash") val midHash: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("weight") val weight: Int? = null,
    @SerialName("action") val action: String? = null,
    @SerialName("pool") val pool: Int? = null,
    @SerialName("idStr") val idStr: String? = null,
) {
    val color: RgbColor?
        get() = _color?.let { RgbColor.fromHex(it) }
    val mode: DanmakuMode?
        get() = _mode?.let { DanmakuMode.fromCode(it) }

    public suspend fun getActualMid(
        context: CoroutineContext = Platform.ioDispatcher,
    ): Int? = midHash?.let { inverseCrc32(midHash, context) }?.toIntOrNull()
}
