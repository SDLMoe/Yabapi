// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode

/**
 * @param code 返回值 [GeneralCode]
 * @param data [ExpReward]
 */
@Serializable
public data class ExpRewardGetResponse(
    @SerialName("code") val code: GeneralCode,
    @SerialName("message") val message: String,
    @SerialName("ttl") val ttl: Int,
    @SerialName("data") val data: ExpReward,
)

private const val LOGIN_EXP = 5
private const val WATCH_EXP = 5
private const val SHARE_EXP = 5

@Serializable
public data class ExpReward(
    @SerialName("login") val isLogin: Boolean,
    @SerialName("watch") val isWatched: Boolean,
    @SerialName("coins") val coinExp: Int,
    @SerialName("share") val isShared: Boolean,
    @SerialName("email") val isEmailVerified: Boolean,
    @SerialName("tel") val isPhoneVerified: Boolean,
    @SerialName("safe_question") val hasSafeQuestion: Boolean,
    @SerialName("identify_card") val isIdCardVerified: Boolean,
) {
    public fun countTodayRewarded(): Int {
        var rewarded = 0
        if (isLogin) rewarded += LOGIN_EXP
        if (isWatched) rewarded += WATCH_EXP
        if (isShared) rewarded += SHARE_EXP
        rewarded += coinExp
        return rewarded
    }

    public fun replaceWithCoinExp(data: CoinExpGetResponse): ExpReward = this.copy(coinExp = data.coinExp)
}
