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
    @SerialName("list") val list: SpaceVideoList,
    @SerialName("page") val page: SpaceVideoPage,
    @SerialName("episodic_button") val episodicButton: SpaceVideoButton? = null,
)

@Serializable
public data class SpaceVideoList(
    @SerialName("tlist") val typeList: Map<String, SpaceTypeNode> = emptyMap(),
    @SerialName("vlist") val videoList: List<SpaceVideoNode> = emptyList(),
)

@Serializable
public data class SpaceTypeNode(
    @SerialName("count") val count: Int,
    @SerialName("name") val name: String,
    @SerialName("tid") val tid: Int,
)

@Serializable
public data class SpaceVideoNode(
    @SerialName("aid") val aid: Int,
    @SerialName("author") val author: String,
    @SerialName("bvid") val bvid: String,
    @SerialName("comment") val comment: Int,
    @SerialName("copyright") val copyright: String,
    @SerialName("created") val created: Int,
    @SerialName("description") val description: String,
    @SerialName("hide_click") val hideClick: Boolean,
    @SerialName("is_pay") val isPay: Boolean,
    @SerialName("is_union_video") val isUnionVideo: Boolean,
    @SerialName("is_steins_gate") val isInteractive: Boolean, // 互動視頻
    @SerialName("is_live_playback") val isLivePlayback: Boolean, // 直播回放
    @SerialName("length") val length: String, // MM:SS
    @SerialName("mid") val mid: Int,
    @SerialName("pic") val cover: String,
    @SerialName("play") val play: Int,
    @SerialName("review") val review: Int,
    @SerialName("subtitle") val subtitle: String,
    @SerialName("title") val title: String,
    @SerialName("typeid") val type: VideoType,
    @SerialName("video_review") val danmaku: Int,
)

@Serializable
public data class SpaceVideoPage(
    @SerialName("count") val total: Int,
    @SerialName("pn") val current: Int,
    @SerialName("ps") val pageSize: Int,
)
