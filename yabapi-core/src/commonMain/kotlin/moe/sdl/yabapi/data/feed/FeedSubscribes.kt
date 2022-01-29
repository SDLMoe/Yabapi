package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.bangumi.BangumiType

@Serializable
public data class FeedSubscribes(
    @SerialName("uids") val uids: List<Int> = emptyList(),
    @SerialName("bangumis") val bangumis: List<FeedBangumiSub> = emptyList(),
)

@Serializable
public data class FeedBangumiSub(
    @SerialName("season_id") val seasonId: Int,
    @SerialName("type") val type: BangumiType? = BangumiType.UNKNOWN,
)
