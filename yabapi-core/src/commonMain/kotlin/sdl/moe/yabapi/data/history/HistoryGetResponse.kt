@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.live.LiveRoomStatus
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class HistoryGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: HistoryData? = null,
)

@Serializable
public data class HistoryData(
    @SerialName("cursor") val cursor: HistoryCursor,
    @SerialName("tab") val tab: List<HistoryTab> = emptyList(),
    @SerialName("list") val list: List<HistoryNode> = emptyList(),
)

@Serializable
public data class HistoryCursor(
    @SerialName("max") val max: Int,
    @SerialName("view_at") val viewAt: Long,
    @SerialName("business") val business: String,
    @SerialName("ps") val ps: Int,
)

@Serializable
public data class HistoryTab(
    @SerialName("type") val type: String,
    @SerialName("name") val name: String,
)

@Serializable
public data class HistoryNode(
    @SerialName("title") val title: String,
    @SerialName("long_title") val longTitle: String,
    @SerialName("cover") val cover: String,
    @SerialName("covers") val covers: List<String> = emptyList(),
    @SerialName("uri") val uri: String,
    @SerialName("history") val ids: HistoryIds,
    @SerialName("videos") val videos: Int,
    @SerialName("author_name") val authorName: String,
    @SerialName("author_face") val authorAvatar: String,
    @SerialName("author_mid") val authorMid: Int,
    @SerialName("view_at") val viewAt: Long,
    @SerialName("progress") val progress: Int,
    @SerialName("badge") val badge: String,
    @SerialName("show_title") val showTitle: String,
    @SerialName("duration") val duration: Long,
    @SerialName("current") val current: String,
    @SerialName("total") val total: Int,
    @SerialName("new_desc") val newDesc: String,
    @SerialName("is_finish") val isFinish: Boolean,
    @SerialName("is_fav") val isCollected: Boolean,
    @SerialName("kid") val keyId: Int,
    @SerialName("tag_name") val tagName: String,
    @SerialName("live_status") val liveStatus: LiveRoomStatus,
)

@Serializable
public data class HistoryIds(
    @SerialName("oid") val oid: Int,
    @SerialName("epid") val ep: Int,
    @SerialName("bvid") val bv: String,
    @SerialName("page") val page: Int,
    @SerialName("cid") val cid: Int,
    @SerialName("part") val part: String,
    @SerialName("business") val business: String,
    @SerialName("dt") val platform: HistoryPlatform,
)
