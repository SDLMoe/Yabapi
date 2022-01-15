// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.GET_ALL_STICKERS_URL
import sdl.moe.yabapi.data.sticker.AllStickersGetResponse
import sdl.moe.yabapi.enums.StickerBusiness
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("StickerApi")

/**
 * 表情包相关 API
 */

/**
 * 获取站内可用的所有表情包
 * 实际返回会根据用户而异, 并非真正的所有表情
 * @param business 使用場景 [StickerBusiness]
 */
public suspend fun BiliClient.getAllStickers(
    business: StickerBusiness,
    context: CoroutineContext = this.context,
): AllStickersGetResponse = withContext(context) {
    logger.debug { "Getting all stickers for business: $business" }
    client.get<AllStickersGetResponse>(GET_ALL_STICKERS_URL) {
        parameter("business", business.toString())
    }.also {
        logger.debug { "Got all stickers response: $it" }
    }
}
