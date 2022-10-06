@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.serializer.BooleanJsSerializer
import moe.sdl.yabapi.serializer.data.RgbColorIntSerializerNullable

@Serializable
public data class LiveRankMedalResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: LiveRankMedalData? = null,
)

@Serializable
public data class LiveRankMedalData(
    @SerialName("list") val user: List<User> = emptyList(),
    @SerialName("own") val self: Self? = null,
) {
    @Serializable
    public data class User(
        @SerialName("content") val content: MedalContent? = null,
        @SerialName("isMaster") val isLiver: Boolean? = null,
        @SerialName("isSelf") val isSelf: Boolean? = null,
        @SerialName("rank") val rank: Int? = null,
        @SerialName("score") val score: Long? = null,
        @SerialName("trend") val trend: Int? = null,
        @SerialName("type") val type: String? = null,
        @SerialName("uface") val avatar: String? = null,
        @SerialName("uid") val uid: Long? = null,
        @SerialName("uname") val userName: String? = null,
    )

    @Serializable
    public data class Self(
        @SerialName("content") val content: MedalContent? = null,
        @SerialName("rank") val rank: String? = null,
        @SerialName("score") val score: Long? = null,
        @SerialName("uid") val uid: Long? = null,
        @SerialName("uname") val username: String? = null,
        @SerialName("uface") val avatar: String? = null,
        @SerialName("isMaster") val isLiver: Boolean? = null,
        @SerialName("type") val type: String? = null,
    )

    @Serializable
    public data class MedalContent(
        @SerialName("level") val level: Int? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("masterRoomId") val masterRoomId: RgbColor? = null,
        @SerialName("medalName") val medalName: String? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("color") val color: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_start") val medalColorStart: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_end") val medalColorEnd: RgbColor? = null,
        @Serializable(RgbColorIntSerializerNullable::class)
        @SerialName("medal_color_border") val medalColorBorder: RgbColor? = null,
        @SerialName("is_lighted") val isLighted: Boolean? = null,
        @SerialName("guard_level") val guardLevel: Int? = null,
        @SerialName("type") val type: String? = null,
    )
}
