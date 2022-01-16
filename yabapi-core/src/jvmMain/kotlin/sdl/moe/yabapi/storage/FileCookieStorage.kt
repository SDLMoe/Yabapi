@file:JvmName("FileCookieStorageJvm")

package sdl.moe.yabapi.storage

import com.soywiz.korio.file.std.toVfs
import kotlinx.coroutines.runBlocking
import java.io.File

internal actual fun FileCookieStorage.addShutdownHook() =
    Runtime.getRuntime().addShutdownHook(Thread {
        runBlocking {
            this@addShutdownHook.save()
        }
    })

@Suppress("NOTHING_TO_INLINE")
internal inline fun FileCookieStorage(file: File, saveInTime: Boolean = false): FileCookieStorage =
    FileCookieStorage(file.toVfs(), saveInTime)

@Suppress("NOTHING_TO_INLINE")
internal inline fun FileCookieStorage(path: String, saveInTime: Boolean = false): FileCookieStorage =
    FileCookieStorage(File(path).toVfs(), saveInTime)
