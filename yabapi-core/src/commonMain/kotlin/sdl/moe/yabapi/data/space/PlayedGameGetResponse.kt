package sdl.moe.yabapi.data.space

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class PlayedGameGetResponse(
    @SerialName("code") val code: SpaceResponseCode,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: List<PlayedGameNode>? = null,
)



@Serializable
public data class PlayedGameNode(
    @SerialName("website") val website: String,
    @SerialName("image") val image: String,
    @SerialName("name") val name: String,
)
