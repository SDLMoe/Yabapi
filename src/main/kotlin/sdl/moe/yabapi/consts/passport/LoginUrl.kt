// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.consts.passport

import sdl.moe.yabapi.consts.PASSPORT

internal const val QUERY_CAPTCHA_URL: String = "$PASSPORT/web/captcha/combine?plat=6"

/** params("act","getKey") */
internal const val RSA_GET_WEB_URL: String = "$PASSPORT/login"

internal const val RSA_GET_APP_URL: String = "$PASSPORT/api/oauth2/getKey"

internal const val LOGIN_WEB_URL: String = "$PASSPORT/web/login/v2"

internal const val LOGIN_QRCODE_GET_WEB_URL: String = "$PASSPORT/qrcode/getLoginUrl"

internal const val LOGIN_WEB_QRCODE_URL = "$PASSPORT/qrcode/getLoginInfo"

internal const val GET_CALLING_CODE_URL: String = "$PASSPORT/web/generic/country/list"

internal const val SEND_SMS_URL: String = "$PASSPORT/web/sms/general/v2/send"

internal const val LOGIN_WEB_SMS_URL: String = "$PASSPORT/x/passport-login/web/login/sms"
