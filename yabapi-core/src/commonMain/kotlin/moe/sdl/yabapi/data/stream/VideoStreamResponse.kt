package moe.sdl.yabapi.data.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
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
public data class VideoStreamData(
    @SerialName("from") val from: String? = null,
    @SerialName("result") val result: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("quality") val quality: QnQuality? = null,
    @SerialName("format") val format: String? = null,
    @SerialName("timelength") val length: Long? = null, // ms
    @SerialName("accept_format") val acceptFormat: String? = null,
    @SerialName("accept_description") val acceptDescription: List<String> = emptyList(),
    @SerialName("accept_quality") private val _acceptQuality: List<String> = emptyList(),
    @SerialName("video_codecid") val videoCodecId: CodecId? = null,
    @SerialName("seek_param") val seekParam: String? = null,
    @SerialName("seek_type") val seekType: String? = null,
    @SerialName("durl") val urls: List<StreamUrl>? = null,
    @SerialName("dash") val dash: DashStream? = null,
    @SerialName("support_formats") val supportFormats: List<SupportFormat> = emptyList(),
    @SerialName("high_format") val highFormat: JsonElement? = null,
) {
    val acceptQuality: List<QnQuality> = _acceptQuality.fold(mutableListOf()) { acc, s ->
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
)

@Serializable
public data class DashStream(
    @SerialName("duration") val duration: Int? = null,
    @SerialName("minBufferTime") val minBufferTime: Double? = null,
    @SerialName("min_buffer_time") private val _minBufferTime: Double? = null,
    @SerialName("video") val videos: List<DashTrack> = emptyList(),
    @SerialName("audio") val audios: List<DashTrack> = emptyList(),
    @SerialName("dolby") val dolby: List<DashTrack> = emptyList(),
)

@Serializable
public data class DashTrack(
    @SerialName("id") val id: QnQuality? = null,
    @SerialName("baseUrl") val baseUrl: String? = null,
    @SerialName("base_url") private val _baseUrl: String? = null,
    @SerialName("backupUrl") val backupUrl: List<String> = emptyList(),
    @SerialName("backup_url") private val _backupUrl: List<String> = emptyList(),
    @SerialName("bandwidth") val bandwidth: Int? = null,
    @SerialName("mimeType") val mimeType: String? = null,
    @SerialName("mime_type") private val _mimeType: String? = null,
    @SerialName("codecs") val codecs: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("frameRate") val frameRate: String? = null,
    @SerialName("frame_rate") private val _frameRate: String? = null,
    @SerialName("sar") val sar: String? = null,
    @SerialName("startWithSap") val startWithSap: Int? = null,
    @SerialName("start_with_sap") private val _startWithSap: Int? = null,
    @SerialName("SegmentBase") val segmentBase: SegmentBase? = null,
    @SerialName("segment_base") private val _segmentBase: JsonObject? = null,
    @SerialName("codecid") val codec: CodecId = CodecId.UNKNOWN,
)

@Serializable
public enum class CodecId {
    UNKNOWN,

    @SerialName("7")
    AVC,

    @SerialName("12")
    HEVC,

    @SerialName("0")
    AUDIO,
}

@Serializable
public data class SegmentBase(
    @SerialName("Initialization") val initialization: String? = null,
    @SerialName("indexRange") val indexRange: String? = null,
)

@Serializable
public data class SupportFormat(
    @SerialName("quality") val quality: QnQuality? = null,
    @SerialName("format") val format: String? = null,
    @SerialName("new_description") val newDescription: String? = null,
    @SerialName("display_desc") val description: String? = null,
    @SerialName("superscript") val superscript: String? = null,
    @SerialName("codecs") val codecs: List<String> = emptyList(),
)
