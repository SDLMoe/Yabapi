@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import moe.sdl.yabapi.data.bangumi.BangumiType
import moe.sdl.yabapi.data.bangumi.BangumiType.UNKNOWN
import moe.sdl.yabapi.enums.feed.FeedType
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class BangumiCard(
    @SerialName("aid") val aid: Int,
    @SerialName("apiSeasonInfo") val apiSeasonInfo: Season,
    @SerialName("bullet_count") val bulletCount: Int,
    @SerialName("cover") val cover: String,
    @SerialName("episode_id") val episodeId: Int,
    @SerialName("index") val index: String,
    @SerialName("index_title") val indexTitle: String,
    @SerialName("new_desc") val newDesc: String,
    @SerialName("online_finish") val onlineFinish: Int,
    @SerialName("play_count") val playCount: Int,
    @SerialName("reply_count") val replyCount: Int,
    @SerialName("url") val url: String,
) : FeedCard {
    @Serializable
    public data class Season(
        @SerialName("bgm_type") val type: BangumiType = UNKNOWN,
        @SerialName("cover") val cover: String,
        @SerialName("is_finish") val isFinish: Boolean,
        @SerialName("season_id") val seasonId: Int,
        @SerialName("title") val title: String,
        @SerialName("total_count") val totalCount: Int,
        @SerialName("ts") val timestamp: Long,
        @SerialName("type_name") val typeName: String,
    )

    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.ARTICLE.code

        override fun decode(json: Json, data: String): BangumiCard = json.decodeFromString(data)
    }
}
