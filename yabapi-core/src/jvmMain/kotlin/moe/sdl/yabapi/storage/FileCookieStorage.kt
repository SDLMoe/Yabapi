@file:JvmName("FileCookieStorageJvm")

package moe.sdl.yabapi.storage

import kotlinx.coroutines.runBlocking
import okio.FileSystem
import okio.Path.Companion.toPath
import moe.sdl.yabapi.Platform
import java.io.File
import kotlin.coroutines.CoroutineContext

internal actual fun FileCookieStorage.addShutdownHook() =
    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            this@addShutdownHook.save()
        }
    })

@Suppress("NOTHING_TO_INLINE")
internal inline fun FileCookieStorage(
    file: File,
    context: CoroutineContext = Platform.ioDispatcher,
    noinline config: FileCookieStorage.Config.() -> Unit = {},
): FileCookieStorage = FileCookieStorage(FileSystem.SYSTEM, file.absolutePath.toPath(), context, config)

@Suppress("NOTHING_TO_INLINE")
internal inline fun FileCookieStorage(
    path: String,
    context: CoroutineContext = Platform.ioDispatcher,
    noinline config: FileCookieStorage.Config.() -> Unit = {},
): FileCookieStorage = FileCookieStorage(FileSystem.SYSTEM, path.toPath(), context, config)
