@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.message

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class MessageResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("ttl") val ttl: Int? = null,
    @SerialName("data") val data: Data? = null,
) {
    @Serializable
    public data class Data(
        @SerialName("messages") val messages: List<RecvMessage> = emptyList(),
        @SerialName("has_more") val hasMore: Boolean? = null,
        @SerialName("min_seqno") val minSeq: ULong? = null,
        @SerialName("max_seqno") val maxSeq: ULong? = null,
        @SerialName("e_infos") val emoticons: List<Emoticon>? = null,
    )

    @Serializable
    public data class Emoticon(
        @SerialName("text") val text: String? = null,
        @SerialName("url") val url: String? = null,
        @SerialName("size") val size: Int? = null,
    )
}
