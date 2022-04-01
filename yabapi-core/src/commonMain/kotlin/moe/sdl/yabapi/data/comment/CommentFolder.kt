@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.comment

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class CommentFolder(
    @SerialName("has_folded") val hasFolded: Boolean? = null,
    @SerialName("is_folded") val isFolded: Boolean? = null,
    @SerialName("rule") val rule: String? = null,
)
