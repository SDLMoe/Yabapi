@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.feed

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.data.GeneralCode
import moe.sdl.yabapi.data.GeneralCode.UNKNOWN
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class FeedUpdatedResponse(
    @SerialName("code") val code: GeneralCode = UNKNOWN,
    @SerialName("msg") val msg: String? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("data") val data: FeedUpdatedData? = null,
)

@Serializable
public data class FeedUpdatedData(
    @SerialName("button_statement") val buttonStatement: String? = null,
    @SerialName("items") val items: List<UpdatedNode> = emptyList(),
    @SerialName("_gt_") val gt: Int? = null,
)

@Serializable
public data class UpdatedNode(
    @SerialName("user_profile") val userProfile: UpdatedUser,
    @SerialName("has_update") val hasUpdate: Boolean? = null,
    @SerialName("is_reserve_recall") val isReserveRecall: String? = null,
)

@Serializable
public data class UpdatedUser(
    @SerialName("info") val info: Info? = null,
) {
    @Serializable
    public data class Info(
        @SerialName("uid") val uid: Long? = null,
        @SerialName("uname") val userName: String? = null,
        @SerialName("face") val face: String? = null,
    )
}
