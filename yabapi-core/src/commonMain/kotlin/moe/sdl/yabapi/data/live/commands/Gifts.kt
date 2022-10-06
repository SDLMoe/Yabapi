package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
public data class BatchComboGift(
    @SerialName("action") val action: String? = null,
    @SerialName("batch_combo_id") val batchComboId: String? = null,
    @SerialName("batch_combo_num") val batchComboNum: Int? = null,
    @SerialName("blind_gift") val blindGift: BlindGift? = null,
    @SerialName("gift_id") val giftId: Long? = null,
    @SerialName("gift_name") val giftName: String? = null,
    @SerialName("gift_num") val giftNum: Int? = null,
    @SerialName("send_master") val sendMaster: JsonElement? = null,
    @SerialName("uid") val uid: Long? = null,
    @SerialName("uname") val username: String? = null,
)

/**
 * 盲盒
 */
@Serializable
public data class BlindGift(
    @SerialName("blind_gift_config_id") val blindGiftConfigId: Long? = null,
    @SerialName("from") val from: Int? = null,
    @SerialName("gift_action") val giftAction: String? = null,
    @SerialName("original_gift_id") val originalGiftId: Long? = null,
    @SerialName("original_gift_name") val originalGiftName: String? = null,
)
