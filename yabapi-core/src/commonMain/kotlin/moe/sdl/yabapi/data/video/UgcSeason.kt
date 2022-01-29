package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.enums.video.VideoType

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
