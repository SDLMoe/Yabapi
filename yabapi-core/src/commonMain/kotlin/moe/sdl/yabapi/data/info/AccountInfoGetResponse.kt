package moe.sdl.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.GeneralCode

/**
 * 帐号基本信息 (昵称 签名 生日 性别 等级)
 * @param data [AccountInfo]
 */
@Serializable
public data class AccountInfoGetResponse(
    @SerialName("code") val code: GeneralCode = GeneralCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: AccountInfo? = null,
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
    @SerialName("mid") val mid: Long? = null,
    @SerialName("uname") val nick: String? = null,
    @SerialName("userid") val userId: String? = null,
    @SerialName("sign") val bio: String? = null,
    @SerialName("birthday") val birthday: String? = null,
    @SerialName("sex") val sex: String? = null,
    @SerialName("nick_free") val hasSetNick: Boolean? = null,
    @SerialName("rank") val rank: String? = null,
)
