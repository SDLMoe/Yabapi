@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoDimension(
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("rotate") val isRotate: Boolean? = null,
)
