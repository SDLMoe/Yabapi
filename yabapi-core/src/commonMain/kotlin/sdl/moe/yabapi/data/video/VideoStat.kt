// © Copyright 2022 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.video

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
    @SerialName("aid") val aid: Int,
    @SerialName("view") val view: Int,
    @SerialName("danmaku") val danmaku: Int,
    @SerialName("reply") val reply: Int,
    @SerialName("favorite") val collect: Int,
    @SerialName("coin") val coin: Int,
    @SerialName("share") val share: Int,
    @SerialName("now_rank") val nowRank: Int,
    @SerialName("his_rank") val highestRank: Int,
    @SerialName("like") val like: Int,
    @SerialName("dislike") val dislike: Int,
    @SerialName("evaluation") val evaluation: String,
    @SerialName("argue_msg") val argueMsg: String,
)


// just tiny difference, can replace with @JsonNames, but it's in Experimental
@Serializable
public data class UgcSeasonStat(
    @SerialName("season_id") val seasonId: Int? = null,
    @SerialName("aid") val aid: Int? = null,
    @SerialName("view") val view: Int,
    @SerialName("danmaku") val danmaku: Int,
    @SerialName("reply") val reply: Int,
    @SerialName("fav") val collect: Int,
    @SerialName("coin") val coin: Int,
    @SerialName("share") val share: Int,
    @SerialName("now_rank") val nowRank: Int,
    @SerialName("his_rank") val highestRank: Int,
    @SerialName("like") val like: Int,
    @SerialName("dislike") val dislike: Int? = null,
    @SerialName("evaluation") val evaluation: String? = null,
    @SerialName("argue_msg") val argueMsg: String? = null,
)
