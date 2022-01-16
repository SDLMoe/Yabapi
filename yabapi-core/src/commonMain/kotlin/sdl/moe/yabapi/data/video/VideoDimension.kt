@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoDimension(
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int,
    @SerialName("rotate") val isRotate: Boolean,
)
