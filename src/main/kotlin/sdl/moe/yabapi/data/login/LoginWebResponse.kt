// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

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
import sdl.moe.yabapi.serializer.data.login.LoginWebResponseCodeSerializer

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
 * @property RSA_DECRYPT_FAILED RSA 解密失敗
 */
@Serializable(with = LoginWebResponseCodeSerializer::class)
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
    CAPTCHA_SERVICE_ERROR,

    @SerialName("86000")
    RSA_DECRYPT_FAILED;
}

/**
 * [LoginWebResponse] 的正文数据
 * @param redirectUrl 跳转地址, 用于游戏分站
 * @param isLogin 是否登录
 * @param mid 用户 mid
 * @param telephone 用户手机号, * 号遮蔽部分
 * @param email 用户邮箱, * 号遮蔽部分
 * @param sorce 未知
 * @param keepTime 未知
 * @param goUrl 重定向地址
 */
@Serializable
public data class LoginWebResponseData(
    // not login
    @SerialName("redirectUrl")
    val redirectUrl: String? = null,

    // login-ed
    @SerialName("isLogin")
    val isLogin: Boolean? = null,

    // need two factor
    @SerialName("mid")
    val mid: Int? = null,
    @SerialName("tel")
    val telephone: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("sorce")
    val sorce: Int? = null,
    @SerialName("keeptime")
    val keepTime: Int? = null,

    // except for 'not login'
    @SerialName("goUrl")
    val goUrl: String? = null,
)
