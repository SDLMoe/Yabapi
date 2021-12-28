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
import sdl.moe.yabapi.consts.info.COIN_LOG_GET_URL
import sdl.moe.yabapi.consts.info.EXP_REWARD_GET_URL
import sdl.moe.yabapi.consts.info.REAL_NAME_DETAILED_GET_URL
import sdl.moe.yabapi.consts.info.REAL_NAME_INFO_GET_URL
import sdl.moe.yabapi.consts.info.SECURE_INFO_GET_URL
import sdl.moe.yabapi.consts.info.STAT_GET_URL
import sdl.moe.yabapi.consts.info.VIP_STAT_GET_URL
import sdl.moe.yabapi.data.info.AccountInfoGetResponse
import sdl.moe.yabapi.data.info.BasicInfoGetResponse
import sdl.moe.yabapi.data.info.CoinExpGetResponse
import sdl.moe.yabapi.data.info.CoinGetResponse
import sdl.moe.yabapi.data.info.CoinLogGetResponse
import sdl.moe.yabapi.data.info.ExpRewardGetResponse
import sdl.moe.yabapi.data.info.RealNameDetailedGetResponse
import sdl.moe.yabapi.data.info.RealNameInfoGetResponse
import sdl.moe.yabapi.data.info.SecureInfoGetResponse
import sdl.moe.yabapi.data.info.StatGetResponse
import sdl.moe.yabapi.data.info.VipStatGetResponse
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

    public suspend fun BiliClient.getExpReward(): ExpRewardGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Exp Reward..." }
        client.get<ExpRewardGetResponse>(EXP_REWARD_GET_URL).also {
            logger.debug { "Got Exp Reward Reponse: $it" }
        }
    }

    public suspend fun BiliClient.getCoinExp(): CoinExpGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Coin Exp..." }
        client.get<CoinExpGetResponse>(COIN_EXP_GET_URL).also {
            logger.debug { "Got Coin Exp Response: $it" }
        }
    }

    public suspend fun BiliClient.getVipStat(): VipStatGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Vip Stat..." }
        client.get<VipStatGetResponse>(VIP_STAT_GET_URL).also {
            logger.debug { "Got Vip Stat Response: $it" }
        }
    }

    public suspend fun BiliClient.getSecureInfo(): SecureInfoGetResponse = with(Platform.ioDispatcher) {
        logger.debug { "Getting Secure Info..." }
        client.get<SecureInfoGetResponse>(SECURE_INFO_GET_URL).also {
            logger.debug { "Got Secure Info: $it" }
        }
    }

    public suspend fun BiliClient.getRealNameInfo() : RealNameInfoGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Real Name Info..." }
        client.get<RealNameInfoGetResponse>(REAL_NAME_INFO_GET_URL).also {
            logger.debug { "Got Real Name Info: $it" }
        }
    }

    public suspend fun BiliClient.getRealNameDetailed(): RealNameDetailedGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Real Name Detailed..." }
        client.get<RealNameDetailedGetResponse>(REAL_NAME_DETAILED_GET_URL).also {
            logger.debug { "Got Real Name Detailed: $it" }
        }
    }

    public suspend fun BiliClient.getCoinLog() : CoinLogGetResponse = withContext(Platform.ioDispatcher) {
        logger.debug { "Getting Coin Log..." }
        client.get<CoinLogGetResponse>(COIN_LOG_GET_URL).also {
            logger.debug { "Got Coin Log: $it" }
        }
    }
}