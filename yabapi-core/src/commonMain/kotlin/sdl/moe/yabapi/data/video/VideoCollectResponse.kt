// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class VideoCollectResponse(
    @SerialName("code") val code: VideoCollectCode = VideoCollectCode.UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: VideoCollectData? = null,
)

/**
 * @param isCollectByNonSubscriber 是不是收藏了但沒關注
 */
@Serializable
public data class VideoCollectData(
    @SerialName("prompt") val isCollectByNonSubscriber: Boolean,
)

@Serializable
public enum class VideoCollectCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-111")
    CSRF_ERROR,

    @SerialName("-400")
    INVALID_REQUEST,

    @SerialName("-403")
    NO_PERMISSION,

    @SerialName("10003")
    NO_SUCH_VIDEO,

    @SerialName("11201")
    COLLECTED,

    @SerialName("11202")
    CANCELED,

    @SerialName("11203")
    FOLDER_FULL,

    @SerialName("2001000")
    ERROR_PARAMETER,
}
