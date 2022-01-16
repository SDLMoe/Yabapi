package sdl.moe.yabapi.data.live.commands

import kotlinx.atomicfu.atomic
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import sdl.moe.yabapi.BiliClient
import kotlin.native.concurrent.ThreadLocal

@Serializable
public data class RawLiveCommand(
    public val value: JsonObject,
) {
    public val operation: String by lazy {
        value.jsonObject["cmd"]?.jsonPrimitive?.content
            ?: throw SerializationException("Required [cmd] field cannot find in JsonObject $value")
    }

    public val data: LiveCommand? by lazy {
        getData()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun getData(json: Json = Json): LiveCommand? = LiveCommandFactory.map[operation]?.decode(json, value)

    @Suppress("NOTHING_TO_INLINE")
    public inline fun BiliClient.getData(): LiveCommand? = getData(this.json)
}

public sealed interface LiveCommand {
    public val operation: String
}

public sealed interface LiveCommandData

public sealed class LiveCommandFactory {

    public abstract val operation: String

    public abstract fun decode(json: Json, data: JsonElement): LiveCommand

    @ThreadLocal
    public companion object {
        private var isInitialized = atomic(false)

        internal fun init() {
            if (!isInitialized.value) {

                isInitialized.getAndSet(true)
            }
        }

        private val factories: List<LiveCommandFactory> = listOf(
            ComboSendCmd,
            DanmakuMsgCmd,
            EntryEffectCmd,
            GuardBuyCmd,
            HotRankChangeCmd,
            HotRankChangeV2Cmd,
            HotRankSettlementCmd,
            HotRankSettlementV2Cmd,
            HotRoomNotifyCmd,
            InteractWordCmd,
            LiveInteractGameCmd,
            LotAwardCmd,
            LotCheckStatusCmd,
            LotEndCmd,
            LotStartCmd,
            MatchRoomConfCmd,
            NoticeMsgCmd,
            OnlineRankCountCmd,
            OnlineRankTopCmd,
            OnlineRankV2Cmd,
            RoomChangeCmd,
            RoomUpdateCmd,
            SendGiftCmd,
            StopRoomListCmd,
            SuperChatDeleteCmd,
            SuperChatEntranceCmd,
            SuperChatMsgCmd,
            SuperChatMsgJpnCmd,
            UserToastMsgCmd,
            WidgetBannerCmd
        )

        public val map: Map<String, LiveCommandFactory> by lazy {
            factories.associateBy { it.operation }
        }
    }
}
