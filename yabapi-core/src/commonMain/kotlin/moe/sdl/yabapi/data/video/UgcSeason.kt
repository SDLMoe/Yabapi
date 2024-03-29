package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.enums.video.VideoType

@Serializable
public data class UgcSeason(
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("mid") val mid: Long? = null,
    @SerialName("intro") val intro: String? = null,
    @SerialName("sign_state") val signState: Int? = null,
    @SerialName("attribute") val attribute: Int? = null,
    @SerialName("sections") val sections: List<UgcSeasonSections> = emptyList(),
    @SerialName("stat") val stat: UgcSeasonStat? = null,
    @SerialName("ep_count") val episodeCount: Int? = null,
    @SerialName("season_type") val seasonType: Int? = null,
)

@Serializable
public data class UgcSeasonSections(
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("type") val type: VideoType? = null,
    @SerialName("episodes") val episodes: List<UgcSeasonEpisode> = emptyList(),
)

@Serializable
public data class UgcSeasonEpisode(
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("section_id") val sectionId: Long? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("aid") val aid: Long? = null,
    @SerialName("cid") val cid: Long? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("type") val type: VideoType? = null,
    @SerialName("attribute") val attribute: Int? = null,
    @SerialName("arc") val archive: VideoArchive? = null,
    @SerialName("page") val page: VideoPart? = null,
    @SerialName("bvid") val bvId: String? = null,
)
