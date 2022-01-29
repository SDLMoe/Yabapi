package moe.sdl.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.relation.RelationResponseCode.ERROR_REQUEST
import moe.sdl.yabapi.data.relation.RelationResponseCode.LIMITED
import moe.sdl.yabapi.data.relation.RelationResponseCode.PRIVACY_LIMITED
import moe.sdl.yabapi.data.relation.RelationResponseCode.SUCCESS

/**
 * @property SUCCESS 成功
 * @property ERROR_REQUEST 錯誤請求
 * @property LIMITED 獲取數量超過前 5 頁
 * @property PRIVACY_LIMITED 隱私設置限制
 */
@Serializable
public enum class RelationResponseCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-400")
    ERROR_REQUEST,

    @SerialName("22007")
    LIMITED,

    @SerialName("22115")
    PRIVACY_LIMITED,
}
