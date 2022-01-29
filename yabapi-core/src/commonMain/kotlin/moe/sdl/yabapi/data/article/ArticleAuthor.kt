package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.data.info.Pendant
import moe.sdl.yabapi.data.info.UserNameplateData
import moe.sdl.yabapi.data.info.UserVip

@Serializable
public data class ArticleAuthor(
    @SerialName("mid") val mid: Int,
    @SerialName("name") val name: String,
    @SerialName("face") val face: String,
    @SerialName("pendant") val pendant: Pendant,
    @SerialName("official_verify") val officialCertify: OfficialCertify,
    @SerialName("nameplate") val nameplate: UserNameplateData,
    @SerialName("vip") val vip: UserVip,
)
