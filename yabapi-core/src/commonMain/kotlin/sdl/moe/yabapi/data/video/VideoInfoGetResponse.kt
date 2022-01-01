// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import sdl.moe.yabapi.data.video.VideoInfoGetCode.UNKNOWN
import sdl.moe.yabapi.enums.VideoType
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoInfoGetResponse(
    @SerialName("code") val code: VideoInfoGetCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoInfo? = null,
)

@Serializable
public enum class VideoInfoGetCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("-404")
    NOT_FOUND,

    @SerialName("62002")
    VIDEO_INVISIBLE;
}

@Serializable
public data class VideoInfo(
    @SerialName("bvid") val bvid: String,
    @SerialName("aid") val aid: Int,
    @SerialName("videos") val partsCount: Int,
    @SerialName("tid") val videoType: VideoType,
    @SerialName("tname") val videoTypeName: String? = null,
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
    @SerialName("owner") val owner: VideoOwner,
    @SerialName("stat") val stat: VideoStat,
    @SerialName("dynamic") val dynamic: String,
    @SerialName("cid") val cid: Int,
    @SerialName("dimension") val dimension: VideoDimension,
    @SerialName("season_id") val seasonId: String? = null,
    @SerialName("no_cache") val noCache: Boolean,
    @SerialName("pages") val parts: List<VideoPart>,
    @SerialName("subtitle") val subtitle: VideoSubtitle,
    @SerialName("ugc_season") val ugcSeason: JsonObject? = null, // TODO: Support Ugc Season
    @SerialName("staff") val staff: List<VideoStaff>? = null,
    @SerialName("user_garb") val userGrab: UserGarb,
    @SerialName("honor_reply") val honor: VideoHonorData,
)

@Serializable
public data class UserGarb(
    @SerialName("url_image_ani_cut") val urlImageAnimeCut: String,
)
