// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

/**
 * 帐号基本信息 (昵称 签名 生日 性别 等级)
 * @param data [AccountInfo]
 */
@Serializable
public data class AccountInfoGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: AccountInfo,
)

/**
 * @param mid 用户 mid
 * @param nick 昵称
 * @param userId 用户ID, 形如 bili_11451419
 * @param bio 简介/个性签名
 * @param birthday 生日 YYYY-MM-DD
 * @param sex 性别
 * @param hasSetNick 是否有昵称 而非bili_xxxxx
 * @param rank 会员等级
 */
@Serializable
public data class AccountInfo(
    @SerialName("mid") val mid: Int,
    @SerialName("uname") val nick: String,
    @SerialName("userid") val userId: String,
    @SerialName("sign") val bio: String,
    @SerialName("birthday") val birthday: String,
    @SerialName("sex") val sex: String,
    @SerialName("nick_free") val hasSetNick: Boolean,
    @SerialName("rank") val rank: String,
)
