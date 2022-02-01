@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonArray
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class FeedData(
    @SerialName("new_num") val newNum: Int? = null,
    @SerialName("exist_gap") val existGap: Boolean? = null,
    @SerialName("update_num") val updateNum: Int? = null,
    @SerialName("has_more") val hasMore: Boolean? = null,
    @SerialName("open_rcmd") val openRecommend: Boolean? = null,
    @SerialName("fold_mgr") val foldManager: List<FoldManager> = emptyList(),
    @SerialName("cards") val cards: List<FeedCardNode> = emptyList(),
    @SerialName("attentions") val subs: FeedSubscribes? = null,
    @SerialName("max_dynamic_id") val maxFeedId: ULong? = null,
    @SerialName("history_offset") val historyOffset: ULong? = null,
    @SerialName("next_offset") val nextOffset: ULong? = null,
    @SerialName("rcmd_cards") val rcmdCards: JsonArray? = null,
    @SerialName("_gt_") val gt: Int? = null,
) {
    @Serializable
    public data class FoldManager(
        @SerialName("fold_type") val foldType: Int? = null,
        @SerialName("folds") val folds: JsonArray? = null,
    )
}
