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
    @SerialName("aid") val aid: Int? = null,
    @SerialName("apiSeasonInfo") val apiSeasonInfo: Season? = null,
    @SerialName("bullet_count") val bulletCount: Int? = null,
    @SerialName("cover") val cover: String? = null,
    @SerialName("episode_id") val episodeId: Int? = null,
    @SerialName("index") val index: String? = null,
    @SerialName("index_title") val indexTitle: String? = null,
    @SerialName("new_desc") val newDesc: String? = null,
    @SerialName("online_finish") val onlineFinish: Int? = null,
    @SerialName("play_count") val playCount: Int? = null,
    @SerialName("reply_count") val replyCount: Int? = null,
    @SerialName("url") val url: String? = null,
) : FeedCard {
    @Serializable
    public data class Season(
        @SerialName("bgm_type") val type: BangumiType = UNKNOWN,
        @SerialName("cover") val cover: String? = null,
        @SerialName("is_finish") val isFinish: Boolean? = null,
        @SerialName("season_id") val seasonId: Int? = null,
        @SerialName("title") val title: String? = null,
        @SerialName("total_count") val totalCount: Int? = null,
        @SerialName("ts") val timestamp: Long? = null,
        @SerialName("type_name") val typeName: String? = null,
    )

    public companion object : FeedCardFactory() {
        override val code: Int = FeedType.BANGUMI.code

        override fun decode(json: Json, data: String): BangumiCard = json.decodeFromString(data)
    }
}
