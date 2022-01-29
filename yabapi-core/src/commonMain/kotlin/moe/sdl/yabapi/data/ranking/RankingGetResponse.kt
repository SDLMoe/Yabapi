package moe.sdl.yabapi.data.ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.video.VideoInfo

@Serializable
public data class RankingGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<VideoInfo> = emptyList(),
)

@Serializable
public data class LatestVideoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LatestVideoData? = null,
)

@Serializable
public data class LatestVideoData(
    @SerialName("archives") val archives: List<VideoInfo> = emptyList(),
    @SerialName("page") val page: LatestVideoPageInfo,
)

@Serializable
public data class LatestVideoPageInfo(
    @SerialName("count") val count: Int,
    @SerialName("num") val num: Int,
    @SerialName("size") val size: Int,
)
