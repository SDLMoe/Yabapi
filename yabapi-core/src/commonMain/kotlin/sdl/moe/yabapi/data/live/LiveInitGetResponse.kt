@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.live.LiveResponseCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveInitGetResponse(
    @SerialName("code") val code: LiveResponseCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveInitData? = null,
)

@Serializable
public data class LiveInitData(
    @SerialName("room_id") val roomId: Int,
    @SerialName("short_id") val shortId: Int,
    @SerialName("uid") val uid: Int,
    @SerialName("need_p2p") val needP2p: Boolean,
    @SerialName("is_hidden") val isHidden: Boolean,
    @SerialName("is_locked") val isLocked: Boolean,
    @SerialName("is_portrait") val isPortrait: Boolean,
    @SerialName("live_status") val liveStatus: Boolean,
    @SerialName("hidden_till") val hiddenTill: Long,
    @SerialName("lock_till") val lockTill: Long,
    @SerialName("encrypted") val encrypted: Boolean,
    @SerialName("pwd_verified") val pwdVerified: Boolean,
    @SerialName("live_time") val liveTime: Long,
    @SerialName("room_shield") val roomShield: Int,
    @SerialName("is_sp") val isSpecial: Boolean,
    @SerialName("special_type") val specialType: SpecialType = SpecialType.UNKNOWN,
)

@Serializable
public enum class SpecialType {
    UNKNOWN,

    @SerialName("0")
    NORMAL,

    @SerialName("1")
    PAID,

    @SerialName("2")
    NEW_YEAR,
}
