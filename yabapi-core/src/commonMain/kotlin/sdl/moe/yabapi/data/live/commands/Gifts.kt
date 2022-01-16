package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
public data class BatchComboGift(
    @SerialName("action") val action: String,
    @SerialName("batch_combo_id") val batchComboId: String,
    @SerialName("batch_combo_num") val batchComboNum: Int,
    @SerialName("blind_gift") val blindGift: BlindGift? = null,
    @SerialName("gift_id") val giftId: Int,
    @SerialName("gift_name") val giftName: String,
    @SerialName("gift_num") val giftNum: Int,
    @SerialName("send_master") val sendMaster: JsonElement? = null,
    @SerialName("uid") val uid: Int,
    @SerialName("uname") val username: String,
)

/**
 * 盲盒
 */
@Serializable
public data class BlindGift(
    @SerialName("blind_gift_config_id") val blindGiftConfigId: Int,
    @SerialName("from") val from: Int,
    @SerialName("gift_action") val giftAction: String,
    @SerialName("original_gift_id") val originalGiftId: Int,
    @SerialName("original_gift_name") val originalGiftName: String,
)
