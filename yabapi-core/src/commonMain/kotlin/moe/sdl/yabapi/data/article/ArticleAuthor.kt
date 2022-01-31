package moe.sdl.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.info.OfficialCertify
import moe.sdl.yabapi.data.info.Pendant
import moe.sdl.yabapi.data.info.UserNameplateData
import moe.sdl.yabapi.data.info.UserVip

@Serializable
public data class ArticleAuthor(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("face") val face: String? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("official_verify") val officialCertify: OfficialCertify? = null,
    @SerialName("nameplate") val nameplate: UserNameplateData? = null,
    @SerialName("vip") val vip: UserVip? = null,
)
