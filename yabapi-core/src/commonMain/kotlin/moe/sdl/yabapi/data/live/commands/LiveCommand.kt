package moe.sdl.yabapi.data.live.commands

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import moe.sdl.yabapi.Yabapi.defaultJson
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
        LiveCommandFactory.map[operation]?.decode(defaultJson.value, value)
    }
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
        private val factories: List<LiveCommandFactory> by lazy {
            listOf(
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
                WatchedChangeCmd,
                WidgetBannerCmd,
            )
        }

        public val map: Map<String, LiveCommandFactory> by lazy {
            factories.associateBy { it.operation }
        }
    }
}
