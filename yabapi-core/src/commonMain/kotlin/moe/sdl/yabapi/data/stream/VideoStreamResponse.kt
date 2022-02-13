package moe.sdl.yabapi.data.stream

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNames
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class VideoStreamResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoStreamData? = null,
)

@Serializable
public abstract class AbstractStreamData {
    public abstract val from: String?
    public abstract val result: String?
    public abstract val message: String?
    public abstract val quality: QnQuality?
    public abstract val format: String?
    public abstract val length: Long? // ms
    public abstract val acceptFormat: String?
    public abstract val acceptDescription: List<String>
    protected abstract val rawAcceptQuality: List<String>
    public abstract val videoCodecId: CodecId?
    public abstract val seekParam: String?
    public abstract val seekType: String?
    public abstract val urls: List<StreamUrl>
    public abstract val dash: DashStream?
    public abstract val supportFormats: List<SupportFormat>
    public abstract val highFormat: JsonElement?
}

@Serializable
public data class VideoStreamData(
    @SerialName("from") override val from: String? = null,
    @SerialName("result") override val result: String? = null,
    @SerialName("message") override val message: String? = null,
    @SerialName("quality") override val quality: QnQuality? = null,
    @SerialName("format") override val format: String? = null,
    @SerialName("timelength") override val length: Long? = null, // ms
    @SerialName("accept_format") override val acceptFormat: String? = null,
    @SerialName("accept_description") override val acceptDescription: List<String> = emptyList(),
    @SerialName("accept_quality") override val rawAcceptQuality: List<String> = emptyList(),
    @SerialName("video_codecid") override val videoCodecId: CodecId? = null,
    @SerialName("seek_param") override val seekParam: String? = null,
    @SerialName("seek_type") override val seekType: String? = null,
    @SerialName("durl") override val urls: List<StreamUrl> = emptyList(),
    @SerialName("dash") override val dash: DashStream? = null,
    @SerialName("support_formats") override val supportFormats: List<SupportFormat> = emptyList(),
    @SerialName("high_format") override val highFormat: JsonElement? = null,
): AbstractStreamData() {
    val acceptQuality: List<QnQuality> = rawAcceptQuality.fold(mutableListOf()) { acc, s ->
        acc.add(QnQuality.fromCode(s.toInt()))
        acc
    }
}

@Serializable
public data class StreamUrl(
    @SerialName("order") val order: Int? = null,
    @SerialName("length") val length: Int? = null, // ms
    @SerialName("size") val size: Int? = null, // byte
    @SerialName("ahead") val ahead: String? = null,
    @SerialName("vhead") val vhead: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("backup_url") val backups: List<String> = emptyList(),
    @SerialName("md5") val md5: String? = null,
)

@Serializable
public data class DashStream(
    @SerialName("duration") val duration: Int? = null,
    @SerialName("minBufferTime") val minBufferTime: Double? = null,
    @SerialName("min_buffer_time") private val _minBufferTime: Double? = null,
    @SerialName("video") val videos: List<DashTrack> = emptyList(),
    @SerialName("audio") val audios: List<DashTrack> = emptyList(),
    @SerialName("dolby") val dolby: Dolby? = null,
) {
    @Serializable
    public data class Dolby(
        val type: Int? = null,
        val audio: List<DashTrack> = emptyList(),
    )
}

@Serializable
public data class DashTrack(
    @SerialName("id") val id: QnQuality? = null,
    @SerialName("baseUrl") private val _baseUrl1: String? = null,
    @SerialName("base_url") private val _baseUrl2: String? = null,
    @SerialName("backupUrl") private val _backupUrl1: List<String>? = null,
    @SerialName("backup_url") private val _backupUrl2: List<String>? = null,
    @SerialName("bandwidth") private val bandwidth: Int? = null,
    @SerialName("mimeType") private val _mimeType1: String? = null,
    @SerialName("mime_type") private val _mimeType2: String? = null,
    @SerialName("codecs") private val codecs: String? = null,
    @SerialName("width") private val width: Int? = null,
    @SerialName("height") private val height: Int? = null,
    @SerialName("frameRate") private val _frameRate1: String? = null,
    @SerialName("frame_rate") private val _frameRate2: String? = null,
    @SerialName("sar") private val sar: String? = null,
    @SerialName("startWithSap") private val _startWithSap1: Int? = null,
    @SerialName("start_with_sap") private val _startWithSap2: Int? = null,
    @SerialName("SegmentBase") private val _segmentBase1: SegmentBase? = null,
    @SerialName("segment_base") private val _segmentBase2: SegmentBase? = null,
    @SerialName("size") val size: Long? = null, // 不一定返回, 目前僅觀察到 dolby 音軌有
    @SerialName("codecid") private val codec: CodecId = CodecId.UNKNOWN,
) {
    public val baseUrl: String?
        get() = _baseUrl1 ?: _baseUrl2

    public val backupUrl: List<String>
        get() = _backupUrl1 ?: _backupUrl2 ?: emptyList()

    public val mimeType: String?
        get() = _mimeType1 ?: _mimeType2

    public val frameRate: String?
        get() = _frameRate1 ?: _frameRate2

    public val startWithSap: Int?
        get() = _startWithSap1 ?: _startWithSap2

    public val segmentBase: SegmentBase?
        get() = _segmentBase1 ?: _segmentBase2
}

@Serializable
public enum class CodecId {
    UNKNOWN,

    @SerialName("7")
    AVC,

    @SerialName("12")
    HEVC,

    @SerialName("13")
    AV1,

    @SerialName("0")
    AUDIO,
}

@Serializable
public data class SegmentBase @OptIn(ExperimentalSerializationApi::class) constructor(
    @JsonNames("initialization", "Initialization") val initialization: String? = null,
    @JsonNames("index_range","indexRange") val indexRange: String? = null,
)

@Serializable
public data class SupportFormat(
    @SerialName("quality") val quality: QnQuality? = null,
    @SerialName("format") val format: String? = null,
    @SerialName("new_description") val newDescription: String? = null,
    @SerialName("display_desc") val displayDescription: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("superscript") val superscript: String? = null,
    @SerialName("codecs") val codecs: List<String> = emptyList(),
    @SerialName("need_login") val needLogin: Boolean? = null,
    @SerialName("need_vip") val needVip: Boolean? = null,
)
