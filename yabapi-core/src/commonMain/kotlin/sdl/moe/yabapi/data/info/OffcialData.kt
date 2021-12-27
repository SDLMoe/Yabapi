// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.info.IsOffcialCertifiedSerializer
import sdl.moe.yabapi.serializer.data.info.OffcialRoleSerializer
import kotlin.jvm.JvmInline

/**
 * 官方身份數據類
 * @param role [OffcialRole]
 * @param title 認證信息
 * @param info 備註
 * @param isCertified 是否認證
 * @see [IsOffcialCertified]
 */
@Serializable
public data class Offcial(
    @SerialName("role") val role: OffcialRole = OffcialRole.UNKNOWN,
    @SerialName("title") val title: String? = null,
    @SerialName("desc") val info: String? = null,
    @SerialName("type") val isCertified: IsOffcialCertified = IsOffcialCertified(false),
)

@Serializable(with = OffcialRoleSerializer::class)
public enum class OffcialRole(public val valueList: List<Int>) {
    UNKNOWN(emptyList()),

    NONE(listOf(0)),

    PERSONAL(listOf(1, 2, 7)),

    ORGANIZATION(listOf(3, 4, 5, 6))
}

/**
 * value class 用於封裝
 * @see IsOffcialCertifiedSerializer
 */
@Serializable(with = IsOffcialCertifiedSerializer::class)
@JvmInline
public value class IsOffcialCertified(public val value: Boolean)

/**
 * 官方认证信息, 可以认为是 [Offcial] 的迷你版¿
 * @property isCertified 是否认证
 * @property info 简介
 * @see [Offcial]
 * @see [IsOffcialCertified]
 */
@Serializable
public data class OffcialCertify(
    @SerialName("type") val isCertified: IsOffcialCertified = IsOffcialCertified(false),
    @SerialName("desc") val info: String? = null,
)
