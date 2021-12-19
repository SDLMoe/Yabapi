// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import sdl.moe.yabapi.data.CookieWrapper
import sdl.moe.yabapi.data.toCookies
import java.io.File
import java.io.IOException
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

private val logger = KotlinLogging.logger {}

public class FileCookieStorage(
    private val file: File,
) : CookiesStorage {

    private var isInitiated: Boolean = false

    private val mutex = Mutex()

    private val delegateStorage = AcceptAllCookiesStorage()

    private val cookies: List<Cookie>
        get() = delegateStorage::class.declaredMemberProperties.firstOrNull {
            it.name == "container"
        }.let { prop ->
            val list = mutableListOf<Cookie>()
            val value: Any? = prop?.getter?.apply { isAccessible = true }?.call(delegateStorage)
            if (value is MutableList<*>) {
                value.forEach { element -> if (element is Cookie) list.add(element) }
            }
            list.also {
                logger.debug { "Reflected Cookie List: $it" }
            }
        }

    private val wrappers: List<CookieWrapper>
        get() = CookieWrapper.fromCookies(cookies)

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        logger.debug { "Adding cookie: $cookie" }
        delegateStorage.addCookie(requestUrl, cookie)
        save()
    }

    override fun close() {
        // DO NOTHING
    }

    override suspend fun get(requestUrl: Url): List<Cookie> = delegateStorage.get(requestUrl)

    @Throws(IOException::class)
    private suspend fun init(): Unit {
        if (!isInitiated) {
            logger.debug { "Initializing FileCookieStorage" }
            if (file.exists()) {
                load()
            } else {
                logger.debug { "File does not exist, creating new file ${file.absoluteFile}" }
                mutex.withLock(this::init) {
                    file.createNewFile()
                }
            }
        }
    }

    private suspend fun save(): Unit {
        init()
        logger.debug { "Saving FileCookieStorage to ${file.absoluteFile}" }
        mutex.withLock(this::save) {
            file.writeText(Json.encodeToString(wrappers))
        }
    }

    private suspend fun load(): Unit {
        logger.debug { "Loading FileCookieStorage from ${file.absoluteFile}" }
        var wrappers: List<CookieWrapper>
        mutex.withLock(this::init) {
            wrappers = Json.decodeFromString(file.readText())
        }
        wrappers.toCookies().forEach {
            delegateStorage.addCookie(Url("${it.domain}/${it.path}"), it)
        }
    }
}
