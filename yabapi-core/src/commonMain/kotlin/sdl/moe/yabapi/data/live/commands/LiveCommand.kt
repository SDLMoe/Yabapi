// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
public class LiveCommand internal constructor(
    @SerialName("cmd")
    public val operation: String,
    @SerialName("data")
    internal val _data: JsonElement? = null,
    @SerialName("info")
    internal val info: JsonArray? = null,
) {
    public val data: LiveCommandData
        get() {
            TODO()
        }

    public fun getDataOrNull(): LiveCommandData {
        TODO()
    }

    override fun toString(): String {
        return "LiveCommand(operation='$operation', _data=$_data, info=$info)"
    }
}

@Serializable
public sealed class LiveCommandData {
    public abstract val operation: String

    public companion object {
        public fun getAllCommands(): List<LiveCommandData> = listOf()

        public fun fromOperationOrNull(operation: String): LiveCommandData? =
            getAllCommands().firstOrNull { operation == it.operation }

        public fun fromOperation(operation: String): LiveCommandData =
            getAllCommands().first { operation == it.operation }
    }
}
