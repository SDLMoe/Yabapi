@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.video

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class VideoRights(
    @SerialName("bp") val bp: Int? = null,
    @SerialName("elec") val canCharge: Boolean? = null,
    @SerialName("download") val canDownload: Boolean? = null,
    @SerialName("movie") val isMovie: Boolean? = null,
    @SerialName("pay") val needPay: Boolean? = null,
    @SerialName("hd5") val isHighBitrate: Boolean? = null,
    @SerialName("no_reprint") val showNoReprint: Boolean? = null,
    @SerialName("autoplay") val isAutoplay: Boolean? = null,
    @SerialName("ugc_pay") val isUgcPay: Boolean? = null,
    @SerialName("is_stein_gate") val isInteractive: Boolean? = null,
    @SerialName("is_cooperation") val isCooperation: Boolean? = null,
    @SerialName("ugc_pay_preview") val canPayPreview: Boolean? = null,
    @SerialName("no_background") val noBackground: Boolean? = null,
    @SerialName("clean_mode") val isCleanMode: Boolean? = null,
    @SerialName("is_360") val isPanoramic: Boolean? = null,
    @SerialName("no_share") val noShare: Boolean? = null,
)
