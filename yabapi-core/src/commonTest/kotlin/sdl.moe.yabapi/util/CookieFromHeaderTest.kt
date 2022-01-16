package sdl.moe.yabapi.util

import io.ktor.http.Url
import kotlin.test.Test

class CookieFromHeaderTest {
    @Test
    fun readCookieTest() {
        val cookie = cookieFromHeader(
            string = "rpdid=|(J~|u)Y)~~|0J'uYJ)Yk~~l); DedeUserID=1919810; DedeUserID__ckMd5=114514; buvid4=107F8C58-4AF4-C011-AX79-ECF7947124125772infoc; LIVE_BUVID=AUTO871241242112428; fingerprint4=197224e5ee2ab9712412412478fb40f4e907bda49; fingerprint_s=0a77d4dd12412412479b7bfe7e928; CURRENT_QUALITY=80; _uuid=411111-1111-21D9-10EE5-4C144910E7F7949111infoc; fingerprint=7172f571111c9224471ea192d94d419ab; buvid_fp=757F8C58-4AF4-C044-AB79-ECF79471511115772infoc; buvid_fp_plain=757F8C58-4AF4-C044-AB79-ECF79471517111172infoc; SESSDATA=77ca4f7f%2C1751111711%2Cc0a9d%2Ac1; bili_jct=0d9144e9c7444b745b78da5f1e4a1245; sid=7u94kc9r; CURRENT_BLACKGAP=0; blackside_state=0; i-wanna-go-back=-1; b_ut=5; CURRENT_FNVAL=2000; bp_t_offset_114514217=708149201409921111; innersign=0",
            requestUrl = Url("https://example.com"))
        cookie.forEach {
            println(it)
        }
    }
}
