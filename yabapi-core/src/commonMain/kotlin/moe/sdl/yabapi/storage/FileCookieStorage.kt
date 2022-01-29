package moe.sdl.yabapi.storage

import com.soywiz.korio.async.runBlockingNoJs
import io.ktor.client.features.cookies.AcceptAllCookiesStorage
import io.ktor.client.features.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import io.ktor.http.hostIsIp
import io.ktor.http.isSecure
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.AtomicRef
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.FileSystem
import okio.Path
import moe.sdl.yabapi.Platform
import moe.sdl.yabapi.data.CookieWrapper
import moe.sdl.yabapi.data.toCookies
import moe.sdl.yabapi.isJs
import moe.sdl.yabapi.util.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.math.min
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val logger by lazy { Logger("FileCookieStorage") }

/**
 * Modified base on ktor [AcceptAllCookiesStorage]
 */
public class FileCookieStorage(
    private val fileSystem: FileSystem,
    private val path: Path,
    private val context: CoroutineContext = Platform.ioDispatcher,
    config: Config.() -> Unit = {},
) : CookiesStorage {
    public class Config {
        public var saveInTime: Boolean = false
    }

    private val config = Config().apply(config)

    private val fileMutex = Mutex()

    private val mutex = Mutex()

    private val container: AtomicRef<List<Cookie>> = atomic(emptyList())

    private val wrappers
        get() = CookieWrapper.fromCookies(container.value)

    private val oldestCookie: AtomicLong = atomic(0L)

    private var isInitiated = atomic(false)

    override suspend fun get(requestUrl: Url): List<Cookie> = mutex.withLock {
        init()

        val now = Clock.System.now().epochSeconds
        if (now >= oldestCookie.value) cleanup(now)

        return@withLock container.value.filter { it.matches(requestUrl) }.also {
            logger.debug { "Found ${it.size} cookies for ${requestUrl.host}" }
            logger.verbose { "Cookies: $it" }
        }
    }

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie): Unit = mutex.withLock {
        init()

        with(cookie) {
            if (name.isBlank()) return@withLock
        }
        container.getAndSet(
            container.value.toMutableList().apply {
                removeAll { it.name == cookie.name && it.matches(requestUrl) }
                add(cookie.fillDefaults(requestUrl))
            }
        )
        logger.debug { "Added cookie $cookie for ${requestUrl.host}" }
        cookie.expires?.timestamp?.let { expires ->
            if (oldestCookie.value > expires) {
                oldestCookie.value = expires
            }
        }
        if (Platform.isJs() || config.saveInTime) {
            save()
        }
    }

    override fun close() {
        logger.debug { "Closing FileCookiesStorage path: $path" }
        runBlockingNoJs {
            save()
        }
    }

    private fun cleanup(timestamp: Long) {
        container.getAndSet(
            container.value.toMutableList().apply {
                removeAll { cookie ->
                    val expires = cookie.expires?.timestamp ?: return@removeAll false
                    expires < timestamp
                }
            }
        )

        val newOldest = container.value.fold(Long.MAX_VALUE) { acc, cookie ->
            cookie.expires?.timestamp?.let { min(acc, it) } ?: acc
        }

        oldestCookie.value = newOldest
    }

    private suspend fun init() {
        if (!isInitiated.value) {
            addShutdownHook()
            logger.debug { "Initializing FileCookieStorage" }
            if (fileSystem.exists(path)) {
                load()
            } else {
                logger.debug { "File does not exist, creating new file $path" }
                fileMutex.withLock {
                    path.parent?.let { fileSystem.createDirectories(it) }
                    fileSystem.write(path, true) {}
                }
            }
            isInitiated.getAndSet(true)
        }
    }

    public suspend fun save(): Unit = withContext(context) {
        logger.debug { "Saving FileCookieStorage to $path" }
        init()
        fileMutex.withLock {
            fileSystem.write(path) {
                writeUtf8(Json.encodeToString(wrappers))
            }
        }
    }

    private suspend fun load() {
        logger.debug { "Loading FileCookieStorage from $path" }
        val text = fileMutex.withLock {
            fileSystem.read(path) {
                readUtf8()
            }
        }
        val wrappers: List<CookieWrapper> =
            if (text.isNotBlank()) {
                Json.decodeFromString(text)
            } else listOf()
        container.getAndSet(
            container.value + wrappers.toCookies()
        )
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
