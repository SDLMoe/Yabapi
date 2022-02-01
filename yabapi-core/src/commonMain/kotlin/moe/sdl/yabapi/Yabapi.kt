package moe.sdl.yabapi

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import moe.sdl.yabapi.enums.LogLevel
import moe.sdl.yabapi.enums.LogLevel.INFO
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.LoggerFunc
import moe.sdl.yabapi.util.nowLocalString
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("Yabapi") }

public object Yabapi {
    public val defaultJson: AtomicRef<Json> = atomic(Json {
        prettyPrint = true
        isLenient = true
        coerceInputValues = true
    })

    @ExperimentalSerializationApi
    public val protoBuf: AtomicRef<ProtoBuf> = atomic(ProtoBuf)

    public val yabapiLogLevel: AtomicRef<LogLevel> = atomic(INFO)

    public val log: AtomicRef<LoggerFunc> =
        { tag: String, level: LogLevel, throwable: Throwable?, message: () -> String ->
            if (level >= yabapiLogLevel.value) {
                val str = "${nowLocalString()} [${level.name}] $tag ${message().replace("\n", "\\n")}"
                println(str)
                throwable?.printStackTrace()
            }
        }.let { atomic(it) }
}

internal inline fun <reified T> String.deserializeJson(): T {
    logger.debug { "Received Source String: $this" }
    return Yabapi.defaultJson.value.decodeFromString(this)
}
