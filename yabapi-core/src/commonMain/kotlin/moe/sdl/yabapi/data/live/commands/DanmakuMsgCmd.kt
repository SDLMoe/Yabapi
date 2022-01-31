@file:UseSerializers(BooleanJsSerializer::class)

package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import moe.sdl.yabapi.serializer.BooleanJsSerializer

@Serializable
public data class DanmakuMsgCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("info") val _data: JsonArray? = null,
) : LiveCommand {
    val data: DanmakuMsgCmdData? by lazy {
        _data?.let { DanmakuMsgCmdData.decode(it) }
    }

    public companion object : LiveCommandFactory() {
        override val operation: String = "DANMU_MSG"

        override fun decode(json: Json, data: JsonElement): LiveCommand =
            json.decodeFromJsonElement<DanmakuMsgCmd>(data)
    }
}

@Serializable
public data class DanmakuMsgCmdData(
    val isSendFromSelf: Boolean?,
    val isSticker: Boolean?,
    val playerMode: Int?,
    val fontSize: Int?,
    val danmakuColor: Int?,
    val sentTime: Long?,
    val colors: Triple<String?, String?, String?>,
    val content: String?,
    val stickerData: LiveStickerData?,
    val liveUser: LiveUser?,
    val medal: LiveFansMedal?,
) {
    public companion object {
        /**
         * Why Bilibili f**king use json array to serialize object?
         */
        public fun decode(data: JsonArray): DanmakuMsgCmdData {
            // node 0
            val danmakuInfo = data.getOrNull(0)?.jsonArray

            val isSendFromSelf = danmakuInfo?.getIntBoolean(0)
            val playerMode = danmakuInfo?.getInt(1)
            val fontSize = danmakuInfo?.getInt(2)
            val danmakuColor = danmakuInfo?.getInt(3)
            val sendTime = danmakuInfo?.getLong(4)
            val colorList = if (danmakuInfo?.getInt(10) == 5) {
                danmakuInfo.getString(11)?.split(',')
            } else null

            val isSticker = danmakuInfo?.getIntBoolean(12)
            val stickerObject = if (isSticker == true) danmakuInfo.getOrNull(13)?.jsonObject else null
            val stickerData: LiveStickerData? = stickerObject?.let { Json.decodeFromJsonElement(stickerObject) }

            val colors = if (colorList?.size == 3) {
                val (color1, color2, color3) = colorList
                Triple(color1, color2, color3)
            } else Triple(null, null, null)

            // node 1
            val content = data.getString(1)

            // node 2
            val userInfo = data.getOrNull(2)?.jsonArray

            val user = userInfo?.run {
                LiveUser(
                    mid = getInt(0),
                    name = getString(1),
                    isRoomAdmin = getIntBoolean(2),
                    isMonthVip = getIntBoolean(3),
                    isYearVip = getIntBoolean(4),
                    color = getString(7),
                )
            }

            // node 3
            val medalInfo = data.getOrNull(3)?.jsonArray

            val medal = medalInfo?.run {
                LiveFansMedal(
                    level = getInt(0),
                    name = getString(1),
                    liverName = getString(2),
                    roomId = getInt(3),
                    color1 = getInt(4),
                    color2 = getInt(7),
                    color3 = getInt(8),
                    color4 = getInt(9),
                )
            }

            return DanmakuMsgCmdData(
                isSendFromSelf = isSendFromSelf,
                isSticker = isSticker,
                playerMode = playerMode,
                fontSize = fontSize,
                danmakuColor = danmakuColor,
                sentTime = sendTime,
                colors = colors,
                content = content,
                stickerData = stickerData,
                liveUser = user ?: LiveUser(),
                medal = medal ?: LiveFansMedal(),
            )
        }
    }
}

@Serializable
public data class LiveUser(
    val mid: Int? = null,
    val name: String? = null,
    val isRoomAdmin: Boolean? = null,
    val isMonthVip: Boolean? = null,
    val isYearVip: Boolean? = null,
    val color: String? = null,
)

@Serializable
public data class LiveFansMedal(
    val level: Int? = null,
    val name: String? = null,
    val liverName: String? = null,
    val roomId: Int? = null,
    val color1: Int? = null,
    val color2: Int? = null,
    val color3: Int? = null,
    val color4: Int? = null,
)

@Serializable
public data class LiveStickerData(
    @SerialName("bulge_display") val bulgeDisplay: Boolean? = null,
    @SerialName("emoticon_unique") val id: String? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("in_player_area") val inPlayerArea: Boolean? = null,
    @SerialName("is_dynamic") val isDynamic: Boolean? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("width") val width: Int? = null,
)

@Suppress("NOTHING_TO_INLINE")
private inline fun JsonArray.getJsonPrimitive(index: Int) = this.getOrNull(index)?.jsonPrimitive

@Suppress("NOTHING_TO_INLINE")
private inline fun JsonArray.getInt(index: Int) = getJsonPrimitive(index)?.intOrNull

@Suppress("NOTHING_TO_INLINE")
private inline fun JsonArray.getLong(index: Int) = getJsonPrimitive(index)?.longOrNull

@Suppress("NOTHING_TO_INLINE")
private inline fun JsonArray.getIntBoolean(index: Int) = getJsonPrimitive(index)?.intOrNull == 1

@Suppress("NOTHING_TO_INLINE")
private inline fun JsonArray.getString(index: Int) = getJsonPrimitive(index)?.contentOrNull
