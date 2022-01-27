package sdl.moe.yabapi.data.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.OfficialCertify
import sdl.moe.yabapi.data.info.Pendant
import sdl.moe.yabapi.data.info.UserNameplateData
import sdl.moe.yabapi.data.info.UserVip

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
