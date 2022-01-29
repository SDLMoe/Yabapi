@file:JvmName("DefaultPropertiesJvm")

package moe.sdl.yabapi.consts

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

@JvmName("getDefaultEngineJvm")
public actual fun getDefaultEngine(): HttpClientEngineFactory<*> = CIO
