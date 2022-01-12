// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.storage

import com.soywiz.klock.DateTime
import com.soywiz.korio.async.runBlockingNoJs
import com.soywiz.korio.file.VfsFile
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import io.ktor.http.hostIsIp
import io.ktor.http.isSecure
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import sdl.moe.yabapi.Platform
import sdl.moe.yabapi.data.CookieWrapper
import sdl.moe.yabapi.data.toCookies
import sdl.moe.yabapi.isJs
import sdl.moe.yabapi.util.Logger
import kotlin.math.min
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger = Logger("FileCookieStorage")

/**
 * Modified base on ktor [AcceptAllCookiesStorage]
 *
 * Platform Differences:
 * - JVM 带有 Shutdown Hook 在 crash 时会保存
 * - Js 无法使用 runBlocking, 仅能实时保存, saveInTime 参数无效
 *
 * @param file [VfsFile]
 *
 * @param saveInTime 是否即时保存, 默认为 false, 只在程序退出时保存
 */
public class FileCookieStorage(
    private val file: VfsFile,
    private val saveInTime: Boolean = false,
) : CookiesStorage {

    init {
        this.addShutdownHook()
    }

    private val fileMutex = Mutex()

    private val mutex = Mutex()

    private val container: MutableList<Cookie> = mutableListOf()

    private val wrappers
        get() = CookieWrapper.fromCookies(container)

    private val oldestCookie: AtomicLong = atomic(0L)

    private var isInitiated = false

    override suspend fun get(requestUrl: Url): List<Cookie> = mutex.withLock {
        init()

        val now = Clock.System.now().epochSeconds
        if (now >= oldestCookie.value) cleanup(now)

        return@withLock container.filter { it.matches(requestUrl) }.also {
            logger.debug { "Found ${it.size} cookies for ${requestUrl.host}" }
            logger.verbose { "Cookies: $it" }
        }
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie): Unit = mutex.withLock {
        init()

        with(cookie) {
            if (name.isBlank()) return@withLock
        }

        container.removeAll { it.name == cookie.name && it.matches(requestUrl) }
        container.add(cookie.fillDefaults(requestUrl))
        logger.debug { "Added cookie $cookie for ${requestUrl.host}" }
        cookie.expires?.timestamp?.let { expires ->
            if (oldestCookie.value > expires) {
                oldestCookie.value = expires
            }
        }
        if (Platform.isJs() || saveInTime) {
            save()
        }
    }

    override fun close() {
        if (!Platform.isJs()) runBlockingNoJs {
            save()
        }
    }

    private fun cleanup(timestamp: Long) {
        container.removeAll { cookie ->
            val expires = cookie.expires?.timestamp ?: return@removeAll false
            expires < timestamp
        }

        val newOldest = container.fold(Long.MAX_VALUE) { acc, cookie ->
            cookie.expires?.timestamp?.let { min(acc, it) } ?: acc
        }

        oldestCookie.value = newOldest
    }

    private suspend fun init() {
        if (!isInitiated) {
            logger.debug { "Initializing FileCookieStorage" }
            if (file.exists()) {
                load()
            } else {
                logger.debug { "File does not exist, creating new file ${file.absolutePath}" }
                fileMutex.withLock {
                    file.touch(DateTime.now())
                }
            }
            isInitiated = true
        }
    }

    public suspend fun save(): Unit = withContext(Platform.ioDispatcher) {
        logger.debug { "Saving FileCookieStorage to ${file.absolutePath}" }
        init()
        fileMutex.withLock {
            file.writeString(Json.encodeToString(wrappers))
        }
    }

    private suspend fun load() {
        logger.debug { "Loading FileCookieStorage from ${file.absolutePath}" }
        val text = fileMutex.withLock { file.readString() }
        val wrappers: List<CookieWrapper> =
            if (text.isNotBlank()) {
                Json.decodeFromString(text)
            } else listOf()
        container += wrappers.toCookies()
        logger.debug { "Loaded FileCookieStorage: $container" }
    }
}

internal expect fun FileCookieStorage.addShutdownHook()

// All below from ktor.util
internal fun Cookie.fillDefaults(requestUrl: Url): Cookie {
    var result = this

    if (result.path?.startsWith("/") != true) {
        result = result.copy(path = requestUrl.encodedPath)
    }

    if (result.domain.isNullOrBlank()) {
        result = result.copy(domain = requestUrl.host)
    }

    return result
}

internal fun Cookie.matches(requestUrl: Url): Boolean {
    val domain = domain?.toLowerCasePreservingASCIIRules()?.trimStart('.')
        ?: error("Domain field should have the default value")

    val path = with(path) {
        val current = path ?: error("Path field should have the default value")
        if (current.endsWith('/')) current else "$path/"
    }

    val host = requestUrl.host.toLowerCasePreservingASCIIRules()
    val requestPath = let {
        val pathInRequest = requestUrl.encodedPath
        if (pathInRequest.endsWith('/')) pathInRequest else "$pathInRequest/"
    }

    if (host != domain && (hostIsIp(host) || !host.endsWith(".$domain"))) {
        return false
    }

    if (path != "/" &&
        requestPath != path &&
        !requestPath.startsWith(path)
    ) return false

    return !(secure && !requestUrl.protocol.isSecure())
}

internal fun String.toLowerCasePreservingASCIIRules(): String {
    val firstIndex = indexOfFirst {
        toLowerCasePreservingASCII(it) != it
    }

    if (firstIndex == -1) {
        return this
    }

    val original = this
    return buildString(length) {
        append(original, 0, firstIndex)

        for (index in firstIndex..original.lastIndex) {
            append(toLowerCasePreservingASCII(original[index]))
        }
    }
}

private fun toLowerCasePreservingASCII(ch: Char): Char = when (ch) {
    in 'A'..'Z' -> ch + 32
    in '\u0000'..'\u007f' -> ch
    else -> ch.lowercaseChar()
}
