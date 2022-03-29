package moe.sdl.yabapi.data.message

import moe.sdl.yabapi.data.message.MessageSetting._At
import moe.sdl.yabapi.data.message.MessageSetting._Comment
import moe.sdl.yabapi.data.message.MessageSetting._FoldUnfollowed
import moe.sdl.yabapi.data.message.MessageSetting._Like
import moe.sdl.yabapi.data.message.MessageSetting._Intercept
import moe.sdl.yabapi.data.message.MessageSetting._Notify

@Suppress("ClassName")
private sealed interface MessageSetting {
    val key: String
    val code: Int

    enum class _Notify(override val code: Int) : MessageSetting {
        ON(1), OFF(3);

        override val key: String = "msg_notify"
    }

    enum class _Intercept(override val code: Int) : MessageSetting {
        ON(1), OFF(0);

        override val key: String = "ai_intercept"
    }

    enum class _Comment(override val code: Int) : MessageSetting {
        ON(0), ONLY_FOLLOW(1), OFF(2);

        override val key: String = "set_comment"
    }

    enum class _At(override val code: Int) : MessageSetting {
        ON(0), ONLY_FOLLOW(1), OFF(2);

        override val key: String = "set_at"
    }

    enum class _Like(override val code: Int) : MessageSetting {
        ON(1), OFF(5);

        override val key: String = "set_like"
    }

    enum class _FoldUnfollowed(override val code: Int) : MessageSetting {
        ON(1), OFF(0);

        override val key: String = "show_unfollowed_msg"
    }
}

/**
 * DSL 消息提醒设置
 *
 * 可配置项首字母大写 可用选项小写开头
 *
 * 用法:
 * ```kotlin
 * // this: MessageSettingBuilder
 * Notify set on
 * Intercept set off
 * At set followed
 * ```
 *
 * 如果多次设置一个元素，那么将只有最后调用的有效。
 *
 */
@Suppress("PropertyName")
public class MessageSettingBuilder {
    private val list: MutableList<MessageSetting> = mutableListOf()

    internal fun build(): List<Pair<String, Int>> = list
        .reversed().distinctBy { it::class }
        .map { it.key to it.code }

    public inline val on: DslSwitch2Status
        get() = DslSwitch2Status.FIRST
    public inline val off: DslSwitch2Status
        get() = DslSwitch2Status.SECOND
    public inline val followed: DslSwitch3Status
        get() = DslSwitch3Status.THIRD

    public val Notify: DslSwitch2 = object : DslSwitch2() {
        override fun slot1() {
            list.add(_Notify.ON)
        }

        override fun slot2() {
            list.add(_Notify.OFF)
        }
    }

    public val Intercept: DslSwitch2 = object : DslSwitch2() {
        override fun slot1() {
            list.add(_Intercept.ON)
        }

        override fun slot2() {
            list.add(_Intercept.OFF)
        }
    }

    public val Comment: DslSwitch3 = object : DslSwitch3() {
        override fun slot1() {
            list.add(_Comment.ON)
        }

        override fun slot2() {
            list.add(_Comment.OFF)
        }

        override fun slot3() {
            list.add(_Comment.ONLY_FOLLOW)
        }
    }

    public val At: DslSwitch3 = object : DslSwitch3() {
        override fun slot1() {
            list.add(_At.ON)
        }

        override fun slot2() {
            list.add(_At.OFF)
        }

        override fun slot3() {
            list.add(_At.ONLY_FOLLOW)
        }
    }

    public val Like: DslSwitch2 = object : DslSwitch2() {
        override fun slot1() {
            list.add(_Like.ON)
        }
        override fun slot2() {
            list.add(_Like.OFF)
        }
    }

    public val FoldUnfollowed: DslSwitch2 = object : DslSwitch2() {
        override fun slot1() {
            list.add(_FoldUnfollowed.ON)
        }

        override fun slot2() {
            list.add(_FoldUnfollowed.OFF)
        }
    }
}

public interface DslSwitchStatus

public enum class DslSwitch2Status : DslSwitchStatus { FIRST, SECOND; }

public enum class DslSwitch3Status : DslSwitchStatus { FIRST, SECOND, THIRD; }

public interface DslSwitch

public abstract class DslSwitch2 : DslSwitch {
    public infix fun set(status: DslSwitch2Status): Unit = when (status) {
        DslSwitch2Status.FIRST -> slot1()
        DslSwitch2Status.SECOND -> slot2()
    }

    internal abstract fun slot1()

    internal abstract fun slot2()
}

public abstract class DslSwitch3 : DslSwitch2() {
    public infix fun set(status: DslSwitch3Status): Unit = when (status) {
        DslSwitch3Status.FIRST -> slot1()
        DslSwitch3Status.SECOND -> slot2()
        DslSwitch3Status.THIRD -> slot3()
    }

    internal abstract fun slot3()
}
