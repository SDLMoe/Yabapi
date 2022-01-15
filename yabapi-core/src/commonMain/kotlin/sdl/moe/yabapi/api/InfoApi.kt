// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.api

import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import sdl.moe.yabapi.BiliClient
import sdl.moe.yabapi.consts.internal.ACCOUNT_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.BASIC_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_EXP_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_GET_URL
import sdl.moe.yabapi.consts.internal.COIN_LOG_GET_URL
import sdl.moe.yabapi.consts.internal.EXP_REWARD_GET_URL
import sdl.moe.yabapi.consts.internal.MY_SPACE_GET_URL
import sdl.moe.yabapi.consts.internal.NICK_CHECK_URL
import sdl.moe.yabapi.consts.internal.REAL_NAME_DETAILED_GET_URL
import sdl.moe.yabapi.consts.internal.REAL_NAME_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.SECURE_INFO_GET_URL
import sdl.moe.yabapi.consts.internal.STAT_GET_URL
import sdl.moe.yabapi.consts.internal.USER_CARD_GET_URL
import sdl.moe.yabapi.consts.internal.USER_SPACE_GET_URL
import sdl.moe.yabapi.consts.internal.VIP_STAT_GET_URL
import sdl.moe.yabapi.data.info.AccountInfoGetResponse
import sdl.moe.yabapi.data.info.BasicInfoGetResponse
import sdl.moe.yabapi.data.info.CheckNickResponse
import sdl.moe.yabapi.data.info.CoinExpGetResponse
import sdl.moe.yabapi.data.info.CoinGetResponse
import sdl.moe.yabapi.data.info.CoinLogGetResponse
import sdl.moe.yabapi.data.info.ExpRewardGetResponse
import sdl.moe.yabapi.data.info.MySpaceGetResponse
import sdl.moe.yabapi.data.info.RealNameDetailedGetResponse
import sdl.moe.yabapi.data.info.RealNameInfoGetResponse
import sdl.moe.yabapi.data.info.SecureInfoGetResponse
import sdl.moe.yabapi.data.info.StatGetResponse
import sdl.moe.yabapi.data.info.UserCardGetResponse
import sdl.moe.yabapi.data.info.UserSpaceGetResponse
import sdl.moe.yabapi.data.info.VipStatGetResponse
import sdl.moe.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("InfoApi")

public suspend fun BiliClient.getBasicInfo(
    context: CoroutineContext = this.context,
): BasicInfoGetResponse = withContext(context) {
    logger.debug { "Getting basic info..." }
    client.get<BasicInfoGetResponse>(BASIC_INFO_GET_URL).also {
        logger.debug { "Basic info response: $it" }
    }
}

public suspend fun BiliClient.getStat(
    context: CoroutineContext = this.context,
): StatGetResponse = withContext(context) {
    logger.debug { "Getting stat info..." }
    client.get<StatGetResponse>(STAT_GET_URL).also {
        logger.debug { "Got stat info response: $it" }
    }
}

public suspend fun BiliClient.getCoinInfo(
    context: CoroutineContext = this.context,
): CoinGetResponse = withContext(context) {
    logger.debug { "Getting coin number..." }
    client.get<CoinGetResponse>(COIN_GET_URL).also {
        logger.debug { "Got Coin info response: $it" }
    }
}

public suspend fun BiliClient.getAccountInfo(
    context: CoroutineContext = this.context,
): AccountInfoGetResponse = withContext(context) {
    logger.debug { "Getting Account Info..." }
    client.get<AccountInfoGetResponse>(ACCOUNT_INFO_GET_URL).also {
        logger.debug { "Got Account Info Response: $it" }
    }
}

public suspend fun BiliClient.getExpReward(
    context: CoroutineContext = this.context,
): ExpRewardGetResponse = withContext(context) {
    logger.debug { "Getting Exp Reward..." }
    client.get<ExpRewardGetResponse>(EXP_REWARD_GET_URL).also {
        logger.debug { "Got Exp Reward Reponse: $it" }
    }
}

