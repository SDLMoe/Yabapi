package sdl.moe.yabapi.util.encoding

import java.security.MessageDigest

private val md5 by lazy { MessageDigest.getInstance("MD5") }

@Suppress("NOTHING_TO_INLINE")
internal inline fun String.md5(): String = md5.digest(this.toByteArray()).hex
