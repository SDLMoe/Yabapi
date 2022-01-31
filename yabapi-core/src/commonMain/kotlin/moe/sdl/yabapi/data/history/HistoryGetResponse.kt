@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.live.LiveRoomStatus
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class HistoryGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: HistoryData? = null,
)

@Serializable
public data class HistoryData(
    @SerialName("cursor") val cursor: HistoryCursor? = null,
    @SerialName("tab") val tab: List<HistoryTab> = emptyList(),
    @SerialName("list") val list: List<HistoryNode> = emptyList(),
)

@Serializable
public data class HistoryCursor(
    @SerialName("max") val max: Int? = null,
    @SerialName("view_at") val viewAt: Long? = null,
    @SerialName("business") val business: String? = null,
    @SerialName("ps") val ps: Int? = null,
)

@Serializable
public data class HistoryTab(
    @SerialName("type") val type: String? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class HistoryNode(
    @SerialName("title") val title: String? = null,
    @SerialName("long_title") val longTitle: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("covers") val covers: List<String> = emptyList(),
    @SerialName("uri") val uri: String? = null,
    @SerialName("history") val ids: HistoryIds? = null,
    @SerialName("videos") val videos: Int? = null,
    @SerialName("author_name") val authorName: String? = null,
    @SerialName("author_face") val authorAvatar: String? = null,
    @SerialName("author_mid") val authorMid: Int? = null,
    @SerialName("view_at") val viewAt: Long? = null,
    @SerialName("progress") val progress: Int? = null,
    @SerialName("badge") val badge: String? = null,
    @SerialName("show_title") val showTitle: String? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("current") val current: String? = null,
    @SerialName("total") val total: Int? = null,
    @SerialName("new_desc") val newDesc: String? = null,
    @SerialName("is_finish") val isFinish: Boolean? = null,
    @SerialName("is_fav") val isCollected: Boolean? = null,
    @SerialName("kid") val keyId: Int? = null,
    @SerialName("tag_name") val tagName: String? = null,
    @SerialName("live_status") val liveStatus: LiveRoomStatus? = null,
)

@Serializable
public data class HistoryIds(
    @SerialName("oid") val oid: Int? = null,
    @SerialName("epid") val ep: Int? = null,
    @SerialName("bvid") val bv: String? = null,
    @SerialName("page") val page: Int? = null,
    @SerialName("cid") val cid: Int? = null,
    @SerialName("part") val part: String? = null,
    @SerialName("business") val business: String? = null,
    @SerialName("dt") val platform: HistoryPlatform? = null,
)
