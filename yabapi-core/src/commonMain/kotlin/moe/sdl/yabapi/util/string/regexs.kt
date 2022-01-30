package moe.sdl.yabapi.util.string

import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
private val jsonRegex by lazy { Regex("""[{\[]([,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]|".*?")+[}\]]""") }

/**
 * @receiver input must be one line
 */
internal fun String.findJson(): List<String> = jsonRegex.findAll(this).map { it.value }.toList()

@SharedImmutable
private val biliInitialStateRegex by lazy { Regex("""window.__INITIAL_STATE__=(\{[\S\s\r\n]+});""") }

internal fun String.findInitialState(): String? = biliInitialStateRegex.find(this)?.groups?.get(1)?.value
