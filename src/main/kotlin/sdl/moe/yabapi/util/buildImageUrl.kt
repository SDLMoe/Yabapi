package sdl.moe.yabapi.util

import sdl.moe.yabapi.enums.ImageFormat

private val regexPattern by lazy {
    Regex(
        /**                $1    $2                   $3          $4       $5  */
        pattern = """^(https?://)(.*\.hdslb\.com/bfs(/.*)+)\.(jpg|png|gif)(@.+)?$"""
    )
}
private const val MIN_QUALITY = 0
private const val MAX_QUALITY = 100

/**
 * 根据地址和参数, 构建所需的图片地址
 * @param url 输入 *.hdslb.com/bfs 下的图片地址
 * @param format 图片格式
 * @param quality 图片质量, 仅适用于 Webp 格式, 否则会被忽略
 * @param weight 宽度, 图片实际大小不一定一致, 根据 Fit 方式得出
 * @param height 高度, 图片实际大小不一定一致, 根据 Fit 方式得出
 * @return [String] 构建后的图片地址
 */
@Suppress("ComplexMethod")
public fun buildImageUrl(
    url: String,
    format: ImageFormat = ImageFormat.WEBP,
    quality: Int? = null,
    weight: Int? = null,
    height: Int? = null,
): String {
    val found = regexPattern.find(url)
    require(found != null) { "Invalid url: $url, must be matched by regex ${regexPattern.pattern}" }
    require(quality == null || quality in MIN_QUALITY..MAX_QUALITY) {
        "Invalid quality: $quality, must be in $MIN_QUALITY..$MAX_QUALITY"
    }

    val sb = StringBuilder()
    var appendedValue = 0
    var isWebp = false

    sb.append(found.groupValues[1])
    sb.append(found.groupValues[2])
    when (format) {
        ImageFormat.JPEG -> sb.append(".jpg")
        ImageFormat.PNG -> sb.append(".png")
        ImageFormat.GIF -> sb.append(".gif")
        ImageFormat.WEBP -> {
            sb.append(".jpg")
            isWebp = true
        }
        else -> sb.append(".jpg")
    }

    fun appendAndCount(str: String) {
        if (appendedValue >= 1) sb.append("_")
        sb.append("${str}h")
        appendedValue += 1
    }

    if (weight != null || height != null || isWebp) sb.append("@")
    weight?.let { appendAndCount("${it}w") }
    height?.let { appendAndCount("${it}h") }
    if (isWebp) {
        quality?.let { appendAndCount("${it}q") }
        sb.append(".webp")
    }

    return sb.toString()
}
