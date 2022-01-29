@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorIntSerializer

@Serializable
public data class LiveRankMedalResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveRankMedalData? = null,
)

@Serializable
public data class LiveRankMedalData(
    @SerialName("list") val user: List<User>,
    @SerialName("own") val self: Self,
) {
    @Serializable
    public data class User(
        @SerialName("content") val content: MedalContent,
        @SerialName("isMaster") val isLiver: Boolean,
        @SerialName("isSelf") val isSelf: Boolean,
        @SerialName("rank") val rank: Int,
        @SerialName("score") val score: Long,
        @SerialName("trend") val trend: Int,
        @SerialName("type") val type: String,
        @SerialName("uface") val avatar: String,
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val userName: String,
    )

    @Serializable
    public data class Self(
        @SerialName("content") val content: MedalContent,
        @SerialName("rank") val rank: String,
        @SerialName("score") val score: Long,
        @SerialName("uid") val uid: Int,
        @SerialName("uname") val username: String,
        @SerialName("uface") val avatar: String,
        @SerialName("isMaster") val isLiver: Boolean,
        @SerialName("type") val type: String,
    )

    @Serializable
    public data class MedalContent(
        @SerialName("level") val level: Int,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("masterRoomId") val masterRoomId: RgbColor,
        @SerialName("medalName") val medalName: String,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("color") val color: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor,
        @SerialName("is_lighted") val isLighted: Boolean,
        @SerialName("guard_level") val guardLevel: Int,
        @SerialName("type") val type: String,
    )
}
