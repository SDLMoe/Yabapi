package moe.sdl.yabapi.util

import moe.sdl.yabapi.util.string.findJson
import kotlin.test.Test

internal class JsonRegex {
    @Test
    fun test() {
        val input = """
  *          {"cmd":"INTERACT_WORD","data":{"contribution":{"grade":0},"dmscore":2,"fans_medal":{"anchor_roomid":0,"guard_level":0,"icon_id":0,"is_lighted":0,"medal_color":0,"medal_color_border":0,"medal_color_end":0,"medal_color_start":0,"medal_level":0,"medal_name":"","score":0,"special":"","target_id":0},"identities":[1],"is_spread":0,"msg_type":1,"roomid":8633637,"score":1641614035637,"spread_desc":"","spread_info":"","tail_icon":0,"timestamp":1641614035,"trigger_time":1641614034577209000,"uid":1559096389,"uname":"Alive-K","uname_color":""}}  _          {"cmd":"INTERACT_WORD","data":{"contribution":{"grade":0},"dmscore":6,"fans_medal":{"anchor_roomid":90713,"guard_level":0,"icon_id":0,"is_lighted":0,"medal_color":9272486,"medal_color_border":12632256,"medal_color_end":12632256,"medal_color_start":12632256,"medal_level":9,"medal_name":"顾顾","score":8621,"special":"","target_id":7946235},"identities":[1],"is_spread":0,"msg_type":1,"roomid":8633637,"score":1641614036062,"spread_desc":"","spread_info":"","tail_icon":0,"timestamp":1641614036,"trigger_time":1641614035081271800,"uid":382417801,"uname":"啾咪略略略","uname_color":""}}
        """.trimIndent()
        input.findJson().forEach { println(it) }
    }
}
