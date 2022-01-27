package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import sdl.moe.yabapi.data.info.LevelInfo
import sdl.moe.yabapi.data.info.Pendant

@Serializable
public data class FeedUserProfile(
    @SerialName("info") val info: UserInfo,
    @SerialName("card") val card: FeedUserCard,
    @SerialName("vip") val vip: FeedVip,
    @SerialName("pendant") val pendant: Pendant,
    @SerialName("decorate_card") val decorateCard: FeedDecorateCard? = null,
    @SerialName("rank") val rank: String,
    @SerialName("sign") val sign: String,
    @SerialName("level_info") val levelInfo: LevelInfo,
)
