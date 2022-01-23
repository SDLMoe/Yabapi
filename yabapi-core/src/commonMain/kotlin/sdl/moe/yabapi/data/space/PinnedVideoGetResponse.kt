@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.video.VideoDimension
import sdl.moe.yabapi.data.video.VideoOwner
import sdl.moe.yabapi.data.video.VideoRights
import sdl.moe.yabapi.data.video.VideoStat
import sdl.moe.yabapi.enums.video.Unknown
import sdl.moe.yabapi.enums.video.VideoType
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val videos: Int,
    @SerialName("tid") val tid: VideoType = Unknown,
    @SerialName("tname") val typeName: String,
    @SerialName("copyright") val copyright: Boolean,
    @SerialName("pic") val pic: String,
    @SerialName("title") val title: String,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("ctime") val createdTime: Long,
    @SerialName("desc") val description: String,
    @SerialName("state") val state: Int,
    @SerialName("duration") val duration: Int, // s
    @SerialName("mission_id") val missionId: Int? = null,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("cid") val cid: Int,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("short_link_v2") val shortLinkV2: String,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("bvid") val bvId: String,
    @SerialName("reason") val reason: String,
    @SerialName("inter_video") val interVideo: Boolean,
)
