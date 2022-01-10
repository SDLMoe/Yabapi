// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.atomicfu.atomic
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import sdl.moe.yabapi.consts.json

@Serializable
public data class RawLiveCommand(
    public val value: JsonObject,
) {
    public val operation: String by lazy {
        value.jsonObject["cmd"]?.jsonPrimitive?.content
            ?: throw SerializationException("Required [cmd] field cannot find in JsonObject $value")
    }

    public val data: LiveCommand? by lazy {
        value.let {
            LiveCommandFactory.map[operation]?.decode(json, it)
        }
    }
}

public sealed interface LiveCommand {
    public val operation: String
}

public sealed interface LiveCommandData

public sealed class LiveCommandFactory {

    public abstract val operation: String

    public abstract fun decode(json: Json, data: JsonElement): LiveCommand

    public companion object {
        private var isInitialized = atomic(false)

        internal fun init() {
            if (!isInitialized.value) {

                isInitialized.getAndSet(true)
            }
        }

        public val factories: List<LiveCommandFactory> = listOf(
            DanmakuMsgCmd,
            GuardBuyCmd,
            SuperChatDeleteCmd,
            SuperChatEntranceCmd,
            SuperChatMsgCmd,
            SuperChatMsgJpnCmd,
        )

        public val map: Map<String, LiveCommandFactory> by lazy {
            factories.associateBy { it.operation }
        }
    }
}
