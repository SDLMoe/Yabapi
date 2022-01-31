package moe.sdl.yabapi.data.stream

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import moe.sdl.yabapi.Yabapi
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
    @SerialName("room_id") val roomId: Int? = null,
    @SerialName("short_id") val shortId: Int? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("is_hidden") val isHidden: Boolean? = null,
    @SerialName("is_locked") val isLocked: Boolean? = null,
    @SerialName("is_portrait") val isPortrait: Boolean? = null,
    @SerialName("live_status") val status: LiveRoomStatus? = null,
    @SerialName("hidden_till") val hiddenTill: Long? = null,
    @SerialName("lock_till") val lockTill: Long? = null,
    @SerialName("encrypted") val encrypted: Boolean? = null,
    @SerialName("pwd_verified") val pwdVerified: Boolean? = null,
    @SerialName("live_time") val liveTime: Long? = null,
    @SerialName("room_shield") val roomShield: Int? = null,
    @SerialName("all_special_types") val allSpecialTypes: JsonArray? = null,
    @SerialName("playurl_info") val playUrlInfo: LivePlayInfo? = null,
)

@Serializable
public data class LivePlayInfo(
    @SerialName("conf_json") val _confJson: String? = null,
    @SerialName("playurl") val playUrl: LiveStreamPlayUrl? = null,
) {
    public val confJson: LiveStreamConfigInfo? by lazy {
        _confJson?.let { Yabapi.defaultJson.value.decodeFromString(it) }
    }
}

@Serializable
public data class LiveStreamConfigInfo(
    @SerialName("cdn_rate") val cdnRate: Int? = null,
    @SerialName("report_interval_sec") val reportIntervalSec: Int? = null,
)

@Serializable
public data class LiveStreamPlayUrl(
    @SerialName("cid") val cid: Int? = null,
    @SerialName("g_qn_desc") val gQnDesc: List<QnDescNode> = emptyList(),
    @SerialName("stream") val stream: List<ProtocolTrack> = emptyList(),
    @SerialName("p2p_data") val p2pData: P2pData? = null,
    @SerialName("dolby_qn") val dolbyQn: JsonElement? = null,
) {
    @Serializable
    public data class QnDescNode(
        @SerialName("qn") val qn: Int? = null,
        @SerialName("desc") val desc: String? = null,
        @SerialName("hdr_desc") val hdrDesc: String? = null,
    )

    @Serializable
    public data class ProtocolTrack(
        @SerialName("protocol_name") val protocolName: String? = null,
        @SerialName("format") val format: List<FormatTrack> = emptyList(),
    )

    @Serializable
    public data class P2pData(
        @SerialName("p2p") val isP2p: Boolean? = null,
        @SerialName("p2p_type") val p2pType: Int? = null,
        @SerialName("m_p2p") val mP2P: Boolean? = null,
        @SerialName("m_servers") val mServers: JsonElement? = null,
    )

    @Serializable
    public data class FormatTrack(
        @SerialName("format_name") val formatName: String? = null,
        @SerialName("codec") val codec: List<CodecTrack> = emptyList(),
    )

    @Serializable
    public data class CodecTrack(
        @SerialName("codec_name") val codecName: String? = null,
        @SerialName("current_qn") val currentQn: Int? = null,
        @SerialName("accept_qn") val acceptQn: List<Int> = emptyList(),
        @SerialName("base_url") val baseUrl: String? = null,
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
        @SerialName("host") val host: String? = null,
        @SerialName("extra") val extra: String? = null,
        @SerialName("stream_ttl") val ttl: Int? = null,
    )
}
