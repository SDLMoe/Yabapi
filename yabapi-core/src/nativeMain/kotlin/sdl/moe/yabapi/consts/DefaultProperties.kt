package sdl.moe.yabapi.consts

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.curl.Curl

public actual fun getDefaultEngine(): HttpClientEngineFactory<*> = Curl
