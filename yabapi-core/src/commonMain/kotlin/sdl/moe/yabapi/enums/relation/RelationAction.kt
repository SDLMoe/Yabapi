package sdl.moe.yabapi.enums.relation

/**
 * 顯然的, 加/刪操作 需要對應, 否則會失敗
 */
public enum class RelationAction(public val code: Int) {
    SUB(1),
    UNSUB(2),
    SUB_QUIETLY(3),
    UNSUB_QUIETLY(4),
    ADD_BLACKLIST(5),
    REMOVE_BLACKLIST(6),
    REMOVE_FANS(7);
}
