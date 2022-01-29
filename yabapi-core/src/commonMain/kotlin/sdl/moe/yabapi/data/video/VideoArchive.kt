package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.video.VideoCopyright.UNKNOWN
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class VideoArchive(
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val partsCount: Int,
    @SerialName("type_id") val videoType: VideoType,
    @SerialName("type_name") val videoTypeName: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright = UNKNOWN,
    @SerialName("pic") val cover: String,
    @SerialName("title") val title: String,
    @SerialName("pubdate") val releaseDate: Long,
    @SerialName("ctime") val uploadDate: Long,
    @SerialName("desc") val desc: String,
    @SerialName("desc_v2") val descriptionV2: List<DescriptionV2> = emptyList(),
    @SerialName("state") val state: VideoState,
    @SerialName("duration") val duration: Long,
    @SerialName("forward") val forward: Int? = null,
    @SerialName("mission_id") val missionId: Int? = null,
    @SerialName("redirect_url") val redirectUrl: String? = null,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("author") val owner: VideoOwner,
    @SerialName("stat") val stat: UgcSeasonStat,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: String? = null,
    @SerialName("no_cache") val noCache: Boolean? = null,
    @SerialName("subtitle") val subtitle: VideoSubtitle? = null,
    @SerialName("ugc_season") val ugcSeason: UgcSeason? = null,
    @SerialName("staff") val staff: List<VideoStaff>? = null,
    @SerialName("user_garb") val userGrab: UserGarb? = null,
    @SerialName("honor_reply") val honor: VideoHonorData? = null,
)
