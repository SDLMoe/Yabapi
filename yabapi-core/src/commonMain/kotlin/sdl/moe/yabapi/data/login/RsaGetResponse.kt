// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param salt 密码校验盐值
 * @param rsa RSA公钥, 固定值
 * @param message 错误信息
 * @param timeStamp 时间戳
 * @param code 错误代码 [RsaGetResponseCode]
 */
@Serializable
public data class RsaGetResponse(
    @SerialName("hash")
    val salt: String? = null,
    @SerialName("key")
    val rsa: String? = null,
    @SerialName("message")
    val message: String = "",
    @SerialName("ts")
    val timeStamp: Long? = null,
    @SerialName("code")
    val code: RsaGetResponseCode = RsaGetResponseCode.SUCCESS,
)

@Serializable
public enum class RsaGetResponseCode {
    UNKNOWN,

    @SerialName("-3")
    SIGN_INVALID,

    @SerialName("0")
    SUCCESS,
}
