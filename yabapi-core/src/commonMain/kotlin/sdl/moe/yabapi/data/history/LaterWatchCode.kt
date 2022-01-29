package sdl.moe.yabapi.data.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class LaterWatchCode {
    UNKNOWN,

    @SerialName("0")
    SUCCESS,

    @SerialName("-101")
    NOT_LOGIN,

    @SerialName("-111")
    CSRF_ERROR,

    @SerialName("-400")
    ERROR_REQUEST,

    @SerialName("90001")
    FULL,

    @SerialName("90003")
    DELETED_VIDEO,
}
