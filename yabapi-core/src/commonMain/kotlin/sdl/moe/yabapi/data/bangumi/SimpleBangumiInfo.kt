@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class SimpleBangumiEpisode(
    @SerialName("id") val id: Int,
    @SerialName("cover") val cover: String? = null,
    @SerialName("desc") val description: String? = null,
    @SerialName("is_new") val isNew: Boolean? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("index") val rawName: String? = null,
    @SerialName("index_show") val showName: String? = null,
)
