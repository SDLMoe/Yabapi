package sdl.moe.yabapi.data.history

import kotlinx.serialization.Serializable
import sdl.moe.yabapi.serializer.data.history.HistoryPlatformSerializer

@Serializable(HistoryPlatformSerializer::class)
public enum class HistoryPlatform(public val codes: List<Int>) {
    UNKNOWN(listOf(-1)),
    APP(listOf(1, 3, 5, 7)),
    WEB(listOf(2)),
    PAD(listOf(4, 6)),
    TV(listOf(33)),
    OTHER(listOf(0)),
    ;
}
