@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class BangumiInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("result") val data: BangumiInfo? = null,
)

@Serializable
public data class BangumiInfo(
    @SerialName("media") val media: BangumiMedia? = null,
    @SerialName("review") val review: BangumiReview? = null,
)

@Serializable
public data class BangumiMedia(
    @SerialName("areas") val areas: List<BangumiArea> = emptyList(),
    @SerialName("cover") val cover: String? = null,
    @SerialName("media_id") val mediaId: Int? = null,
    @SerialName("new_ep") val latestEpisode: SimpleBangumiEpisode? = null,
    @SerialName("rating") val rating: BangumiRating? = null,
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("share_url") val shareUrl: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("type_name") val typeName: String? = null,
)

@Serializable
public data class BangumiArea(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
)

@Serializable
public data class BangumiReview(
    @SerialName("is_coin") val isCoined: Boolean? = null,
    @SerialName("is_open") val isOpen: Boolean? = null,
)
