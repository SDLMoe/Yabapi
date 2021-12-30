// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.info

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.CheckNickResponseCode.AVAILABLE

@Serializable
public data class CheckNickResponse(
    @SerialName("code") val code: CheckNickResponseCode = CheckNickResponseCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ts") val timestamp: Long? = null,
) {
    val isAvailable: Boolean = code == AVAILABLE
}

@Serializable
public enum class CheckNickResponseCode {
    UNKNOWN,

    @SerialName("0")
    AVAILABLE,

    @SerialName("2001")
    USED,

    @SerialName("40002")
    SENSITIVE,

    @SerialName("40004")
    SPECIAL_CHARACTER,

    @SerialName("40005")
    TOO_LONG,

    @SerialName("40006")
    TOO_SHORT,

    @SerialName("40014")
    ALREADY_HAS,

    @SerialName("-400")
    REQUEST_ERROR,

    @SerialName("-500")
    SERVER_ERROR,
}
