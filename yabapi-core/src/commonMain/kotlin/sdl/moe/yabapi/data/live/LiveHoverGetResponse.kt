@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveHoverGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveHover,
)

@Serializable
public data class LiveHover(
    @SerialName("list") val room: List<Room>,
    @SerialName("banner") val banner: List<Banner>,
) {
    @Serializable
    public data class Room(
        @SerialName("area_id") val areaId: Int,
        @SerialName("area_name") val areaName: String,
        @SerialName("pic") val pic: String,
        @SerialName("is_hot") val isHot: Boolean,
        @SerialName("is_new") val isNew: Boolean,
    )
    @Serializable
    public data class Banner(
        @SerialName("id") val id: Int,
        @SerialName("img") val img: String,
        @SerialName("url") val url: String,
    )
}
