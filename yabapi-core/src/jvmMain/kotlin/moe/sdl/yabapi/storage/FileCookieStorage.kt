@file:JvmName("FileCookieStorageJvm")

package moe.sdl.yabapi.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okio.FileSystem
import okio.Path.Companion.toPath
import java.io.File
import kotlin.coroutines.CoroutineContext

internal actual fun FileCookieStorage.addShutdownHook() =
    Runtime.getRuntime().addShutdownHook(
        Thread {
            runBlocking {
                this@addShutdownHook.save()
            }
        }
    )

public fun FileCookieStorage(
    file: File,
    context: CoroutineContext = Dispatchers.IO,
    config: FileCookieStorage.Config.() -> Unit = {},
): FileCookieStorage = FileCookieStorage(FileSystem.SYSTEM, file.absolutePath.toPath(), context, config)

public fun FileCookieStorage(
    path: String,
    context: CoroutineContext = Dispatchers.IO,
    config: FileCookieStorage.Config.() -> Unit = {},
): FileCookieStorage = FileCookieStorage(FileSystem.SYSTEM, path.toPath(), context, config)
