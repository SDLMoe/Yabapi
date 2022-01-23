package sdl.moe.yabapi.util.string

private val jsonRegex by lazy { Regex("""[{\[]([,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]|".*?")+[}\]]""") }

/**
 * @receiver input must be one line
 */
internal fun String.findJson(): List<String> = jsonRegex.findAll(this).map { it.value }.toList()
