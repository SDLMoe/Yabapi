@file:Suppress("UNUSED")

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.login.QueryCaptchaResponseCodeSerializer

/**
 * Json Response
 * @param code [QueryCaptchaResponseCode]
 * @param data [QueryCaptchaResponseData]
 */
@Serializable
data class QueryCaptchaResponse(
    val code: QueryCaptchaResponseCode,
    val data: QueryCaptchaResponseData,
)

/**
 * [QueryCaptchaResponseCode.UNKNOWN] 未知返回值
 * [QueryCaptchaResponseCode.SUCCESS] 成功
 */
@Serializable(with = QueryCaptchaResponseCodeSerializer::class)
enum class QueryCaptchaResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS;
}

/**
 * @param result 结果
 * @param type 未知值, 常见值为1
 */
@Serializable
data class QueryCaptchaResponseData(
    val result: QueryCaptchaResponseResult,
    val type: Int,
)

/**
 * @param success 作用不明
 * @param id 验证码ID, 通常为固定值
 * @param captchaKey B站后端用于产生人机验证
 * @param loginKey 登录密钥, 登录接口相关, 和 [captchaKey] 对应
 */
@Serializable
data class QueryCaptchaResponseResult(
    @SerialName("success")
    val success: Int,
    @SerialName("gt")
    val id: String,
    @SerialName("challenge")
    val captchaKey: String,
    @SerialName("key")
    val loginKey: String,
)