public suspend fun BiliClient.getCoinExp(
    context: CoroutineContext = this.context,
): CoinExpGetResponse = withContext(context) {
    logger.debug { "Getting Coin Exp..." }
    client.get<CoinExpGetResponse>(COIN_EXP_GET_URL).also {
        logger.debug { "Got Coin Exp Response: $it" }
    }
}

public suspend fun BiliClient.getVipStat(
    context: CoroutineContext = this.context,
): VipStatGetResponse = withContext(context) {
    logger.debug { "Getting Vip Stat..." }
    client.get<VipStatGetResponse>(VIP_STAT_GET_URL).also {
        logger.debug { "Got Vip Stat Response: $it" }
    }
}

public suspend fun BiliClient.getSecureInfo(
    context: CoroutineContext = this.context,
): SecureInfoGetResponse = with(context) {
    logger.debug { "Getting Secure Info..." }
    client.get<SecureInfoGetResponse>(SECURE_INFO_GET_URL).also {
        logger.debug { "Got Secure Info: $it" }
    }
}

public suspend fun BiliClient.getRealNameInfo(
    context: CoroutineContext = this.context,
): RealNameInfoGetResponse = withContext(context) {
    logger.debug { "Getting Real Name Info..." }
    client.get<RealNameInfoGetResponse>(REAL_NAME_INFO_GET_URL).also {
        logger.debug { "Got Real Name Info: $it" }
    }
}

public suspend fun BiliClient.getRealNameDetailed(
    context: CoroutineContext = this.context,
): RealNameDetailedGetResponse =
    withContext(context) {
        logger.debug { "Getting Real Name Detailed..." }
        client.get<RealNameDetailedGetResponse>(REAL_NAME_DETAILED_GET_URL).also {
            logger.debug { "Got Real Name Detailed: $it" }
        }
    }

public suspend fun BiliClient.getCoinLog(
    context: CoroutineContext = this.context,
): CoinLogGetResponse = withContext(context) {
    logger.debug { "Getting Coin Log..." }
    client.get<CoinLogGetResponse>(COIN_LOG_GET_URL).also {
        logger.debug { "Got Coin Log: $it" }
    }
}

public suspend fun BiliClient.getUserSpace(
    mid: Int,
    context: CoroutineContext = this.context,
): UserSpaceGetResponse = withContext(context) {
    logger.debug { "Getting User Space Info..." }
    client.get<UserSpaceGetResponse>(USER_SPACE_GET_URL) {
        parameter("mid", mid.toString())
    }.also {
        logger.debug { "Got User $mid Space Info: $it" }
    }
}

public suspend fun BiliClient.getUserCard(
    mid: Int,
    requestBanner: Boolean,
    context: CoroutineContext = this.context,
): UserCardGetResponse =
    withContext(context) {
        logger.debug { "Getting User Card Info..." }
        client.get<UserCardGetResponse>(USER_CARD_GET_URL) {
            parameter("mid", mid.toString())
            parameter("photo", requestBanner.toString())
        }.also {
            logger.debug { "Got User $mid Card Info: $it" }
        }
    }

public suspend fun BiliClient.getMySpace(
    context: CoroutineContext = this.context,
): MySpaceGetResponse = withContext(context) {
    logger.debug { "Getting Current User Space Info:" }
    client.get<MySpaceGetResponse>(MY_SPACE_GET_URL).also {
        logger.debug { "Got Current User Space Info: $it" }
    }
}

public suspend fun BiliClient.checkNick(
    nick: String,
    context: CoroutineContext = this.context,
): CheckNickResponse = withContext(context) {
    logger.debug { "Checking Nick Status..." }
    client.get<CheckNickResponse>(NICK_CHECK_URL) {
        parameter("nickName", nick)
    }.also {
        logger.debug { "Nick \"$nick\" status: $it" }
    }
}
