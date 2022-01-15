// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.enums.video.VideoType

@Serializable
public data class UgcSeason(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("cover") val cover: String,
    @SerialName("mid") val mid: Int,
    @SerialName("intro") val intro: String,
    @SerialName("sign_state") val signState: Int,
    @SerialName("attribute") val attribute: Int,
    @SerialName("sections") val sections: List<UgcSeasonSections>,
    @SerialName("stat") val stat: UgcSeasonStat,
    @SerialName("ep_count") val episodeCount: Int,
    @SerialName("season_type") val seasonType: Int,
)

@Serializable
public data class UgcSeasonSections(
    @SerialName("season_id") val seasonId: Int,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("type") val type: VideoType,
    @SerialName("episodes") val episodes: List<UgcSeasonEpisode> = emptyList(),
)

@Serializable
public data class UgcSeasonEpisode(
    @SerialName("season_id") val seasonId: Int,
    @SerialName("section_id") val sectionId: Int,
    @SerialName("id") val id: Int,
    @SerialName("aid") val aid: Int,
    @SerialName("cid") val cid: Int,
    @SerialName("title") val title: String,
    @SerialName("type") val type: VideoType? = null,
    @SerialName("attribute") val attribute: Int,
    @SerialName("arc") val archive: VideoArchive,
    @SerialName("page") val page: VideoPart,
    @SerialName("bvid") val bvId: String,
)

@Serializable
public data class VideoArchive(
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val partsCount: Int,
    @SerialName("type_id") val videoType: VideoType,
    @SerialName("type_name") val videoTypeName: String? = null,
    @SerialName("copyright") val copyright: VideoCopyright = VideoCopyright.UNKNOWN,
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
