// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.data.live.commands

import kotlinx.atomicfu.atomic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

@Serializable
public data class LiveCommand internal constructor(
    @SerialName("cmd")
    public val operation: String,
    @SerialName("data")
    internal val rawData: JsonElement? = null,
    @SerialName("info")
    internal val rawInfo: JsonArray? = null,
    @SerialName("roomid")
    private val roomId: Int? = null, // not used
) {
    public val data: LiveCommandData? by lazy {
        (rawData ?: rawInfo)?.let {
            CommandDataFactory.init()
            CommandDataFactory.getFromOperation(operation)?.decode(it)
        }
    }

    override fun toString(): String = "LiveCommand(operation='$operation', rawData=${rawData ?: rawInfo ?: "null"})"
}

public sealed interface LiveCommandData

public sealed class CommandDataFactory {

    public abstract val operation: String

    public abstract fun decode(data: JsonElement): LiveCommandData?

    public companion object {
        private var isInitialized = atomic(false)

        internal fun init() {
            if (!isInitialized.value) {
                registerAll(DanmakuMsgCmdData)
                isInitialized.getAndSet(true)
            }
        }

        private val factories: HashMap<String, CommandDataFactory> = hashMapOf()

        public fun getAllFactory(): List<CommandDataFactory> = factories.values.toList()

        public fun getFromOperation(operation: String): CommandDataFactory? = factories[operation]

        @Suppress("SENSELESS_COMPARISON")
        internal fun register(factory: CommandDataFactory) {
            require(factory.operation != null) // not always true, maybe invoke before init
            require(!factories.containsKey(factory.operation))
            factories[factory.operation] = factory
        }

        internal fun registerAll(vararg factory: CommandDataFactory) {
            factory.forEach {
                register(it)
            }
        }
    }
}
