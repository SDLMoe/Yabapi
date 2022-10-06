package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.bangumi.BangumiType
import moe.sdl.yabapi.data.video.VideoOwner

@Serializable
public data class FavoritesMediaResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: FavoritesMediaData? = null,
)

@Serializable
public data class FavoritesMediaData(
    @SerialName("info") val info: FavoritesInfoData,
    @SerialName("medias") val medias: List<FavoritesMediaNode> = emptyList(),
    @SerialName("has_more") val hasMore: Boolean? = null,
)

@Serializable
public data class FavoritesMediaNode(
    @SerialName("id") val id: Long? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("intro") val intro: String? = null,
    @SerialName("page") val page: Long? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("upper") val upper: VideoOwner? = null,
    @SerialName("attr") val attr: Int? = null,
    @SerialName("cnt_info") val cntInfo: VideoStat? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("ctime") val createdTime: Long? = null,
    @SerialName("pubtime") val releaseTime: Long? = null,
    @SerialName("fav_time") val favoriteTime: Long? = null,
    @SerialName("bv_id") val bvId: String? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("season") val season: JsonObject? = null,
    @SerialName("ogv") val ogv: OgvInfo? = null,
    @SerialName("ugc") val ugc: UgcInfo? = null,
) {
    @Serializable
    public data class VideoStat(
        @SerialName("collect") val collect: Int? = null,
        @SerialName("play") val play: Int? = null,
        @SerialName("danmaku") val danmaku: Int? = null,
    )

    @Serializable
    public data class OgvInfo(
        @SerialName("type_name") val typeName: String? = null,
        @SerialName("type_id") val typeId: BangumiType? = null,
        @SerialName("season_id") val seasonId: Long? = null,
    )

    @Serializable
    public data class UgcInfo(
        @SerialName("first_cid") val firstCid: Long? = null,
    )
}
