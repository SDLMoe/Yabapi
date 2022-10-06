package moe.sdl.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.video.VideoCopyright
import moe.sdl.yabapi.data.video.VideoDimension
import moe.sdl.yabapi.data.video.VideoOwner
import moe.sdl.yabapi.data.video.VideoPart
import moe.sdl.yabapi.data.video.VideoRights
import moe.sdl.yabapi.data.video.VideoStat
import moe.sdl.yabapi.data.video.VideoState
import moe.sdl.yabapi.enums.video.Unknown
import moe.sdl.yabapi.enums.video.VideoType

@Serializable
public data class LaterWatchGetResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LaterWatchData? = null,
)

@Serializable
public data class LaterWatchData(
    @SerialName("count") val count: Int? = null,
    @SerialName("list") val list: List<LaterWatchNode> = emptyList(),
)

@Serializable
public data class LaterWatchNode(
    @SerialName("aid") val aid: Long? = null,
    @SerialName("videos") val videos: Long? = null,
    @SerialName("tid") val tid: VideoType = Unknown,
    @SerialName("tname") val tname: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("desc") val desc: String? = null,
    @SerialName("state") val state: VideoState? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("rights") val rights: VideoRights? = null,
    @SerialName("owner") val owner: VideoOwner? = null,
    @SerialName("stat") val stat: VideoStat? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("page") val part: VideoPart? = null,
    @SerialName("count") val count: Int? = null,
    @SerialName("cid") val cid: Long? = null,
    @SerialName("progress") val progress: Int? = null,
    @SerialName("add_at") val addAt: Long? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("uri") val uri: String? = null,
    @SerialName("viewed") val viewed: Boolean? = null,
)
