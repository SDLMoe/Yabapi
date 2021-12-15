package sdl.moe.yabapi

import io.ktor.client.HttpClient
import mu.KotlinLogging
import sdl.moe.yabapi.api.BiliApi
import sdl.moe.yabapi.consts.DefaultHttpClient
import kotlin.collections.set

private val logger = KotlinLogging.logger {}

public class BiliClient(
    public val client: HttpClient = DefaultHttpClient,
) {
    public companion object {
        private val api: HashMap<String, BiliApi> = hashMapOf()

        internal fun registerApi(name: String, api: BiliApi) {
            logger.debug { "Registering api $name.." }
            this.api[name] = api
        }
    }

    public var isLogin: Boolean = false
        private set(value) {
            field = value
            logger.debug { "Login status changed to $value" }
        }
}
