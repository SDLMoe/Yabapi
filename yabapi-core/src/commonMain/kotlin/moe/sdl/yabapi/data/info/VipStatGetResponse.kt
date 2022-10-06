@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 獲取大會員狀態的返回
 * @param code [GeneralCode]
 * @param message 錯誤信息
 * @param data 信息本體 [VipStatGetData]
 */
@Serializable
public data class VipStatGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VipStatGetData? = null,
)

/**
 * @param mid 用戶 mid
 * @param vipType [VipType] 月度/年度
 * @param vipStatus [VipStatus] vip狀態 是否異常
 * @param isPaid 是否購買
 * @param themeType 未知
 */
@Serializable
public data class VipStatGetData(
    @SerialName("mid") val mid: Long? = null,
    @SerialName("vip_type") val vipType: VipType? = null,
    @SerialName("vip_status") val vipStatus: VipStatus? = null,
    @SerialName("vip_due_date") val vipDueDate: Long? = null,
    @SerialName("vip_pay_type") val isPaid: Boolean? = null,
    @SerialName("theme_type") val themeType: Int? = null,
    @SerialName("label") val label: VipLabel? = null,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String? = null,
    @SerialName("nickname_color") val nicknameColor: String? = null,
    @SerialName("is_new_user") val isNewUser: Boolean? = null,
)
