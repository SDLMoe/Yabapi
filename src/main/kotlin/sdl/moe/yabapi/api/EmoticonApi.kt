// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import sdl.moe.yabapi.BiliClient

public object EmoticonApi : BiliApi {
    init {
        BiliClient.registerApi(this)
    }

    override val apiName: String = "emoticon"

    @Suppress("Unused")
    public val BiliClient.emoticon: EmoticonApi
        get() = this@EmoticonApi

}
