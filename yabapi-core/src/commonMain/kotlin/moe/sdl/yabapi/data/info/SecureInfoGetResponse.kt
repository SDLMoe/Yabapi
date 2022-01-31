@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.info.PwdLevel.MEDIUM
import moe.sdl.yabapi.data.info.PwdLevel.STRONG
import moe.sdl.yabapi.data.info.PwdLevel.UNKNOWN
import moe.sdl.yabapi.data.info.PwdLevel.WEAK
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * 獲取安全信息的返回
 * @param code 返回值 [GeneralCode]
 * @param message 錯誤信息
 * @param ttl ttl
 * @param data [SecureInfoData]
 */
@Serializable
public data class SecureInfoGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: SecureInfoData? = null,
)

/**
 * 安全信息獲取本體 data
 * @param bindInfo 綁定相關信息 [SecureBindInfo]
 * @param pwdInfo 密碼相關信息 [SecurePwdInfo]
 * @param snsInfo 社交媒體綁定相關 [SecureSnsInfo]
 * @param other 其他 [SecureOtherInfo]
 */
@Serializable
public data class SecureInfoData(
    @SerialName("account_info") val bindInfo: SecureBindInfo? = null,
    @SerialName("account_safe") val pwdInfo: SecurePwdInfo? = null,
    @SerialName("account_sns") val snsInfo: SecureSnsInfo? = null,
    @SerialName("account_other") val other: SecureOtherInfo? = null,
)

/**
 * @param phoneCensored 遮蔽了的手機號
 * @param mailCensored 遮蔽了的郵箱號
 * @param hasPhoneBind 是否有手機綁定
 * @param hasMailBind 是否有郵箱綁定
 * @param isPhoneVerified 手機綁定是否通過驗證
 * @param isMailVerified 郵箱綁定是否通過驗證
 * @param notSetPwd 是否 **沒有** 設置密碼
 * @param isRealNamed 是否實名
 */
@Serializable
public data class SecureBindInfo(
    @SerialName("hide_tel") val phoneCensored: String? = null,
    @SerialName("hide_mail") val mailCensored: String? = null,
    @SerialName("bind_tel") val hasPhoneBind: Boolean? = null,
    @SerialName("bind_mail") val hasMailBind: Boolean? = null,
    @SerialName("tel_verify") val isPhoneVerified: Boolean? = null,
    @SerialName("mail_verify") val isMailVerified: Boolean? = null,
    @SerialName("unneeded_check") val notSetPwd: Boolean? = null,
    @SerialName("realname_certified") val isRealNamed: Boolean? = null,
)

/**
 * @param score 安全等級, 已棄用
 * @param newScore 新安全等級
 * @param pwdLevel 密碼安全等級 [PwdLevel]
 * @param isSecure 密碼是否安全
 */
@Serializable
public data class SecurePwdInfo(
    @SerialName("Score") val score: Int? = null,
    @SerialName("score_new") val newScore: Int? = null,
    @SerialName("pwd_level") val pwdLevel: PwdLevel = UNKNOWN,
    @SerialName("security") val isSecure: Boolean? = null,
)

/**
 * 密碼等級 弱 [WEAK] | 中 [MEDIUM] | 強 [STRONG] | 未知 [UNKNOWN]
 */
@Serializable
public enum class PwdLevel {
    UNKNOWN,

    @SerialName("1")
    WEAK,

    @SerialName("2")
    MEDIUM,

    @SerialName("3")
    STRONG,
}

/**
 * @param hasWeiboBind 是否有微博綁定
 * @param hasQQBind 是否有 QQ 綁定
 * @param hasWechatBind 是否有微信綁定
 */
@Serializable
public data class SecureSnsInfo(
    @SerialName("weibo_bind") val hasWeiboBind: Boolean? = null,
    @SerialName("qq_bind") val hasQQBind: Boolean? = null,
    @SerialName("wechat_bind") val hasWechatBind: Boolean? = null,
)

/**
 * @param canSkipVerify 能否跳過驗證
 */
@Serializable
public data class SecureOtherInfo(
    @SerialName("skipVerify") val canSkipVerify: Boolean? = null,
)
