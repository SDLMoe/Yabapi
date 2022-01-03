// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.GeneralCode.SUCCESS
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN

/**
 * 通用的状态码枚举类
 * @property UNKNOWN 未知状态码
 * @property SUCCESS 成功
 *
 * 更多参见: [https://github.com/SocialSisterYi/bilibili-API-collect/blob/master/other/errcode.md]
 */
@Serializable
public enum class GeneralCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-1")
    INVALID_APP_OR_BANNED,

    @SerialName("-2")
    INVALID_ACCESS_KEY,

    @SerialName("-3")
    INVALID_TOKEN_KEY,

    @SerialName("-4")
    NO_PREMISSION,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-102")
    BANNED,

    @SerialName("-103")
    LESS_CREDIT,

    @SerialName("-104")
    LESS_COIN,

    @SerialName("-105")
    INVALID_CAPTCHA,

    @SerialName("-106")
    GUEST,

    @SerialName("-107")
    INVALID_OR_BANNED_USER,

    @SerialName("-108")
    PHONE_UNBIND,

    @SerialName("-110")
    PHONE_UNBIND_2,

    @SerialName("-111")
    INVALID_CSRF,

    @SerialName("-112")
    MAINTENANCE,

    @SerialName("-113")
    UNCREDIT,

    @SerialName("-114")
    BIND_PHONE_FIRST,

    @SerialName("-115")
    CREDNT_FIRST,

    @SerialName("-304")
    NO_DIFFER,

    @SerialName("-307")
    DUPLICATE_VIDEO,

    @SerialName("-400")
    ERROR_REQUEST,

    @SerialName("-401")
    UNCERTIFICATED,

    @SerialName("-403")
    FORBIDDEN,

    @SerialName("-404")
    NOT_FOUND,

    @SerialName("-405")
    METHOD_NOT_ALLOWED,

    @SerialName("-409")
    CONFLICTED,

    @SerialName("-500")
    SERVER_ERROR,

    @SerialName("-503")
    OVERLOADED,

    @SerialName("-504")
    TIMEOUT,

    @SerialName("-509")
    LIMITED,

    @SerialName("-616")
    NO_SUCH_FILE,

    @SerialName("-617")
    FILE_TOO_LARGE,

    @SerialName("-625")
    LOGIN_FAILED_FREQUENT,

    @SerialName("-626")
    USER_NOT_EXIST,

    @SerialName("-628")
    WEAK_PASSWORD,

    @SerialName("-629")
    ERROR_ACCOUNT,

    @SerialName("-632")
    AMOUNT_LIMIT,

    @SerialName("-643")
    LOCKED,

    @SerialName("-650")
    LEVEL_TOO_LOW,

    @SerialName("-652")
    USER_DUPLICATED,

    @SerialName("-658")
    TOKEN_EXPIRED,

    @SerialName("-662")
    PWD_TIMESTAMP_EXPIRED,

    @SerialName("-688")
    AREA_LIMIT,

    @SerialName("-689")
    COPYRIGHT_LIMIT,

    @SerialName("-701")
    MINUS_CREDIT_FAILED,

    @SerialName("2202")
    ILLEGAL_CSRF,

    @SerialName("-8888")
    SERVICE_DOWN;
}
