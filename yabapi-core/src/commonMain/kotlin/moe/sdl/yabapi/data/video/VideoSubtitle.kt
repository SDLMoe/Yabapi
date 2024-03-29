@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonNames
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoSubtitle @OptIn(ExperimentalSerializationApi::class) constructor(
    @SerialName("allow_submit") val allowSubmit: Boolean? = null,
    @SerialName("lan") val lan: String? = null,
    @SerialName("lan_doc") val lanDoc: String? = null,
    @JsonNames("list", "subtitles") val list: List<SubtitleTrack> = emptyList(),
)

@Serializable
public data class SubtitleTrack(
    @SerialName("id") val id: ULong? = null,
    @SerialName("lan") val language: String? = null,
    @SerialName("lan_doc") val languageName: String? = null,
    @SerialName("is_lock") val isLocked: Boolean? = null,
    @SerialName("author_mid") val authorMid: Long? = null,
    @SerialName("subtitle_url") val subtitleUrl: String? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("id_str") val idStr: String? = null,
    @SerialName("author") val author: SubtitleAuthor? = null,
)

@Serializable
public data class SubtitleAuthor(
    @SerialName("mid") val mid: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("birthday") val birthday: Int? = null,
    @SerialName("is_fake_account") val isFakeAccount: Boolean? = null,
    @SerialName("is_deleted") val isDeleted: Boolean? = null,
    @SerialName("in_reg_audit") val inRegAudit: Boolean? = null,
    @SerialName("is_senior_member") val isSeniorMember: Boolean? = null,
)
