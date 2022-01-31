@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoSubtitle(
    @SerialName("allow_submit") val allowSubmit: Boolean? = null,
    @SerialName("list") val list: List<SubtitleTrack> = emptyList(),
)

@Serializable
public data class SubtitleTrack(
    @SerialName("id") val id: Int? = null,
    @SerialName("lan") val language: String? = null,
    @SerialName("lan_doc") val languageName: String? = null,
    @SerialName("is_lock") val isLocked: Boolean? = null,
    @SerialName("author_mid") val authorMid: Int? = null,
    @SerialName("subtitle_url") val subtitleUrl: String? = null,
    @SerialName("author") val author: SubtitleAuthor? = null,
)

@Serializable
public data class SubtitleAuthor(
    @SerialName("mid") val mid: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("rank") val rank: Int? = null,
    @SerialName("birthday") val birthday: Int? = null,
    @SerialName("is_fake_account") val isFakeAccount: Boolean? = null,
    @SerialName("is_deleted") val isDeleted: Boolean? = null,
)
