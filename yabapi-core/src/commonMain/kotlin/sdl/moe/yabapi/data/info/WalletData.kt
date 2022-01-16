package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param mid 用户 mid
 * @param bcoinBalance 目前 B 币数
 * @param couponBalance 每月奖励 B 币数
 * @param couponDueTime 可能为奖励到期时间, 但返回为 `0`
 */
@Serializable
public data class Wallet(
    @SerialName("mid") val mid: Int,
    @SerialName("bcoin_balance") val bcoinBalance: Int,
    @SerialName("coupon_balance") val couponBalance: Int,
    @SerialName("coupon_due_time") val couponDueTime: Long,
)
