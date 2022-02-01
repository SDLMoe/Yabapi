package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class FeedLivingResponse(
    @SerialName("code") val code: GeneralCode? = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: FeedLivingData? = null,
)

@Serializable
public data class FeedLivingData(
    @SerialName("results") val results: Int? = null,
    @SerialName("page") val page: String? = null,
    @SerialName("pagesize") val pagesize: String? = null,
    @SerialName("list") val list: List<LivingNode> = emptyList(),
)

@Serializable
public data class LivingNode(
    @SerialName("roomid") val roomId: Int? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val userName: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("pic") val newCover: String? = null, // *hdslb.com/bfs/live/new_room_cover/*
    @SerialName("online") val online: Int? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("parent_area_id") val parentAreaId: Int? = null,
    @SerialName("area_id") val areaId: Int? = null,
)
