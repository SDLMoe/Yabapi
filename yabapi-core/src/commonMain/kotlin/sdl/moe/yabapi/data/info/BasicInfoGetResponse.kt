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
import sdl.moe.yabapi.serializer.data.info.IsOffcialCertifiedSerializer
import sdl.moe.yabapi.serializer.data.info.NextExpSerializer
import sdl.moe.yabapi.serializer.data.info.OffcialRoleSerializer
import kotlin.jvm.JvmInline

/**
 * @see BasicInfoData
 */
@Serializable
public data class BasicInfoGetResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: BasicInfoData,
)

/**
 * 参数太多只列重要的
 *
 * @param isLogin 是否登录
 * @param mid 用户 mid
 * @param moral 节操值
 * @param
 */
@Serializable
public data class BasicInfoData(
    @SerialName("isLogin") val isLogin: Boolean,
    @SerialName("email_verified") val isVerifiedEmail: Boolean? = null,
    @SerialName("face") val avatar: String? = null,
    @SerialName("face_nft") val avatarNft: Int? = null,
    @SerialName("level_info") val levelInfo: LevelInfo? = null,
    @SerialName("mid") val mid: Int? = null,
    @SerialName("mobile_verified") val isVerifiedMobile: Boolean? = null,
    @SerialName("money") val coin: Double? = null,
    @SerialName("moral") val moral: Double? = null,
    @SerialName("official") val offcial: Offcial? = null,
    @SerialName("officialVerify") val offcialCertify: OffcialCertify? = null,
    @SerialName("pendant") val pendant: Pendant? = null,
    @SerialName("scores") val scores: Int? = null,
    @SerialName("uname") val username: String? = null,
    @SerialName("vipDueDate") val vipDueDate: Long? = null,
    @SerialName("vipStatus") val vipStatus: Boolean? = null,
    @SerialName("vipType") val vipType: Int? = null,
    @SerialName("vip_pay_type") val vipPayType: Boolean? = null,
    @SerialName("vip_theme_type") val vipThemeType: Int? = null,
    @SerialName("vip_label") val vipLabel: VipLabel? = null,
    @SerialName("vip_avatar_subscript") val isShowSubscript: Boolean? = null,
    @SerialName("vip_nickname_color") val vipNicknameColor: String? = null,
    @SerialName("vip") val vip: Vip? = null,
    @SerialName("wallet") val wallet: Wallet? = null,
    @SerialName("has_shop") val hasShop: Boolean? = null,
    @SerialName("shop_url") val shopUrl: String? = null,
    @SerialName("allowance_count") val allowanceCount: Int? = null,
    @SerialName("answer_status") val answerStatus: Int? = null,
)

/**
 * @param currentLevel 當前用戶等級
 * @param currentMin 當前等級最小經驗值
 * @param currentExp 當前經驗值
 * @param nextExp [NextExp] 下一級經驗值, 當等級爲 6 時, 值爲 -1 (無下一等級)
 */
@Serializable
public data class LevelInfo(
    @SerialName("current_level") val currentLevel: Int,
    @SerialName("current_min") val currentMin: Int,
    @SerialName("current_exp") val currentExp: Int,
    @SerialName("next_exp") val nextExp: NextExp,
)

/**
 * value class 用於封裝
 * @property value 實際值
 * @see NextExpSerializer
 */
@Serializable(with = NextExpSerializer::class)
@JvmInline
public value class NextExp(public val value: Int)

/**
 * 官方身份數據類
 * @param role [OffcialRole]
 * @param title 認證信息
 * @param info 備註
 * @param isCertified 是否認證
 * @see [IsOffcialCertified]
 */
@Serializable
public data class Offcial(
    @SerialName("role") val role: OffcialRole = OffcialRole.UNKNOWN,
    @SerialName("title") val title: String? = null,
    @SerialName("desc") val info: String? = null,
    @SerialName("type") val isCertified: IsOffcialCertified = IsOffcialCertified(false),
)

@Serializable(with = OffcialRoleSerializer::class)
public enum class OffcialRole(public val valueList: List<Int>) {
    UNKNOWN(emptyList()),

    NONE(listOf(0)),

    PERSONAL(listOf(1, 2, 7)),

    ORGANIZATION(listOf(3, 4, 5, 6))
}

/**
 * value class 用於封裝
 * @see IsOffcialCertifiedSerializer
 */
@Serializable(with = IsOffcialCertifiedSerializer::class)
@JvmInline
public value class IsOffcialCertified(public val value: Boolean)

/**
 * 官方认证信息, 可以认为是 [Offcial] 的迷你版¿
 * @property isCertified 是否认证
 * @property info 简介
 * @see [Offcial]
 * @see [IsOffcialCertified]
 */
@Serializable
public data class OffcialCertify(
    @SerialName("type") val isCertified: IsOffcialCertified = IsOffcialCertified(false),
    @SerialName("desc") val info: String? = null,
)

/**
 * 挂件信息
 * @param pid 挂件id
 * @param name 名称
 * @param image 图片url
 * @param imageEnhance 未知
 * @param imageEnhanceFrame 未知
 * @param expire 可能为过期时间, 但仅返回 `0`
 */
@Serializable
public data class Pendant(
    @SerialName("pid") val pid: Int,
    @SerialName("name") val name: String,
    @SerialName("image") val image: String,
    @SerialName("image_enhance") val imageEnhance: String,
    @SerialName("image_enhance_frame") val imageEnhanceFrame: String,
    @SerialName("expire") val expire: Long,
)

/**
 * 大会员数据类
 * @param type 类型
 * @param status 未知
 * @param dueDate 到期时间
 * @param vipPayType 未知
 * @param themeType 未知
 * @param label [VipLabel]
 * @param role 未知
 */
@Serializable
public data class Vip(
    @SerialName("type") val type: Int,
    @SerialName("status") val status: Int,
    @SerialName("due_date") val dueDate: Long,
    @SerialName("vip_pay_type") val vipPayType: Int,
    @SerialName("theme_type") val themeType: Int,
    @SerialName("label") val label: VipLabel,
    @SerialName("avatar_subscript") val isShowSubscript: Boolean,
    @SerialName("nickname_color") val nicknameColor: String,
    @SerialName("role") val role: Int,
    @SerialName("avatar_subscript_url") val avatarSubscriptUrl: String,
)

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

/**
 * @param mid 用户 mid
 * @param bcoinBalance 目前 B 币数
 * @param couponBalance 每月奖励 B 币数
 * @param couponDueTime 可能为奖励到期时间, 但返回为 `0`
 */
@Serializable
public data class Wallet(
    @SerialName("mid") val mid: Int,
    @SerialName("bcoin_balance") val bcoinBalance: Int,
    @SerialName("coupon_balance") val couponBalance: Int,
    @SerialName("coupon_due_time") val couponDueTime: Long,
)
