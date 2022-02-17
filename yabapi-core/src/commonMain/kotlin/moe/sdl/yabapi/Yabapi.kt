package moe.sdl.yabapi

import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf
import moe.sdl.yabapi.enums.LogLevel
import moe.sdl.yabapi.enums.LogLevel.INFO
import moe.sdl.yabapi.util.Logger
import moe.sdl.yabapi.util.LoggerFunc
import moe.sdl.yabapi.util.encoding.hex
import moe.sdl.yabapi.util.nowLocalString
import moe.sdl.yabapi.util.reflect.qualifiedOrSimpleName
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

@OptIn(ExperimentalSerializationApi::class)
internal inline fun <reified T> ByteArray.deserializeProto(): T {
    logger.debug { "Received Source ByteArray: ${this.hex}" }
    return Yabapi.protoBuf.value.decodeFromByteArray(this)
}

internal inline fun <reified T> String.deserializeJson(): T {
    logger.verbose { "Received raw json: $this" }
    return try {
        Yabapi.defaultJson.value.decodeFromString(this)
    } catch (e: SerializationException) {
        logger.error { "Failed to deserialize ${T::class.qualifiedOrSimpleName}, raw json: $this" }
        throw e
    }
}
