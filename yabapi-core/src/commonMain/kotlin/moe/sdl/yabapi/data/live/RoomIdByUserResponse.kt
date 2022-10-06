package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class RoomIdByUserResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: RoomId? = null,
) {
    // shortcut
    inline val roomId: Long?
        get() = data?.roomId
    @Serializable
    public data class RoomId(
        @SerialName("room_id") val roomId: Long? = null,
    )
}
