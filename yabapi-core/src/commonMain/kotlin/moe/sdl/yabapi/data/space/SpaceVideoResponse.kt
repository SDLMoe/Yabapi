@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.enums.video.VideoType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SpaceVideoResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SpaceVideoData? = null,
)

@Serializable
public data class SpaceVideoData(
    @SerialName("list") val list: SpaceVideoList? = null,
    @SerialName("page") val page: SpaceVideoPage? = null,
    @SerialName("episodic_button") val episodicButton: SpaceVideoButton? = null,
)

@Serializable
public data class SpaceVideoList(
    @SerialName("tlist") val typeList: Map<String, SpaceTypeNode> = emptyMap(),
    @SerialName("vlist") val videoList: List<SpaceVideoNode> = emptyList(),
)

@Serializable
public data class SpaceTypeNode(
    @SerialName("count") val count: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("tid") val tid: Int? = null,
)

@Serializable
public data class SpaceVideoNode(
    @SerialName("aid") val aid: Int? = null,
    @SerialName("author") val author: String? = null,
    @SerialName("bvid") val bvid: String? = null,
    @SerialName("comment") val comment: Int? = null,
    @SerialName("copyright") val copyright: String? = null,
    @SerialName("created") val created: Int? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("hide_click") val hideClick: Boolean? = null,
    @SerialName("is_pay") val isPay: Boolean? = null,
    @SerialName("is_union_video") val isUnionVideo: Boolean? = null,
    @SerialName("is_steins_gate") val isInteractive: Boolean? = null, // 互動視頻
    @SerialName("is_live_playback") val isLivePlayback: Boolean? = null, // 直播回放
    @SerialName("length") val length: String? = null, // MM:SS
    @SerialName("mid") val mid: Int? = null,
    @SerialName("pic") val cover: String? = null,
    @SerialName("play") val play: Int? = null,
    @SerialName("review") val review: Int? = null,
    @SerialName("subtitle") val subtitle: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("typeid") val type: VideoType? = null,
    @SerialName("video_review") val danmaku: Int? = null,
)

@Serializable
public data class SpaceVideoPage(
    @SerialName("count") val total: Int? = null,
    @SerialName("pn") val current: Int? = null,
    @SerialName("ps") val pageSize: Int? = null,
)
