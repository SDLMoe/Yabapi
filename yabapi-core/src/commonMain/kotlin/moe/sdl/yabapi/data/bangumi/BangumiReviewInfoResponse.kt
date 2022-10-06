package moe.sdl.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class BangumiReviewInfoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("result") val result: BangumiReviewInfo? = null,
)

@Serializable
public data class BangumiReviewInfo(
    @SerialName("media") val media: BangumiMediaReview? = null,
)

@Serializable
public data class BangumiMediaReview(
    @SerialName("areas") val areas: List<BangumiArea>? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("media_id") val mediaId: Long? = null,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode? = null,
    @SerialName("rating") val rating: BangumiRating? = null,
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("share_url") val shareUrl: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("type_name") val typeName: String? = null,
)
