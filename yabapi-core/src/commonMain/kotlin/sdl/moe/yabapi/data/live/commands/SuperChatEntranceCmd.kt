// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
public data class SuperChatEntranceCmd(
    @SerialName("cmd") override val operation: String,
    @SerialName("data") val data: SuperChatEntranceData,
) : LiveCommand {
    public companion object : LiveCommandFactory() {
        override val operation: String = "SUPER_CHAT_ENTRANCE"

        override fun decode(json: Json, data: JsonElement): SuperChatEntranceCmd = json.decodeFromJsonElement(data)
    }
}

@Serializable
public data class SuperChatEntranceData(
    @SerialName("icon") val icon: String,
    @SerialName("jump_url") val jumpUrl: String,
    @SerialName("status") val status: Int,
)
