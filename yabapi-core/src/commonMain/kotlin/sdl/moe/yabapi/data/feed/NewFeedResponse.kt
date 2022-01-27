@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class NewFeedResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: NewFeedData? = null,
)

@Serializable
public data class NewFeedData(
    @SerialName("new_num") val newNum: Int,
    @SerialName("exist_gap") val existGap: Boolean,
    @SerialName("update_num") val updateNum: Int,
    @SerialName("open_rcmd") val openRecommend: Boolean,
    @SerialName("cards") val cards: List<FeedCardNode> = emptyList(),
    @SerialName("attentions") val subs: FeedSubscribes,
    @SerialName("max_dynamic_id") val maxFeedId: ULong,
    @SerialName("history_offset") val historyOffset: ULong,
    @SerialName("_gt_") val gt: Int? = null,
)
