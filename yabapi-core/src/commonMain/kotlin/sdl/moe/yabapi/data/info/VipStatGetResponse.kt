// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
    @SerialName("ttl") val ttl: Int,
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
    @SerialName("mid") val mid: Int,
    @SerialName("vip_type") val vipType: VipType,
    @SerialName("vip_status") val vipStatus: VipStatus? = null,
    @SerialName("vip_due_date") val vipDueDate: Long,
    @SerialName("vip_pay_type") val isPaid: Boolean,
    @SerialName("theme_type") val themeType: Int,
    @SerialName("label") val label: VipLabel? = null,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String,
    @SerialName("nickname_color") val nicknameColor: String,
    @SerialName("is_new_user") val isNewUser: Boolean,
)