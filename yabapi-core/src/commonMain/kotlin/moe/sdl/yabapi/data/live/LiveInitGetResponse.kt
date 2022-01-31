@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.live.LiveResponseCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class LiveInitGetResponse(
    @SerialName("code") val code: LiveResponseCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: LiveInitData? = null,
)

@Serializable
public data class LiveInitData(
    @SerialName("room_id") val roomId: Int? = null,
    @SerialName("short_id") val shortId: Int? = null,
    @SerialName("uid") val uid: Int? = null,
    @SerialName("need_p2p") val needP2p: Boolean? = null,
    @SerialName("is_hidden") val isHidden: Boolean? = null,
    @SerialName("is_locked") val isLocked: Boolean? = null,
    @SerialName("is_portrait") val isPortrait: Boolean? = null,
    @SerialName("live_status") val liveStatus: Boolean? = null,
    @SerialName("hidden_till") val hiddenTill: Long? = null,
    @SerialName("lock_till") val lockTill: Long? = null,
    @SerialName("encrypted") val encrypted: Boolean? = null,
    @SerialName("pwd_verified") val pwdVerified: Boolean? = null,
    @SerialName("live_time") val liveTime: Long? = null,
    @SerialName("room_shield") val roomShield: Int? = null,
    @SerialName("is_sp") val isSpecial: Boolean? = null,
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
