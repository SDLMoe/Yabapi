package sdl.moe.yabapi.data.ranking

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.data.video.VideoInfo

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
