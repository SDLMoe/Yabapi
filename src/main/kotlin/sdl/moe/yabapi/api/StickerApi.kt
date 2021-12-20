// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import mu.KotlinLogging
import sdl.moe.yabapi.BiliClient

private val logger = KotlinLogging.logger {}

/**
 * 表情包相关 API
 */
public object StickerApi : BiliApi {
    init {
        BiliClient.registerApi(this)
    }

    override val apiName: String = "sticker"

    @Suppress("Unused")
    public val BiliClient.sticker: StickerApi
        get() = this@StickerApi

}
