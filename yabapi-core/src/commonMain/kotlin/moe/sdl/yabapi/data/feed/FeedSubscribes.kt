package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import moe.sdl.yabapi.data.bangumi.BangumiType

@Serializable
public data class FeedSubscribes(
    @SerialName("uids") val uids: List<Long> = emptyList(),
    @SerialName("bangumis") val bangumis: List<FeedBangumiSub> = emptyList(),
)

@Serializable
public data class FeedBangumiSub(
    @SerialName("season_id") val seasonId: Long? = null,
    @SerialName("type") val type: BangumiType? = BangumiType.UNKNOWN,
)
