// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.login.SendSMSResponseCode.BANNED
import sdl.moe.yabapi.data.login.SendSMSResponseCode.CAPTCHA_SERVICE_ERROR
import sdl.moe.yabapi.data.login.SendSMSResponseCode.ERROR_REQUEST
import sdl.moe.yabapi.data.login.SendSMSResponseCode.HAS_SENT
import sdl.moe.yabapi.data.login.SendSMSResponseCode.INVALID_LOGIN_KEY
import sdl.moe.yabapi.data.login.SendSMSResponseCode.INVALID_PHONE
import sdl.moe.yabapi.data.login.SendSMSResponseCode.REQUEST_TOO_FREQUENT
import sdl.moe.yabapi.data.login.SendSMSResponseCode.SUCCESS
import sdl.moe.yabapi.data.login.SendSMSResponseCode.UNKNOWN

/**
 * 發送短信驗證碼返回
 * @param code 狀態碼 [SendSMSResponseCode]
 * @param message 錯誤碼, 成功爲 "0"
 * @param timestamp 時間戳, 成功時無
 * @param data [SendSMSResponseData]
 * @property captchaKey 封裝, [SendSMSResponseData.captchaKey]
 */
@Serializable
public data class SendSMSResponse(
    @SerialName("code")
    val code: SendSMSResponseCode = UNKNOWN,
    @SerialName("message")
    val message: String,
    @SerialName("ts")
    val timestamp: Long? = null,
    @SerialName("data")
    val data: SendSMSResponseData? = null,
) {
    public val captchaKey: String?
        get() = data?.captchaKey
}

/**
 * 發送短信驗證碼返回狀態碼
 * @property UNKNOWN 未知
 * @property SUCCESS 成功
 * @property ERROR_REQUEST 請求錯誤
 * @property INVALID_PHONE 手機格式錯誤
 * @property HAS_SENT 已發送過(需等待CD)
 * @property BANNED 手機號碼被封禁
 * @property INVALID_LOGIN_KEY 登入碼錯誤
 * @property CAPTCHA_SERVICE_ERROR 驗證碼服務錯誤
 * @property REQUEST_TOO_FREQUENT 請求過於頻繁
 */
@Serializable
public enum class SendSMSResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    ERROR_REQUEST,

    @SerialName("1002")
    INVALID_PHONE,

    @SerialName("1003")
    HAS_SENT,

    @SerialName("1025")
    BANNED,

    @SerialName("2400")
    INVALID_LOGIN_KEY,

    @SerialName("2406")
    CAPTCHA_SERVICE_ERROR,

    @SerialName("86203")
    REQUEST_TOO_FREQUENT;
}

/**
 * @param captchaKey 验证密钥
 */
@Serializable
public data class SendSMSResponseData(
    @SerialName("captcha_key") val captchaKey: String,
)
