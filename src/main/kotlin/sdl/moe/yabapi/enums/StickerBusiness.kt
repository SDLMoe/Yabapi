// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.enums

public enum class StickerBusiness {
    REPLY {
        override fun toString(): String = "reply"
    },

    DYNAMIC {
        override fun toString(): String = "dynamic"
    };
}
