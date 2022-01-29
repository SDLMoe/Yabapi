package sdl.moe.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.video.VideoCopyright
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.data.video.VideoOwner
import sdl.moe.yabapi.data.video.VideoRights
import sdl.moe.yabapi.data.video.VideoStat
import sdl.moe.yabapi.data.video.VideoState
import sdl.moe.yabapi.enums.video.Unknown
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class SpaceChannel(
    @SerialName("cid") val cid: Int,
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("intro") val intro: String,
    @SerialName("mtime") val mtime: String,
    @SerialName("count") val count: Int,
    @SerialName("cover") val cover: String,
    @SerialName("is_live_playback") val isLivePlayback: Boolean,
    @SerialName("archives") val archives: List<ChannelVideoArchive> = emptyList(),
)

@Serializable
public data class ChannelVideoArchive(
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val parts: Int,
    @SerialName("tid") val type: VideoType = Unknown,
    @SerialName("tname") val typeName: String,
    @SerialName("copyright") val copyright: VideoCopyright,
    @SerialName("pic") val pic: String,
    @SerialName("title") val title: String,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("desc") val description: String,
    @SerialName("state") val state: VideoState,
    @SerialName("duration") val duration: Int,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("cid") val cid: Int,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("short_link_v2") val shortLinkV2: String,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("bvid") val bvid: String,
    @SerialName("inter_video") val interVideo: Boolean,
    @SerialName("is_live_playback") val isLivePlayback: Boolean,
)
