// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import sdl.moe.yabapi.data.CookieWrapper
import sdl.moe.yabapi.data.toCookies
import java.io.File
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

private val logger = KotlinLogging.logger {}

public class FileCookieStorage(
    private val file: File,
) : CookiesStorage {

    public constructor(path: String) : this(File(path))

    private var isInitiated: Boolean = false

    private val mutex = Mutex()

    private val delegateStorage = AcceptAllCookiesStorage()

    private val _container =
        delegateStorage::class.declaredMemberProperties.firstOrNull {
            it.name == "container"
        }

    public val cookies: List<Cookie>
        get() = _container.let { prop ->
            val list = mutableListOf<Cookie>()
            prop?.getter?.apply { isAccessible = true }?.call(delegateStorage).also { value ->
                (value as? MutableList<*>)?.onEach { element -> if (element is Cookie) list.add(element) }
            }
            list.also {
                logger.trace { "Reflected Cookie List: $it" }
            }
        }

    private val wrappers: List<CookieWrapper>
        get() = CookieWrapper.fromCookies(cookies)

    override suspend fun get(requestUrl: Url): List<Cookie> {
        init()
        return delegateStorage.get(requestUrl)
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        init()
        logger.debug { "Adding cookie: $cookie" }
        delegateStorage.addCookie(requestUrl, cookie)
    }

    public suspend fun reset() {
        logger.debug { "Resetting cookies" }
        _container?.let { prop ->
            prop.getter.apply { isAccessible = true }.call(delegateStorage).let {
                (it as? MutableList<*>)?.clear()
            }
            logger.debug { "Cookies reset: $cookies" }
        }
        save()
    }

    override fun close() {
        runBlocking { save() }
    }

    private suspend fun init() {
        if (!isInitiated) {
            logger.debug { "Initializing FileCookieStorage" }
            Runtime.getRuntime().addShutdownHook(Thread {
                runBlocking { save() }
            })
            if (file.exists()) {
                load()
            } else {
                logger.debug { "File does not exist, creating new file ${file.absoluteFile}" }
                mutex.withLock {
                    file.parentFile?.mkdirs()
                    file.createNewFile()
                }
            }
            isInitiated = true
        }
    }

    public suspend fun save(): Unit = withContext(Dispatchers.IO) {
        logger.debug { "Saving FileCookieStorage to ${file.absoluteFile}" }
        init()
        mutex.withLock {
            file.writeText(Json.encodeToString(wrappers))
        }
    }

    private suspend fun load() {
        logger.debug { "Loading FileCookieStorage from ${file.absoluteFile}" }
        var wrappers: List<CookieWrapper> = listOf()
        val text = mutex.withLock { file.readText() }
        if (text.isNotBlank()) wrappers = Json.decodeFromString(text)
        wrappers.toCookies().forEach {
            delegateStorage.addCookie(Url("${it.domain}/${it.path}"), it)
        }
        logger.debug { "Loaded FileCookieStorage: $cookies" }
    }
}
