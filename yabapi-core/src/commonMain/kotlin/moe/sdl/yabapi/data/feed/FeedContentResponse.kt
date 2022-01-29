@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class FeedContentResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("message") val message: String? = null,
    @SerialName("msg") val msg: String? = null,
    @SerialName("data") val data: FeedContent? = null,
)

@Serializable
public data class FeedContent(
    @SerialName("card") val card: FeedCardNode? = null,
    @SerialName("result") val result: Int? = null,
    @SerialName("_gt_") val gt: Int? = null,
    @SerialName("attentions") val subs: FeedSubscribes? = null,
)
