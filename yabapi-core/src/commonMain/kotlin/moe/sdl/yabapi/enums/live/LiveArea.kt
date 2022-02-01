package moe.sdl.yabapi.enums.live

import kotlinx.serialization.Serializable

@Serializable
public sealed class LiveArea(public val id: Int, public val name: String) {
    public object All : LiveArea(id = 0, name = "全部")

    public object Entertainment : LiveArea(id = 1, name = "娱乐")

    public object OnlineGame : LiveArea(id = 2, name = "网游")

    public object MobileGame : LiveArea(id = 3, name = "手游")

    public object Radio : LiveArea(id = 5, name = "电台")

    public object IndieGame : LiveArea(id = 6, name = "单机游戏")

    public object VTuber : LiveArea(id = 9, name = "虚拟主播")

    public object Life : LiveArea(id = 10, name = "生活")

    public object Study : LiveArea(id = 11, name = "学习")

    public object Event : LiveArea(id = 12, name = "大事件")

    public object Match : LiveArea(id = 13, name = "赛事")
}
