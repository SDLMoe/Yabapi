package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 視頻數據
 * @param aid av id
 * @param view 播放
 * @param danmaku 彈幕
 * @param reply 回覆
 * @param collect 收藏
 * @param coin 硬幣
 * @param share 分享
 * @param nowRank 當前排名
 * @param highestRank 歷史最高排名
 * @param like 點讚數
 * @param dislike 點踩數, 恆爲 `0`
 * @param evaluation 視頻評分
 * @param argueMsg 爭議信息
 */
@Serializable
public data class VideoStat(
    @SerialName("aid") val aid: Int? = null,
    @SerialName("view") val view: Int? = null,
    @SerialName("danmaku") val danmaku: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("favorite") val collect: Int? = null,
    @SerialName("coin") val coin: Int? = null,
    @SerialName("share") val share: Int? = null,
    @SerialName("now_rank") val nowRank: Int? = null,
    @SerialName("his_rank") val highestRank: Int? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("dislike") val dislike: Int? = null,
    @SerialName("evaluation") val evaluation: String? = null,
    @SerialName("argue_msg") val argueMsg: String? = null,
)

// just tiny difference, can replace with @JsonNames, but it's in Experimental
@Serializable
public data class UgcSeasonStat(
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("aid") val aid: Int? = null,
    @SerialName("view") val view: Int? = null,
    @SerialName("danmaku") val danmaku: Int? = null,
    @SerialName("reply") val reply: Int? = null,
    @SerialName("fav") val collect: Int? = null,
    @SerialName("coin") val coin: Int? = null,
    @SerialName("share") val share: Int? = null,
    @SerialName("now_rank") val nowRank: Int? = null,
    @SerialName("his_rank") val highestRank: Int? = null,
    @SerialName("like") val like: Int? = null,
    @SerialName("dislike") val dislike: Int? = null,
    @SerialName("evaluation") val evaluation: String? = null,
    @SerialName("argue_msg") val argueMsg: String? = null,
)
