package moe.sdl.yabapi.data.bangumi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class BangumiType {
    UNKNOWN,
    @SerialName("1")
    ANIME, // 番剧
    @SerialName("2")
    MOVIE, // 电影
    @SerialName("3")
    DOCUMENTARY, // 纪录片
    @SerialName("4")
    GUOCHUANG, // 国创
    @SerialName("5")
    SERIES, // 电视剧
    @SerialName("7")
    VARIETY, // 综艺
    ;
}
