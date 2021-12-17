// © Copyright 2021 Colerar and repository contributors
// Use of this source code is governed by the MIT license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util.encoding

import java.security.MessageDigest

private val md5 by lazy { MessageDigest.getInstance("MD5") }

@Suppress("NOTHING_TO_INLINE")
internal inline fun String.md5(): String = md5.digest(this.toByteArray()).hex
