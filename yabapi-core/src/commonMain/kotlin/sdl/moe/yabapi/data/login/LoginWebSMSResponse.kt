// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:UseSerializers(BooleanJsSerializer::class)
// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode.INVALID_SMS_CODE
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode.REQUEST_ERROR
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode.SMS_CODE_EXPIRED
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode.SUCCESS
import sdl.moe.yabapi.data.login.LoginWebSMSResponseCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

/**
 * L
 */
@Serializable
public data class LoginWebSMSResponse(
    val code: LoginWebSMSResponseCode = UNKNOWN,
    val message: String,
    val data: LoginWebSMSResponseData,
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
    val isNew: Boolean,
    val status: Int,
    val url: String,
)
