@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.video.VideoCopyright
import moe.sdl.yabapi.data.video.VideoDimension
import moe.sdl.yabapi.data.video.VideoOwner
import moe.sdl.yabapi.data.video.VideoRights
import moe.sdl.yabapi.data.video.VideoStat
import moe.sdl.yabapi.enums.video.Unknown
import moe.sdl.yabapi.enums.video.VideoType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class PinnedVideoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: PinnedVideo? = null,
)

@Serializable
public data class MasterpieceGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<PinnedVideo> = emptyList(),
)

@Serializable
public data class PinnedVideo(
    @SerialName("aid") val aid: Long? = null,
    @SerialName("videos") val videos: Int? = null,
    @SerialName("tid") val tid: VideoType = Unknown,
    @SerialName("tname") val typeName: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright = VideoCopyright.UNKNOWN,
    @SerialName("pic") val pic: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("state") val state: Int? = null,
    @SerialName("duration") val duration: Int? = null, // s
    @SerialName("mission_id") val missionId: Long? = null,
    @SerialName("rights") val rights: VideoRights? = null,
    @SerialName("owner") val owner: VideoOwner? = null,
    @SerialName("stat") val stat: VideoStat? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("cid") val cid: Long? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("bvid") val bvId: String? = null,
    @SerialName("reason") val reason: String? = null,
    @SerialName("inter_video") val interVideo: Boolean? = null,
)
