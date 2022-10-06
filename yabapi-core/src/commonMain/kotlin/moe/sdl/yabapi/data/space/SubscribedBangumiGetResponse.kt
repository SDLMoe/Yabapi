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
    @SerialName("pn") val page: Int? = null,
    @SerialName("ps") val pageSize: Int? = null,
    @SerialName("total") val total: Int? = null,
)

/**
 * 只解析第一層的原始類型, 其他自行通過 [getBangumiDetailedBySeason] 獲取
 */
@Serializable
public data class SubscribedBangumi(
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("media_id") val mediaId: Long? = null,
    @SerialName("season_type") val seasonType: Int? = null,
    @SerialName("season_type_name") val seasonTypeName: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("total_count") val totalCount: Int? = null,
    @SerialName("is_finish") val isFinish: Boolean? = null,
    @SerialName("is_started") val isStarted: Boolean? = null,
    @SerialName("is_play") val isPlay: Boolean? = null,
    @SerialName("badge") val badge: String? = null,
    @SerialName("badge_type") val badgeType: Int? = null,
    @SerialName("rights") val rights: JsonObject? = null,
    @SerialName("stat") val stat: JsonObject? = null,
    @SerialName("new_ep") val newEp: JsonObject? = null,
    @SerialName("rating") val rating: JsonObject? = null,
    @SerialName("square_cover") val squareCover: String? = null,
    @SerialName("season_status") val seasonStatus: Int? = null,
    @SerialName("season_title") val seasonTitle: String? = null,
    @SerialName("badge_ep") val badgeEp: String? = null,
    @SerialName("media_attr") val mediaAttr: Int? = null,
    @SerialName("season_attr") val seasonAttr: Int? = null,
    @SerialName("evaluate") val evaluate: String? = null,
    @SerialName("areas") val areas: JsonArray? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("first_ep") val firstEp: Int? = null,
    @SerialName("release_date_show") val releaseDateShow: String? = null,
    @SerialName("can_watch") val canWatch: Boolean? = null,
    @SerialName("series") val series: JsonObject? = null,
    @SerialName("publish") val publish: JsonObject? = null,
    @SerialName("mode") val mode: Int? = null,
    @SerialName("section") val section: JsonArray? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("badge_info") val badgeInfo: JsonObject? = null,
    @SerialName("renewal_time") val renewalTime: String? = null,
    @SerialName("first_ep_info") val firstEpInfo: JsonObject? = null,
    @SerialName("formal_ep_count") val formalEpCount: Int? = null,
    @SerialName("short_url") val shortUrl: String? = null,
    @SerialName("badge_infos") val badgeInfos: JsonObject? = null,
    @SerialName("season_version") val seasonVersion: String? = null,
    @SerialName("horizontal_cover_16_9") val horizontalCover16ratio9: String? = null,
    @SerialName("horizontal_cover_16_10") val horizontalCover16ratio10: String? = null,
    @SerialName("subtitle_14") val subtitle14: String? = null,
    @SerialName("viewable_crowd_type") val viewableCrowdType: Int? = null,
    @SerialName("producers") val producers: JsonArray? = null,
    @SerialName("follow_status") val followStatus: Int? = null,
    @SerialName("is_new") val isNew: Boolean? = null,
    @SerialName("progress") val progress: String? = null,
    @SerialName("both_follow") val bothFollow: Boolean? = null,
)
