@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.longOrNull
import moe.sdl.yabapi.Yabapi.defaultJson
import moe.sdl.yabapi.data.video.VideoInfoGetCode.UNKNOWN
import moe.sdl.yabapi.enums.video.Unknown
import moe.sdl.yabapi.enums.video.VideoType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoInfoGetResponse(
    @SerialName("code") val code: VideoInfoGetCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoInfo? = null,
)

@Serializable
public data class VideoInfo(
    @SerialName("bvid") val bvid: String,
    @SerialName("season_type") val seasonType: Int? = null,
    @SerialName("is_ogv") val isOgv: Boolean? = null, // Occupationally Generated Video
    @SerialName("ogv_info") val ogvInfo: JsonElement? = null,
    @SerialName("rcmd_reason") val recommendReason: String? = null,
    @SerialName("aid") val aid: Int,
    @SerialName("cid") val cid: Int? = null,
    @SerialName("tid") val videoType: VideoType? = Unknown,
    @SerialName("typename") private val _typename: String? = null,
    @SerialName("tname") private val _tname: String? = null,
    @SerialName("videos") val partsCount: Int? = null,
    @SerialName("copyright") val copyright: VideoCopyright = VideoCopyright.UNKNOWN,
    @SerialName("pic") val cover: String,
    @SerialName("title") val title: String,
    @SerialName("create") val createdTime: String? = null,
    @SerialName("pubdate") val releaseDate: Long? = null,
    @SerialName("time") val time: Long? = null,
    @SerialName("ip") val ip: String? = null,
    @SerialName("inter_video") val isInteractive: Boolean? = null,
    @SerialName("resource_type") val resourceType: String? = null,
    @SerialName("ctime") val uploadDate: Long? = null,
    @SerialName("description") private val _description: String? = null,
    @SerialName("desc") private val _desc: String? = null,
    @SerialName("desc_v2") val descriptionV2: List<DescriptionV2> = emptyList(),
    @SerialName("state") val state: VideoState? = null,
    @SerialName("duration") private val _duration: JsonPrimitive,
    @SerialName("order_id") val orderId: Int? = null,
    @SerialName("forward") val forward: Int? = null,
    @SerialName("mission_id") val missionId: Int? = null,
    @SerialName("redirect_url") val redirectUrl: String? = null,
    @SerialName("rights") val rights: VideoRights,
    @SerialName("owner") val owner: VideoOwner? = null,
    @SerialName("stat") val stat: VideoStat? = null,
    @SerialName("coins") val coins: Int? = null,
    @SerialName("play") val play: Int? = null,
    @SerialName("review") val review: Int? = null,
    @SerialName("dynamic") val dynamic: String? = null,
    @SerialName("dimension") val dimension: VideoDimension? = null,
    @SerialName("first_frame") val firstFrame: String? = null,
    @SerialName("short_link") val shortLink: String? = null,
    @SerialName("short_link_v2") val shortLinkV2: String? = null,
    @SerialName("up_from_v2") val isUpFromV2: Int? = null,
    @SerialName("season_id") val seasonId: String? = null,
    @SerialName("no_cache") val noCache: Boolean? = null,
    @SerialName("pages") val parts: List<VideoPart> = emptyList(),
    @SerialName("subtitle") private val _subtitle: JsonElement? = null,
    @SerialName("video_review") val videoReview: Int? = null,
    @SerialName("favorites") val collected: Int? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("author") val authorName: String? = null,
    @SerialName("badgepay") val isPaidBadge: Boolean? = null,
    @SerialName("pts") val rankingPoint: Int? = null,
    @SerialName("label") val label: VideoLabel? = null,
    @SerialName("ugc_season") val ugcSeason: UgcSeason? = null,
    @SerialName("staff") val staff: List<VideoStaff> = emptyList(),
    @SerialName("is_season_display") val isSeasonDisplay: Boolean? = null,
    @SerialName("user_garb") val userGrab: UserGarb? = null,
    @SerialName("honor_reply") val honor: VideoHonorData? = null,
) {

    public fun getSubtitle(json: Json = defaultJson.value): VideoSubtitle? =
        if (_subtitle is JsonObject || _subtitle != null) {
            json.decodeFromJsonElement<VideoSubtitle>(_subtitle)
        } else null

    val typeName: String? by lazy { _typename ?: _tname }

    val description: String by lazy { _desc ?: _description ?: "" }

    val durationLong: Long? by lazy { _duration.longOrNull }

    val durationStr: String? by lazy { _duration.contentOrNull }
}

@Serializable
public data class UserGarb(
    @SerialName("url_image_ani_cut") val urlImageAnimeCut: String,
)

@Serializable
public data class VideoLabel(
    @SerialName("type") val type: Int,
)
