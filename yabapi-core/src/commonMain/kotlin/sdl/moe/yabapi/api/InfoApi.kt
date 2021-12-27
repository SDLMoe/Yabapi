// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.consts.info.ACCOUNT_INFO_GET_URL
import sdl.moe.yabapi.consts.info.BASIC_INFO_GET_URL
import sdl.moe.yabapi.consts.info.COIN_EXP_GET_URL
import sdl.moe.yabapi.consts.info.COIN_GET_URL
import sdl.moe.yabapi.consts.info.EXP_REWARD_GET_URL
import sdl.moe.yabapi.consts.info.STAT_GET_URL
import sdl.moe.yabapi.data.info.AccountInfoGetResponse
import sdl.moe.yabapi.data.info.BasicInfoGetResponse
import sdl.moe.yabapi.data.info.CoinExpGetResponse
import sdl.moe.yabapi.data.info.CoinGetResponse
import sdl.moe.yabapi.data.info.ExpRewardGetResponse
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
            logger.debug { "Got stat info response: $it" }
        }
    }

    public suspend fun BiliClient.getCoinInfo(): CoinGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting coin number..." }
        client.get<CoinGetResponse>(COIN_GET_URL).also {
            logger.debug { "Got Coin info response: $it" }
        }
    }

    public suspend fun BiliClient.getAccountInfo(): AccountInfoGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Account Info..." }
        client.get<AccountInfoGetResponse>(ACCOUNT_INFO_GET_URL).also {
            logger.debug { "Got Account Info Response: $it" }
        }
    }

    public suspend fun BiliClient.getExpReward(): ExpRewardGetResponse {
        logger.debug { "Getting Exp Reward..." }
        return client.get<ExpRewardGetResponse>(EXP_REWARD_GET_URL).also {
            logger.debug { "Got Exp Reward Reponse: $it" }
        }
    }

    public suspend fun BiliClient.getCoinExp(): CoinExpGetResponse {
        logger.debug { "Getting Coin Exp..." }

        return client.get<CoinExpGetResponse>(COIN_EXP_GET_URL).also {
            logger.debug { "Got Coin Exp Response: $it" }
        }
    }
}
