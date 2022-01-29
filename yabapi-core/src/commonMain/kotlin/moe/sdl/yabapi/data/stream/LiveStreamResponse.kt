package moe.sdl.yabapi.data.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.live.LiveRoomStatus

@Serializable
public data class LiveStreamResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveStreamInfo? = null,
) {
    // public fun filterStream()
}

@Serializable
public data class LiveStreamInfo(
    @SerialName("room_id") val roomId: Int,
    @SerialName("short_id") val shortId: Int,
    @SerialName("uid") val uid: Int,
    @SerialName("is_hidden") val isHidden: Boolean,
    @SerialName("is_locked") val isLocked: Boolean,
    @SerialName("is_portrait") val isPortrait: Boolean,
    @SerialName("live_status") val status: LiveRoomStatus,
    @SerialName("hidden_till") val hiddenTill: Long,
    @SerialName("lock_till") val lockTill: Long,
    @SerialName("encrypted") val encrypted: Boolean,
    @SerialName("pwd_verified") val pwdVerified: Boolean,
    @SerialName("live_time") val liveTime: Long,
    @SerialName("room_shield") val roomShield: Int,
    @SerialName("all_special_types") val allSpecialTypes: JsonArray,
    @SerialName("playurl_info") val playUrlInfo: LivePlayInfo? = null,
)

@Serializable
public data class LivePlayInfo(
    @SerialName("conf_json") val _confJson: String,
    @SerialName("playurl") val playUrl: LiveStreamPlayUrl? = null,
) {
    public val confJson: LiveStreamConfigInfo by lazy { getConfJson() }

    public fun getConfJson(json: Json = defaultJson.value): LiveStreamConfigInfo =
        json.decodeFromString(_confJson)
}

@Serializable
public data class LiveStreamConfigInfo(
    @SerialName("cdn_rate") val cdnRate: Int,
    @SerialName("report_interval_sec") val reportIntervalSec: Int,
)

@Serializable
public data class LiveStreamPlayUrl(
    @SerialName("cid") val cid: Int,
    @SerialName("g_qn_desc") val gQnDesc: List<QnDescNode> = emptyList(),
    @SerialName("stream") val stream: List<ProtocolTrack> = emptyList(),
    @SerialName("p2p_data") val p2pData: P2pData,
    @SerialName("dolby_qn") val dolbyQn: JsonElement? = null,
) {
    @Serializable
    public data class QnDescNode(
        @SerialName("qn") val qn: Int,
        @SerialName("desc") val desc: String,
        @SerialName("hdr_desc") val hdrDesc: String,
    )

    @Serializable
    public data class ProtocolTrack(
        @SerialName("protocol_name") val protocolName: String,
        @SerialName("format") val format: List<FormatTrack> = emptyList(),
    )

    @Serializable
    public data class P2pData(
        @SerialName("p2p") val isP2p: Boolean,
        @SerialName("p2p_type") val p2pType: Int,
        @SerialName("m_p2p") val mP2P: Boolean,
        @SerialName("m_servers") val mServers: JsonElement? = null,
    )

    @Serializable
    public data class FormatTrack(
        @SerialName("format_name") val formatName: String,
        @SerialName("codec") val codec: List<CodecTrack> = emptyList(),
    )

    @Serializable
    public data class CodecTrack(
        @SerialName("codec_name") val codecName: String,
        @SerialName("current_qn") val currentQn: Int,
        @SerialName("accept_qn") val acceptQn: List<Int> = emptyList(),
        @SerialName("base_url") val baseUrl: String,
        @SerialName("url_info") val urlInfo: List<UrlInfo> = emptyList(),
        @SerialName("hdr_qn") val hdrQn: JsonElement? = null,
    ) {
        val playUrl: String? by lazy {
            val url = urlInfo.firstOrNull()
            url?.run {
                host + baseUrl + extra
            }
        }
    }

    @Serializable
    public data class UrlInfo(
        @SerialName("host") val host: String,
        @SerialName("extra") val extra: String,
        @SerialName("stream_ttl") val ttl: Int,
    )
}
