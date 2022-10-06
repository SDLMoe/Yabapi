package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.video.VideoCopyright
import moe.sdl.yabapi.data.video.VideoDimension
import moe.sdl.yabapi.data.video.VideoOwner
import moe.sdl.yabapi.data.video.VideoRights
import moe.sdl.yabapi.data.video.VideoStat
import moe.sdl.yabapi.data.video.VideoState
import moe.sdl.yabapi.enums.video.Unknown
import moe.sdl.yabapi.enums.video.VideoType

@Serializable
public data class SpaceChannel(
    @SerialName("cid") val cid: Long? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("intro") val intro: String? = null,
    @SerialName("mtime") val mtime: String? = null,
    @SerialName("count") val count: Int? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("is_live_playback") val isLivePlayback: Boolean? = null,
    @SerialName("archives") val archives: List<ChannelVideoArchive> = emptyList(),
)

@Serializable
public data class ChannelVideoArchive(
    @SerialName("aid") val aid: Long? = null,
    @SerialName("videos") val parts: Int? = null,
    @SerialName("tid") val type: VideoType = Unknown,
    @SerialName("tname") val typeName: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright? = null,
    @SerialName("pic") val pic: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("state") val state: VideoState? = null,
    @SerialName("duration") val duration: Int? = null,
    @SerialName("rights") val rights: VideoRights? = null,
    @SerialName("owner") val owner: VideoOwner? = null,
    @SerialName("stat") val stat: VideoStat? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("cid") val cid: Long? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("inter_video") val interVideo: Boolean? = null,
    @SerialName("is_live_playback") val isLivePlayback: Boolean? = null,
)
