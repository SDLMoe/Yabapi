@file:UseSerializers(BooleanJsSerializer::class)

package sdl.moe.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import sdl.moe.yabapi.data.GeneralCode
import sdl.moe.yabapi.data.GeneralCode.UNKNOWN
import sdl.moe.yabapi.serializer.BooleanJsSerializer

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
