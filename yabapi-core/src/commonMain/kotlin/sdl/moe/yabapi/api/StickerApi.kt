// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.sticker.GET_ALL_STICKERS_URL
import sdl.moe.yabapi.data.sticker.AllStickersGetResponse
import sdl.moe.yabapi.enums.StickerBusiness
import sdl.moe.yabapi.util.logger

/**
 * 表情包相关 API
 */
public object StickerApi : BiliApi {
    init {
        BiliClient.registerApi(this)
    }

    override val apiName: String = "sticker"

    @Suppress("Unused")
    public val BiliClient.stickerApi: StickerApi
        get() = this@StickerApi

    /**
     * @param business 使用場景 [StickerBusiness]
     */
    public suspend fun BiliClient.getAllStickers(business: StickerBusiness): AllStickersGetResponse =
        withContext(Platform.ioDispatcher) {
            logger.debug { "Getting all stickers for business: $business" }
            client.get<AllStickersGetResponse>(GET_ALL_STICKERS_URL) {
                parameter("business", business.toString())
            }.also {
                logger.debug { "Got all stickers response: $it" }
            }
        }
}
