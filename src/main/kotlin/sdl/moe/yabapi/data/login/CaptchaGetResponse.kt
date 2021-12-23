// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:Suppress("UNUSED")

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.login.GetCaptchaResponseCode.SUCCESS
import sdl.moe.yabapi.data.login.GetCaptchaResponseCode.UNKNOWN

/**
 * Json Response
 * @param code 返回状态码 [GetCaptchaResponseCode]
 * @param data 数据 [GetCaptchaResponseData]
 */
@Serializable
public data class GetCaptchaResponse(
    val code: GetCaptchaResponseCode = UNKNOWN,
    val data: GetCaptchaResponseData,
) {
    /**
     * 封裝, 少寫一層
     * @return [GetCaptchaResponseResult]
     */
    val result: GetCaptchaResponseResult = data.result
}

/**
 * @property [UNKNOWN] 未知返回值
 * @property [SUCCESS] 成功 - 0
 */
@Serializable
public enum class GetCaptchaResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS;
}

/**
 * @param result 结果 [GetCaptchaResponseResult]
 * @param type 未知值, 常见值为1
 */
@Serializable
public data class GetCaptchaResponseData(
    val result: GetCaptchaResponseResult,
    val type: Int,
)

/**
 * @param success 作用不明
 * @param id 验证码ID, 通常为固定值
 * @param captchaKey B站后端用于产生人机验证
 * @param loginKey 登录密钥, 登录接口相关, 和 [captchaKey] 对应
 */
@Serializable
public data class GetCaptchaResponseResult(
    @SerialName("success")
    val success: Int,
    @SerialName("gt")
    val id: String,
    @SerialName("challenge")
    val captchaKey: String,
    @SerialName("key")
    val loginKey: String,
)
