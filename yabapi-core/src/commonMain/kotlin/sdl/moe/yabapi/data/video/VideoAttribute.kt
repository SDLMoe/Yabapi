package sdl.moe.yabapi.data.video

import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.video.VideoAttributeSerializer

@Serializable(VideoAttributeSerializer::class)
public data class VideoAttribute(
    val noRanking: Boolean = false,
    val noFeed: Boolean = false,
    val noWeb: Boolean = false,
    val noApp: Boolean = false,
    val noSearch: Boolean = false,
    val noRecommend: Boolean = false,
    val isPgc: Boolean = false,
    val notReportedAd: Boolean = false, // 私單廣告
    val isAreaLimited: Boolean = false,
    val blockAddTags: Boolean = false,
) {
    public fun encode(): Int = listOf(
        noRanking to 0,
        noFeed to 1,
        noWeb to 2,
        noApp to 3,
        noSearch to 4,
        noRecommend to 6,
        isPgc to 9,
        notReportedAd to 12,
        isAreaLimited to 13,
        blockAddTags to 14,
    ).booleansToInt()

    public companion object {
        public fun decode(data: Int): VideoAttribute = data.intToBooleans().let {
            VideoAttribute(
                noRanking = it[0],
                noFeed = it[1],
                noWeb = it[2],
                noApp = it[3],
                noSearch = it[4],
                noRecommend = it[6],
                isPgc = it[9],
                notReportedAd = it[12],
                isAreaLimited = it[13],
                blockAddTags = it[14],
            )
        }
    }
}

private fun List<Pair<Boolean, Int>>.booleansToInt(): Int =
    asSequence()
        .filter { it.first }
        .fold(0) { acc, (_, bit) ->
            acc or (1 shl bit)
        }

private fun Int.intToBooleans(): BooleanArray {
    val result = BooleanArray(32)
    for (i in 0..31) {
        result[i] = (this shr i) and 1 == 1
    }
    return result
}
