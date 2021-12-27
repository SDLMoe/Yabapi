// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.info.BASIC_INFO_GET_URL
import sdl.moe.yabapi.consts.info.COIN_GET_URL
import sdl.moe.yabapi.consts.info.STAT_GET_URL
import sdl.moe.yabapi.data.info.BasicInfoGetResponse
import sdl.moe.yabapi.data.info.CoinGetResponse
import sdl.moe.yabapi.data.info.StatGetResponse
import sdl.moe.yabapi.util.logger

public object InfoApi : BiliApi {
    override val apiName: String = "info"

    init {
        BiliClient.registerApi(this)
    }

    @Suppress("unused")
    public val BiliClient.infoApi: InfoApi
        get() = this@InfoApi

    public suspend fun BiliClient.getBasicInfo(): BasicInfoGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting basic info..." }
        client.get<BasicInfoGetResponse>(BASIC_INFO_GET_URL).also {
            logger.debug { "Basic info response: $it" }
        }
    }

    public suspend fun BiliClient.getStat(): StatGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting stat info..." }
        client.get<StatGetResponse>(STAT_GET_URL).also {
            logger.debug { "Stat info response: $it" }
        }
    }

    public suspend fun BiliClient.getCoinInfo(): CoinGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting coin number..." }
        client.get<CoinGetResponse>(COIN_GET_URL).also {
            logger.debug { "Coin info response: $it" }
        }
    }
}
