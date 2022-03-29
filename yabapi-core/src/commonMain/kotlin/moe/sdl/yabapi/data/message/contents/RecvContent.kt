package moe.sdl.yabapi.data.message.contents

import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

public sealed interface RecvContent

public abstract class ContentFactory<T : RecvContent> {
    public abstract val code: Int
    public abstract fun decode(json: Json, data: String): T

    @ThreadLocal
    public companion object {
        private val factories by lazy {
            listOf<ContentFactory<*>>(
                Text,
            )
        }

        public val map: Map<Int, ContentFactory<*>> by lazy {
            factories.associateBy { it.code }
        }
    }
}
