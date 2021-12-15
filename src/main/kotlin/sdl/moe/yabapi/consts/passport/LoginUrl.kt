package sdl.moe.yabapi.consts.passport

import sdl.moe.yabapi.consts.PASSPORT

internal const val QUERY_CAPTCHA_URL: String = "$PASSPORT/web/captcha/combine?plat=6"

/** params("act","getKey") */
internal const val RSA_GET_WEB_URL: String = "$PASSPORT/login"

internal const val RSA_GET_APP_URL: String = "$PASSPORT/api/oauth2/getKey"