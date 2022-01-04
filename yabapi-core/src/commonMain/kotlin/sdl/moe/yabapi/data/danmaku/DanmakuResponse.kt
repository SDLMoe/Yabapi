// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.danmaku

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.RgbColor

@Serializable
public data class DanmakuResponse(
    @SerialName("elems") val danmakus: List<DanmakuContent> = emptyList(),
)

@Serializable
public data class DanmakuContent(
    @SerialName("id") val id: Long? = null,
    @SerialName("progress") val progress: Int? = null,
    @SerialName("mode") val mode: Int? = null,
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
}
