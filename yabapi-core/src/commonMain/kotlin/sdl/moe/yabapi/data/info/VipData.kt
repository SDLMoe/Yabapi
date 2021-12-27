// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.VipStatus.IP_CHANGE_FREQUENT
import sdl.moe.yabapi.data.info.VipStatus.NORMAL
import sdl.moe.yabapi.data.info.VipStatus.RISK_LOCKED

/**
 * 大会员数据类
 * @param type 大会员类型 [VipType] 月度/年度
 * @param status 大会员状态 [VipStatus] 正常/异常/冻结
 * @param dueDate 到期时间 单位: ms
 * @param isPaid 是否购买
 * @param themeType 未知
 * @param label [VipLabel]
 * @param role 未知
 */
@Serializable
public data class Vip(
    @SerialName("type") val type: VipType = VipType.UNNOWN,
    @SerialName("status") val status: VipStatus = VipStatus.UNKNOWN,
    @SerialName("due_date") val dueDate: Long,
    @SerialName("vip_pay_type") val isPaid: Boolean,
    @SerialName("theme_type") val themeType: Int,
    @SerialName("label") val label: VipLabel,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean,
    @SerialName("nickname_color") val nicknameColor: String,
    @SerialName("role") val role: Int,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String,
)

@Serializable
public enum class VipType {
    UNNOWN,

    @SerialName("0")
    NONE,

    @SerialName("1")
    MONTH,

    @SerialName("2")
    YEAR,
}

/**
 * 大会员状态
 * @property UNKNOWN 未知
 * @property NORMAL 正常
 * @property IP_CHANGE_FREQUENT IP 更换频繁被冻结
 * @property RISK_LOCKED 风控冻结
 */
@Serializable
public enum class VipStatus {
    UNKNOWN,

    @SerialName("1")
    NORMAL,

    @SerialName("2")
    IP_CHANGE_FREQUENT,

    @SerialName("3")
    RISK_LOCKED,
}

/**
 * @param path 未知
 * @param text 会员名称
 * @param label 标签
 * @param textColor 文字颜色
 * @param backgroundStyle 背景风格
 * @param backgroundColor 背景颜色
 * @param borderColor 描边颜色
 */
@Serializable
public data class VipLabel(
    @SerialName("path") val path: String,
    @SerialName("text") val text: String,
    @SerialName("label_theme") val label: String,
    @SerialName("text_color") val textColor: String,
    @SerialName("bg_style") val backgroundStyle: Int,
    @SerialName("bg_color") val backgroundColor: String,
    @SerialName("border_color") val borderColor: String,
)
