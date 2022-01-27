package sdl.moe.yabapi.data.danmaku

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.Yabapi.defaultJson
import sdl.moe.yabapi.data.danmaku.DanmakuExtraSubType.UNKNOWN

// @Serializable
// public data class DanmakuMetadataResponse(
//     @SerialName("DmWebViewReply") val viewReply: DanmakuWebViewReply? = null,
// )

@Serializable
public data class DanmakuMetadataResponse(
    @SerialName("state") private val _state: Int? = null,
    @SerialName("text") val text: String? = null,
    @SerialName("textSide") val textSide: String? = null,
    @SerialName("dmSge") val segment: DanmakuSegment? = null,
    @SerialName("flag") val flag: DanmakuFlag? = null,
    @SerialName("specialDms") val specials: List<String> = emptyList(),
    @SerialName("checkBox") val checkBox: Boolean? = null,
    @SerialName("count") val count: Long? = null,
    @SerialName("commandDms") val commandDanmakus: List<CommandDanmaku> = emptyList(),
    @SerialName("dmSetting") val setting: DanmakuSetting? = null,
) {
    public val isOpen: Boolean
        get() = _state == 0
}

@Serializable
public data class DanmakuSegment(
    @SerialName("pageSize") val pageSize: Long? = null,
    @SerialName("total") val total: Long? = null,
)

@Serializable
public data class DanmakuFlag(
    @SerialName("recFlag") val recFlag: Int? = null,
    @SerialName("recText") val recText: String? = null,
    @SerialName("recSwitch") val recSwitch: Int? = null,
)

@Serializable
public data class CommandDanmaku(
    @SerialName("id") val id: Long? = null,
    @SerialName("oid") val cid: Long? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("command") val command: String? = null,
    @SerialName("content") val content: String? = null,
    @SerialName("progress") val progress: Int? = null,
    @SerialName("ctime") val createdTime: String? = null,
    @SerialName("mtime") val modifiedTime: String? = null,
    @SerialName("extra") private val _extra: String? = null,
    @SerialName("idStr") val idStr: String? = null,
) {
    public fun getExtra(json: Json = defaultJson.value): CommandDanmakuExtra = when (command) {
        "#UP#" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraUp>(it) } ?: Unknown
        "#LINK#" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraLink>(it) } ?: Unknown
        "ATTENTION" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraSub>(it) } ?: Unknown
        else -> Unknown
    }
}

public sealed class CommandDanmakuExtra

public object Unknown : CommandDanmakuExtra()

@Serializable
public data class CommandDanmakuExtraUp(
    @SerialName("icon") val icon: String? = null,
) : CommandDanmakuExtra()

@Serializable
public data class CommandDanmakuExtraLink(
    @SerialName("aid") val aid: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("bvid") val bvid: String? = null,
) : CommandDanmakuExtra()

@Serializable
public data class CommandDanmakuExtraSub(
    @SerialName("duration") val duration: Int? = null, // ms
    @SerialName("posX") val posX: Double? = null,
    @SerialName("posY") val posY: Double? = null,
    @SerialName("icon") val icon: String? = null,
    @SerialName("type") val type: DanmakuExtraSubType = UNKNOWN,
) : CommandDanmakuExtra()

@Serializable
public enum class DanmakuExtraSubType {
    UNKNOWN,

    @SerialName("0")
    SUB,

    @SerialName("1")
    LIKE,

    @SerialName("2")
    SUB_WITH_LIKE,
}

@Serializable
public data class DanmakuSetting(
    @SerialName("dmSwitch") val dmSwitch: Boolean? = null,
    @SerialName("aiSwitch") val aiSwitch: Boolean? = null,
    @SerialName("aiLevel") val aiLevel: Int? = null,
    @SerialName("blocktop") val blockTop: Boolean? = null,
    @SerialName("blockscroll") val blockScroll: Boolean? = null,
    @SerialName("blockbottom") val blockBottom: Boolean? = null,
    @SerialName("blockcolor") val blockColor: Boolean? = null,
    @SerialName("blockspecial") val blockSpecial: Boolean? = null,
    @SerialName("preventshade") val preventShade: Boolean? = null,
    @SerialName("dmask") val faceMask: Boolean? = null,
    @SerialName("opacity") val opacity: Float? = null,
    @SerialName("dmarea") val area: Int? = null,
    @SerialName("speedplus") val speedCoefficient: Float? = null,
    @SerialName("fontsize") val fontSizeCoefficient: Float? = null,
    @SerialName("screensync") val screenSync: Boolean? = null,
    @SerialName("speedsync") val speedSync: Boolean? = null,
    @SerialName("fontfamily") val fontFamily: String? = null,
    @SerialName("bold") val bold: Boolean? = null,
    @SerialName("fontborder") val fontBorder: Int? = null,
    @SerialName("drawType") val drawType: String? = null,
)
