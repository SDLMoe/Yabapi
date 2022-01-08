// © Copyright 2021-2022 Colerar and repository contributors
// Use of this source code is governed by the CDDL license that can be found via link below:
// https://github.com/SDLMoe/Yabapi/blob/master/LICENSE

package sdl.moe.yabapi.util


private val jsonRegex = Regex("""[{\[]([,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]|".*?")+[}\]]""")

/**
 * @receiver input must be one line
 */
internal fun String.findJson(): List<String> = jsonRegex.findAll(this).map { it.value }.toList()
