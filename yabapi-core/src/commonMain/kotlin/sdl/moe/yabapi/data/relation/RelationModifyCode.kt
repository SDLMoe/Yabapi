// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.relation.RelationModifyCode.BANNED
import sdl.moe.yabapi.data.relation.RelationModifyCode.BLACKLIST
import sdl.moe.yabapi.data.relation.RelationModifyCode.CANNOT_MODIFY_SELF
import sdl.moe.yabapi.data.relation.RelationModifyCode.CSRF_ERROR
import sdl.moe.yabapi.data.relation.RelationModifyCode.INVALID_REQUEST
import sdl.moe.yabapi.data.relation.RelationModifyCode.SUCCESS

/**
 * @property SUCCESS 成功
 * @property BANNED 被封禁
 * @property CSRF_ERROR CSRF 錯誤
 * @property INVALID_REQUEST 錯誤請求
 * @property CANNOT_MODIFY_SELF 不能操作自身
 * @property BLACKLIST 目標位於黑名單
 */
@Serializable
public enum class RelationModifyCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-102")
    BANNED,

    @SerialName("-111")
    CSRF_ERROR,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("22001")
    CANNOT_MODIFY_SELF,

    @SerialName("22003")
    BLACKLIST,
}
