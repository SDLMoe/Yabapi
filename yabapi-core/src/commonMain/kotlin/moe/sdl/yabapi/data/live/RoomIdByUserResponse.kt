package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN

@Serializable
public data class RoomIdByUserResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String,
    @SerialName("message") val message: String,
    @SerialName("data") val data: RoomId,
) {
    // shortcut
    inline val roomId: Int
        get() = data.roomId
    @Serializable
    public data class RoomId(
        @SerialName("room_id") val roomId: Int,
    )
}
