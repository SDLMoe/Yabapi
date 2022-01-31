@file:UseSerializers(BooleanJsSerializer::class)
package moe.sdl.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.login.LoginWebSMSResponseCode.INVALID_SMS_CODE
import moe.sdl.yabapi.data.login.LoginWebSMSResponseCode.REQUEST_ERROR
import moe.sdl.yabapi.data.login.LoginWebSMSResponseCode.SMS_CODE_EXPIRED
import moe.sdl.yabapi.data.login.LoginWebSMSResponseCode.SUCCESS
import moe.sdl.yabapi.data.login.LoginWebSMSResponseCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

/**
 * L
 */
@Serializable
public data class LoginWebSMSResponse(
    val code: LoginWebSMSResponseCode = UNKNOWN,
    val message: String? = null,
    val data: LoginWebSMSResponseData? = null,
)

/**
 * @property UNKNOWN 未知
 * @property SUCCESS 成功
 * @property REQUEST_ERROR 請求錯誤
 * @property INVALID_SMS_CODE 驗證碼錯誤
 * @property SMS_CODE_EXPIRED 驗證碼過期
 */
@Serializable
public enum class LoginWebSMSResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    REQUEST_ERROR,

    @SerialName("1006")
    INVALID_SMS_CODE,

    @SerialName("1007")
    SMS_CODE_EXPIRED;
}

/**
 * @param isNew 是否新用戶
 * @param status 未知
 * @param url 跳轉 URL 默認 [https://www.bilibili.com]
 */
@Serializable
public data class LoginWebSMSResponseData(
    val isNew: Boolean? = null,
    val status: Int? = null,
    val url: String? = null,
)
