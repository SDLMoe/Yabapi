@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.login.LoginWebResponseCode.BLANK_ACCOUNT
import sdl.moe.yabapi.data.login.LoginWebResponseCode.CAPTCHA_SERVICE_ERROR
import sdl.moe.yabapi.data.login.LoginWebResponseCode.INVALID_ACCOUNT
import sdl.moe.yabapi.data.login.LoginWebResponseCode.INVALID_LOGIN_KEY
import sdl.moe.yabapi.data.login.LoginWebResponseCode.INVALID_REQUEST
import sdl.moe.yabapi.data.login.LoginWebResponseCode.MISS_PARAMETER
import sdl.moe.yabapi.data.login.LoginWebResponseCode.NEED_TWO_FACTOR
import sdl.moe.yabapi.data.login.LoginWebResponseCode.SUCCESS
import sdl.moe.yabapi.data.login.LoginWebResponseCode.TIMEOUT
import sdl.moe.yabapi.data.login.LoginWebResponseCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * @param code 返回值 [LoginWebResponseCode]
 * @param timestamp 时间戳 仅失败时返回
 * @param message 错误信息, 默认为 "0"
 * @param data 成功时返回
 */
@Serializable
public data class LoginWebResponse(
    @SerialName("code")
    val code: LoginWebResponseCode,
    @SerialName("ts")
    val timestamp: Long? = null,
    @SerialName("message")
    val message: String = "0",
    @SerialName("data")
    val data: LoginWebResponseData? = null,
)

/**
 * [LoginWebResponse] 的返回值
 * @property UNKNOWN 未知返回值
 * @property SUCCESS 成功
 * @property INVALID_REQUEST 请求错误
 * @property INVALID_ACCOUNT 帐号或密码错误
 * @property BLANK_ACCOUNT 帐号或密码为空
 * @property TIMEOUT 超时
 * @property MISS_PARAMETER 缺少必要参数
 * @property NEED_TWO_FACTOR 需要手机或邮箱二要素验证
 * @property INVALID_LOGIN_KEY 登录密钥错误 @see [QueryCaptchaResponse]
 * @property CAPTCHA_SERVICE_ERROR 验证码服务错误
 */
@Serializable
public enum class LoginWebResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("-629")
    INVALID_ACCOUNT,

    @SerialName("-653")
    BLANK_ACCOUNT,

    @SerialName("-662")
    TIMEOUT,

    @SerialName("-2001")
    MISS_PARAMETER,

    @SerialName("-2100")
    NEED_TWO_FACTOR,

    @SerialName("2400")
    INVALID_LOGIN_KEY,

    @SerialName("2406")
    CAPTCHA_SERVICE_ERROR;
}

/**
 * [LoginWebResponse] 的正文数据
 * @property redirectUrl 跳转地址, 用于游戏分站
 * @property isLogin 是否登录
 * @property mid 用户 mid
 * @property telephone 用户手机号, * 号遮蔽部分
 * @property email 用户邮箱, * 号遮蔽部分
 * @property sorce 未知
 * @property keepTime 未知
 * @property goUrl 重定向地址
 */
@Serializable
public data class LoginWebResponseData(
    // not login
    @SerialName("redirectUrl")
    val redirectUrl: String?,

    // login-ed
    @SerialName("isLogin")
    val isLogin: Boolean?,

    // need two factor
    @SerialName("mid")
    val mid: Int?,
    @SerialName("tel")
    val telephone: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("sorce")
    val sorce: Int,
    @SerialName("keeptime")
    val keepTime: Int,

    // except for 'not login'
    @SerialName("goUrl")
    val goUrl: String?,
)
