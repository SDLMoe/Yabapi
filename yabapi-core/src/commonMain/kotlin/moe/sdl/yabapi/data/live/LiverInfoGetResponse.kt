package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.Gender
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.data.RgbColor
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.serializer.data.RgbColorIntSerializer

@Serializable
public data class LiverInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiverData? = null,
)

@Serializable
public data class LiverData(
    @SerialName("info") val info: LiverInfo? = null,
    @SerialName("exp") val exp: LiverExp? = null,
    @SerialName("follower_num") val followerNum: Int? = null,
    @SerialName("room_id") val roomId: Int? = null, // 短號
    @SerialName("medal_name") val medalName: String? = null,
    @SerialName("glory_count") val gloryCount: Int? = null,
    @SerialName("pendant") val pendant: String? = null,
    @SerialName("link_group_num") val linkGroupNum: Int? = null,
    @SerialName("room_news") val roomNews: RoomNews? = null,
)

@Serializable
public data class LiverInfo(
    @SerialName("uid") val uid: Int? = null,
    @SerialName("uname") val uname: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
    @SerialName("gender") val gender: Gender = Gender.UNKNOWN,
)

@Serializable
public data class LiverExp(
    @SerialName("master_level") val masterLevel: MasterLevel? = null,
) {
    @Serializable
    public data class MasterLevel(
        @SerialName("level") val level: Int? = null,
        @Serializable(RgbColorIntSerializer::class)
        @SerialName("color") val color: RgbColor? = null, // 等級框顏色
        @SerialName("current") val current: List<Int> = emptyList(), // size 2, 1 升級積分, 2 總積分
        @SerialName("next") val next: List<Int> = emptyList(), // size 2, 1 升級積分, 2 總積分
    )
}

@Serializable
public data class RoomNews(
    @SerialName("content") val content: String? = null,
    @SerialName("ctime") val createdTime: String? = null, // yyyy-MM-dd HH:mm:ss
    @SerialName("ctime_text") val createdDate: String? = null,
)
