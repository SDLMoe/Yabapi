package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.video.VideoCopyright.UNKNOWN
import moe.sdl.yabapi.enums.video.VideoType

@Serializable
public data class VideoArchive(
    @SerialName("aid") val aid: Long? = null,
    @SerialName("videos") val partsCount: Int? = null,
    @SerialName("type_id") val videoType: VideoType? = null,
    @SerialName("type_name") val videoTypeName: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright = UNKNOWN,
    @SerialName("pic") val cover: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("ctime") val uploadDate: Long? = null,
    @SerialName("desc") val desc: String? = null,
    @SerialName("desc_v2") val descriptionV2: List<DescriptionV2> = emptyList(),
    @SerialName("state") val state: VideoState? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("forward") val forward: Int? = null,
    @SerialName("mission_id") val missionId: Long? = null,
    @SerialName("redirect_url") val redirectUrl: String? = null,
    @SerialName("rights") val rights: VideoRights? = null,
    @SerialName("author") val owner: VideoOwner? = null,
    @SerialName("stat") val stat: UgcSeasonStat? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("season_id") val seasonId: String? = null,
    @SerialName("no_cache") val noCache: Boolean? = null,
    @SerialName("subtitle") val subtitle: VideoSubtitle? = null,
    @SerialName("ugc_season") val ugcSeason: UgcSeason? = null,
    @SerialName("staff") val staff: List<VideoStaff> = emptyList(),
    @SerialName("user_garb") val userGrab: UserGarb? = null,
    @SerialName("honor_reply") val honor: VideoHonorData? = null,
)
