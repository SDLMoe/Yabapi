package sdl.moe.yabapi.data.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class VideoStreamResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoStreamData? = null,
)

@Serializable
public data class VideoStreamData(
    @SerialName("from") val from: String,
    @SerialName("result") val result: String,
    @SerialName("message") val message: String,
    @SerialName("quality") val quality: QnQuality,
    @SerialName("format") val format: String,
    @SerialName("timelength") val length: Long, // ms
    @SerialName("accept_format") val acceptFormat: String,
    @SerialName("accept_description") val acceptDescription: List<String>,
    @SerialName("accept_quality") private val _acceptQuality: List<String>,
    @SerialName("video_codecid") val videoCodecId: CodecId,
    @SerialName("seek_param") val seekParam: String,
    @SerialName("seek_type") val seekType: String,
    @SerialName("durl") val urls: List<StreamUrl>? = null,
    @SerialName("dash") val dash: DashStream? = null,
    @SerialName("support_formats") val supportFormats: List<SupportFormat>,
    @SerialName("high_format") val highFormat: JsonElement? = null,
) {
    val acceptQuality: List<QnQuality> = _acceptQuality.fold(mutableListOf()) { acc, s ->
        acc.add(QnQuality.fromCode(s.toInt()))
        acc
    }
}

@Serializable
public data class StreamUrl(
    @SerialName("order") val order: Int,
    @SerialName("length") val length: Int, // ms
    @SerialName("size") val size: Int, // byte
    @SerialName("ahead") val ahead: String,
    @SerialName("vhead") val vhead: String,
    @SerialName("url") val url: String,
    @SerialName("backup_url") val backups: List<String>,
)

@Serializable
public data class DashStream(
    @SerialName("duration") val duration: Int,
    @SerialName("minBufferTime") val minBufferTime: Double,
    @SerialName("min_buffer_time") private val _minBufferTime: Double,
    @SerialName("video") val videos: List<DashTrack>,
    @SerialName("audio") val audios: List<DashTrack>,
    @SerialName("dolby") val dolby: List<DashTrack>? = null,
)

@Serializable
public data class DashTrack(
    @SerialName("id") val id: QnQuality,
    @SerialName("baseUrl") val baseUrl: String,
    @SerialName("base_url") private val _baseUrl: String,
    @SerialName("backupUrl") val backupUrl: List<String>,
    @SerialName("backup_url") private val _backupUrl: List<String>,
    @SerialName("bandwidth") val bandwidth: Int,
    @SerialName("mimeType") val mimeType: String,
    @SerialName("mime_type") private val _mimeType: String,
    @SerialName("codecs") val codecs: String,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("frameRate") val frameRate: String,
    @SerialName("frame_rate") private val _frameRate: String,
    @SerialName("sar") val sar: String,
    @SerialName("startWithSap") val startWithSap: Int,
    @SerialName("start_with_sap") private val _startWithSap: Int,
    @SerialName("SegmentBase") val segmentBase: SegmentBase,
    @SerialName("segment_base") private val _segmentBase: JsonObject,
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
    @SerialName("Initialization") val initialization: String,
    @SerialName("indexRange") val indexRange: String,
)

@Serializable
public data class SupportFormat(
    @SerialName("quality") val quality: QnQuality,
    @SerialName("format") val format: String,
    @SerialName("new_description") val newDescription: String,
    @SerialName("display_desc") val description: String,
    @SerialName("superscript") val superscript: String,
    @SerialName("codecs") val codecs: List<String>,
)
