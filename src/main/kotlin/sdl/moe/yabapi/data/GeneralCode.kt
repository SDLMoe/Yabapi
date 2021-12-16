package sdl.moe.yabapi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.GeneralCodeSerializer

/**
 * 通用的状态码枚举类
 * @property UNKNOWN 未知状态码
 * @property SUCCESS 成功
 */
@Serializable(with = GeneralCodeSerializer::class)
public enum class GeneralCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,
}
