@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveRankResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveRank? = null,
)

@Serializable
public data class LiveRank(
    @SerialName("list") val list: List<User>,
    @SerialName("own") val self: Self,
    @SerialName("ratioDesc") val ratioDesc: String,
) {
    @Serializable
    public data class User(
        @SerialName("uid") val uid: Int,
        @SerialName("rank") val rank: Int,
        @SerialName("isSelf") val isSelf: Boolean,
        @SerialName("score") val score: Long,
        @SerialName("uname") val username: String,
        @SerialName("uface") val avatar: String,
        @SerialName("isMaster") val isLiver: Boolean,
        @SerialName("roomid") val roomId: Int? = null,
        @SerialName("liveStatus") val status: LiveRoomStatus = LiveRoomStatus.UNKNOWN,
        @SerialName("content") val content: RankInfo,
        @SerialName("type") val type: String,
        @SerialName("trend") val trend: Int,
    )

    @Serializable
    public data class Self(
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val uname: String,
        @SerialName("rank") val rank: String,
        @SerialName("score") val score: String,
        @SerialName("type") val type: String,
        @SerialName("content") val content: RankInfo,
        @SerialName("uface") val avatar: String,
        @SerialName("isMaster") val isLiver: Boolean,
        @SerialName("roomid") val roomId: Int? = null,
        @SerialName("liveStatus") val status: LiveRoomStatus = LiveRoomStatus.UNKNOWN,
    )

    @Serializable
    public data class RankInfo(
        @SerialName("type") val type: String,
        @SerialName("level") val level: Int? = null,
        @SerialName("value") val value: String? = null,
    )
}
