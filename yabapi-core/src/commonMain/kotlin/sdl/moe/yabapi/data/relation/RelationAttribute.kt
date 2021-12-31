// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.relation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.relation.RelationAttribute.INTER_SUB
import sdl.moe.yabapi.data.relation.RelationAttribute.NOT_SUB
import sdl.moe.yabapi.data.relation.RelationAttribute.QUIETLY_SUB
import sdl.moe.yabapi.data.relation.RelationAttribute.SUB
import sdl.moe.yabapi.data.relation.RelationAttribute.UNKNOWN

/**
 * @property UNKNOWN 未知
 * @property QUIETLY_SUB 悄悄關注
 * @property NOT_SUB 未關注
 * @property SUB 已關注
 * @property INTER_SUB 互相關注
 */
@Serializable
public enum class RelationAttribute {
    UNKNOWN,

    @SerialName("0")
    NOT_SUB,

    @SerialName("1")
    QUIETLY_SUB,

    @SerialName("2")
    SUB,

    @SerialName("6")
    INTER_SUB,

    @SerialName("128")
    BLACKLIST,
}
