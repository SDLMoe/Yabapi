// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.consts.info

import sdl.moe.yabapi.consts.MAIN
import sdl.moe.yabapi.consts.PASSPORT
import sdl.moe.yabapi.consts.WWW

internal const val ACCOUNT_INFO_GET_URL: String = "$MAIN/x/member/web/account"

internal const val EXP_REWARD_GET_URL: String = "$MAIN/x/member/web/exp/reward"

internal const val COIN_EXP_GET_URL: String = "$WWW/plus/account/exp.php"

internal const val VIP_STAT_GET_URL: String = "$MAIN/x/vip/web/user/info"

internal const val SECURE_INFO_GET_URL: String = "$PASSPORT/web/site/user/info"

internal const val REAL_NAME_INFO_GET_URL: String = "$MAIN/x/member/realname/status"

internal const val REAL_NAME_DETAILED_GET_URL: String = "$MAIN/x/member/realname/apply/status"

internal const val COIN_LOG_GET_URL: String = "$MAIN/x/member/web/coin/log"

internal const val CHANGE_BIO_URL: String = "$MAIN/x/member/web/sign/update"
