@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.api.getBangumiDetailedBySeason
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SubscribedBangumiGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SubscribedBangumiData? = null,
)

@Serializable
public data class SubscribedBangumiData(
    @SerialName("list") val list: List<SubscribedBangumi> = emptyList(),
    @SerialName("pn") val page: Int,
    @SerialName("ps") val pageSize: Int,
    @SerialName("total") val total: Int,
)

/**
 * 只解析第一層的原始類型, 其他自行通過 [getBangumiDetailedBySeason] 獲取
 */
@Serializable
public data class SubscribedBangumi(
    @SerialName("season_id") val seasonId: Int,
    @SerialName("media_id") val mediaId: Int,
    @SerialName("season_type") val seasonType: Int,
    @SerialName("season_type_name") val seasonTypeName: String,
    @SerialName("title") val title: String,
    @SerialName("cover") val cover: String,
    @SerialName("total_count") val totalCount: Int,
    @SerialName("is_finish") val isFinish: Boolean,
    @SerialName("is_started") val isStarted: Boolean,
    @SerialName("is_play") val isPlay: Boolean,
    @SerialName("badge") val badge: String,
    @SerialName("badge_type") val badgeType: Int,
    @SerialName("rights") val rights: JsonObject,
    @SerialName("stat") val stat: JsonObject,
    @SerialName("new_ep") val newEp: JsonObject,
    @SerialName("rating") val rating: JsonObject? = null,
    @SerialName("square_cover") val squareCover: String,
    @SerialName("season_status") val seasonStatus: Int,
    @SerialName("season_title") val seasonTitle: String,
    @SerialName("badge_ep") val badgeEp: String,
    @SerialName("media_attr") val mediaAttr: Int,
    @SerialName("season_attr") val seasonAttr: Int,
    @SerialName("evaluate") val evaluate: String,
    @SerialName("areas") val areas: JsonArray,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("first_ep") val firstEp: Int,
    @SerialName("release_date_show") val releaseDateShow: String? = null,
    @SerialName("can_watch") val canWatch: Boolean,
    @SerialName("series") val series: JsonObject,
    @SerialName("publish") val publish: JsonObject,
    @SerialName("mode") val mode: Int,
    @SerialName("section") val section: JsonArray,
    @SerialName("url") val url: String,
    @SerialName("badge_info") val badgeInfo: JsonObject,
    @SerialName("renewal_time") val renewalTime: String? = null,
    @SerialName("first_ep_info") val firstEpInfo: JsonObject,
    @SerialName("formal_ep_count") val formalEpCount: Int? = null,
    @SerialName("short_url") val shortUrl: String,
    @SerialName("badge_infos") val badgeInfos: JsonObject? = null,
    @SerialName("season_version") val seasonVersion: String,
    @SerialName("horizontal_cover_16_9") val horizontalCover16ratio9: String? = null,
    @SerialName("horizontal_cover_16_10") val horizontalCover16ratio10: String? = null,
    @SerialName("subtitle_14") val subtitle14: String,
    @SerialName("viewable_crowd_type") val viewableCrowdType: Int,
    @SerialName("producers") val producers: JsonArray? = null,
    @SerialName("follow_status") val followStatus: Int,
    @SerialName("is_new") val isNew: Boolean,
    @SerialName("progress") val progress: String,
    @SerialName("both_follow") val bothFollow: Boolean,
)
