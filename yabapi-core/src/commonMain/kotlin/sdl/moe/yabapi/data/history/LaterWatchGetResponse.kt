package sdl.moe.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.video.VideoCopyright
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.data.video.VideoOwner
import sdl.moe.yabapi.data.video.VideoPart
import sdl.moe.yabapi.data.video.VideoRights
import sdl.moe.yabapi.data.video.VideoStat
import sdl.moe.yabapi.data.video.VideoState
import sdl.moe.yabapi.enums.video.Unknown
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class LaterWatchGetResponse(
    @SerialName("code") val code: GeneralCode? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LaterWatchData? = null,
)

@Serializable
public data class LaterWatchData(
    @SerialName("count") val count: Int,
    @SerialName("list") val list: List<LaterWatchNode> = emptyList(),
)

@Serializable
public data class LaterWatchNode(
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val videos: Int,
    @SerialName("tid") val tid: VideoType = Unknown,
    @SerialName("tname") val tname: String,
    @SerialName("copyright") val copyright: VideoCopyright,
    @SerialName("pic") val pic: String,
    @SerialName("title") val title: String,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("desc") val desc: String,
    @SerialName("state") val state: VideoState,
    @SerialName("duration") val duration: Long,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("short_link_v2") val shortLinkV2: String,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("page") val part: VideoPart,
    @SerialName("count") val count: Int,
    @SerialName("cid") val cid: Int,
    @SerialName("progress") val progress: Int,
    @SerialName("add_at") val addAt: Long,
    @SerialName("bvid") val bvid: String,
    @SerialName("uri") val uri: String,
    @SerialName("viewed") val viewed: Boolean,
)
