@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class HasLikedResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val hasLiked: Boolean? = null,
)
