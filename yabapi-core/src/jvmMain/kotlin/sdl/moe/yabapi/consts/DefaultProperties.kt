// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

@file:JvmName("DefaultPropertiesJvm")

package sdl.moe.yabapi.consts

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

@JvmName("getDefaultEngineJvm")
public actual fun getDefaultEngine(): HttpClientEngineFactory<*> = CIO
