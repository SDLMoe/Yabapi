package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.info.IsOfficialCertifiedSerializer
import kotlin.jvm.JvmInline

/**
 * value class 用於封裝
 * @see IsOfficialCertifiedSerializer
 */
@Serializable(with = IsOfficialCertifiedSerializer::class)
@JvmInline
public value class IsOfficialCertified(public val value: Boolean)

/**
 * 官方认证信息, 可以认为是 [Official] 的迷你版¿
 * @property isCertified 是否认证
 * @property info 简介
 * @see [Official]
 * @see [IsOfficialCertified]
 */
@Serializable
public data class OfficialCertify(
    @SerialName("type") val isCertified: IsOfficialCertified = IsOfficialCertified(false),
    @SerialName("desc") val info: String? = null,
)
