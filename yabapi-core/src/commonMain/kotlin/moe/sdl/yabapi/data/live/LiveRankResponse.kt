@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveRankResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveRank? = null,
)

@Serializable
public data class LiveRank(
    @SerialName("list") val list: List<User> = emptyList(),
    @SerialName("own") val self: Self? = null,
    @SerialName("ratioDesc") val ratioDesc: String? = null,
) {
    @Serializable
    public data class User(
        @SerialName("uid") val uid: Long? = null,
        @SerialName("rank") val rank: Int? = null,
        @SerialName("isSelf") val isSelf: Boolean? = null,
        @SerialName("score") val score: Long? = null,
        @SerialName("uname") val username: String? = null,
        @SerialName("uface") val avatar: String? = null,
        @SerialName("isMaster") val isLiver: Boolean? = null,
        @SerialName("roomid") val roomId: Long? = null,
        @SerialName("liveStatus") val status: LiveRoomStatus = LiveRoomStatus.UNKNOWN,
        @SerialName("content") val content: RankInfo? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("trend") val trend: Int? = null,
    )

    @Serializable
    public data class Self(
        @SerialName("uid") val uid: Long? = null,
        @SerialName("uname") val uname: String? = null,
        @SerialName("rank") val rank: String? = null,
        @SerialName("score") val score: String? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("content") val content: RankInfo? = null,
        @SerialName("uface") val avatar: String? = null,
        @SerialName("isMaster") val isLiver: Boolean? = null,
        @SerialName("roomid") val roomId: Long? = null,
        @SerialName("liveStatus") val status: LiveRoomStatus = LiveRoomStatus.UNKNOWN,
    )

    @Serializable
    public data class RankInfo(
        @SerialName("type") val type: String? = null,
        @SerialName("level") val level: Int? = null,
        @SerialName("value") val value: String? = null,
    )
}
