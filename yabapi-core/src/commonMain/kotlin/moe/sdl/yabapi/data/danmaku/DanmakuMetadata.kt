package moe.sdl.yabapi.data.danmaku

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.protobuf.ProtoNumber
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.data.danmaku.DanmakuExtraSubType.UNKNOWN

@Serializable
public data class DanmakuMetadata
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val state: Int? = null,
    @ProtoNumber(2) val text: String? = null,
    @ProtoNumber(3) val textSide: String? = null,
    @ProtoNumber(4) val segment: DanmakuSegment? = null,
    @ProtoNumber(5) val flag: DanmakuFlag? = null,
    @ProtoNumber(6) val specials: List<String> = emptyList(),
    @ProtoNumber(7) val checkBox: Boolean? = null,
    @ProtoNumber(8) val count: Long? = null,
    @ProtoNumber(9) val commandDanmakus: List<CommandDanmaku> = emptyList(),
    @ProtoNumber(10) val setting: DanmakuSetting? = null,
    @ProtoNumber(11) val filter: List<String> = emptyList(),
    @ProtoNumber(12) val expressions: List<DanmakuExpressions> = emptyList(),
)

@Serializable
public data class DanmakuSegment
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val pageSize: Long? = null,
    @ProtoNumber(2) val total: Long? = null,
)

@Serializable
public data class DanmakuFlag
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val recFlag: Int? = null,
    @ProtoNumber(2) val recText: String? = null,
    @ProtoNumber(3) val recSwitch: Int? = null,
)

@Serializable
public data class CommandDanmaku
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val id: Long? = null,
    @ProtoNumber(2) val cid: Long? = null,
    @ProtoNumber(3) val mid: Long? = null,
    @ProtoNumber(4) val command: String? = null,
    @ProtoNumber(5) val content: String? = null,
    @ProtoNumber(6) val progress: Int? = null,
    @ProtoNumber(7) val createdTime: String? = null,
    @ProtoNumber(8) val modifiedTime: String? = null,
    @ProtoNumber(9) private val _extra: String? = null,
    @ProtoNumber(10) val idStr: String? = null,
) {
    val extra: CommandDanmakuExtra by lazy {
        val json = defaultJson.value
        when (command) {
            "#UP#" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraUp>(it) } ?: Unknown
            "#LINK#" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraLink>(it) } ?: Unknown
            "ATTENTION" -> _extra?.let { json.decodeFromString<CommandDanmakuExtraSub>(it) } ?: Unknown
            else -> Unknown
        }
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
public data class DanmakuSetting
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val dmSwitch: Boolean? = null,
    @ProtoNumber(2) val aiSwitch: Boolean? = null,
    @ProtoNumber(3) val aiLevel: Int? = null,
    @ProtoNumber(4) val blockTop: Boolean? = null,
    @ProtoNumber(5) val blockScroll: Boolean? = null,
    @ProtoNumber(6) val blockBottom: Boolean? = null,
    @ProtoNumber(7) val blockColor: Boolean? = null,
    @ProtoNumber(8) val blockSpecial: Boolean? = null,
    @ProtoNumber(9) val preventShade: Boolean? = null,
    @ProtoNumber(10) val danmakuAsk: Boolean? = null,
    @ProtoNumber(11) val opacity: Float? = null,
    @ProtoNumber(12) val danmakuArea: Int? = null,
    @ProtoNumber(13) val speedPlus: Float? = null,
    @ProtoNumber(14) val fontSize: Float? = null,
    @ProtoNumber(15) val screenSync: Boolean? = null,
    @ProtoNumber(16) val speedSync: Boolean? = null,
    @ProtoNumber(17) val fontFamily: String? = null,
    @ProtoNumber(18) val bold: Boolean? = null,
    @ProtoNumber(19) val fontBorder: Int? = null,
    @ProtoNumber(20) val drawType: String? = null,
    @ProtoNumber(21) val seniorModeSwitch: Int? = null,
)

@Serializable
public data class DanmakuExpressions
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val data: List<DanmakuExpression> = emptyList(),
)

@Serializable
public data class DanmakuExpression
@OptIn(ExperimentalSerializationApi::class) constructor(
    @ProtoNumber(1) val keyword: List<String> = emptyList(),
    @ProtoNumber(2) val url: String? = null,
    @ProtoNumber(3) val period: List<Period> = emptyList(),
) {
    @Serializable
    public data class Period
    @OptIn(ExperimentalSerializationApi::class) constructor(
        @ProtoNumber(1) val start: Long? = null,
        @ProtoNumber(2) val end: Long? = null,
    )
}
